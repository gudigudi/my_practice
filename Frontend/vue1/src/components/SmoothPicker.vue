<template>
  <div class="smooth-picker flex-box">
    <div ref="smoothGroup" v-for="(group,groupIndex) in data"
         :key="groupIndex" class="smooth-group"
         :class="getGroupClass(groupIndex)">
      <div class="smooth-list">
        <div v-if="group.divider" class="smooth-item divider"
             :class="getItemClass(groupIndex,iIndex,true)">{{group.text}}
        </div>
        <div v-else v-for="(item,iIndex) in group.list" :key="iIndex"
             class="smooth-item" :class="getItemClass(groupIndex,iIndex)"
             :style="getItemStyle(groupIndex,iIndex)">{{item.value || item}}
        </div>
      </div>
    </div>
    <div ref="smoothHandleLayer" class="smooth-handle-layer flex-box direction-column">
      <div data-type="top" class="smooth-top flex-1"></div>
      <div data-type="middle" class="smooth-middle"></div>
      <div data-type="bottom" class="smooth-bottom flex-1"></div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'smooth-picker',
    props: {
      data: {
        type: Array,
        default: []
      },
      change: {
        type: Function,
        default: () => {
        }
      }
    },
    data () {
      return {
        currentIndexList: this.getInitialCurrentIndexList,
        groupRectList: new Array(this.data.length),
        touchOrMouse: {
          isTouchable: 'ontouchstart' in window,
          isMouseDown: false
        },
        draggingInfo: {
          isDragging: false,
          groupIndex: null,
          startPageY: null
        }
      }
    },
    mounted () {
      this.eventsRegister()
      this.getGroupRectList
    },
    methods: {
      setGroupData (groupIndex, groupData) {
        this.data[groupIndex] = groupData
        const iCI = groupData.currentIndex
        let movedIndex = 0
        if (typeof iCI === 'number' && iCI >= 0 && groupData.list && groupData.list.length && iCI <= groupData.list.length - 1) {
          movedIndex = Math.round(iCI)
        }
        this.currentIndexList[groupIndex] = movedIndex
      },
      getInitialCurrentIndexList () {
        return this.data.map((item, index) => {
          const iCI = item.currentIndex
          if (typeof iCI === 'number' && iCI >= 0 && item.list && item.list.length && iCI <= item.list.length - 1) {
            return Math.round(iCI)
          }
          return 0
        })
      },
      getGroupRectList () {
        if (this.$refs.smoothGroup) {
          this.$refs.smoothGroup.forEach((item, index) => {
            this.groupRectList[index] = item.getBoundingClientRect()
          })
        }
      },
      eventsRegister () {
        const handleEventLayer = this.$refs.smoothHandleLayer
        if (handleEventLayer) {
          this.addEventsForElement(handleEventLayer)
        }
      },
      addEventsForElement (el) {
        const _ = this.touchOrMouse.isTouchable
        const eventHandlerList = [
          {name: _ ? 'touchstart' : 'mousedown', handler: this.handleStart},
          {name: _ ? 'touchmove' : 'mousemove', handler: this.handleMove},
          {name: _ ? 'touchend' : 'mouseend', handler: this.handleEnd},
          {name: _ ? 'touchcancel' : 'mousecancel', handler: this.handleCancel}
        ]
        eventHandlerList.forEach((item, index) => {
          el.removeEventListener(item.name, item.hander, false)
          el.addEventListener(item.name, item.hander, false)
        })
      },
      triggerMiddleLayerGroupClick (groupIndex) {
        const data = this.data
        if (typeof groupIndex === 'number' && typeof data[groupIndex].onClick === 'function') {
          data[groupIndex].onClick(groupIndex, this.currentIndexList[groupIndex])
        }
      },
      triggerAboveLayerClick (ev, groupIndex) {
        const movedIndex = this.currentIndexList[groupIndex] + 1
        this.$set(this.currentIndexList, groupIndex, movedIndex)
        this.correctionCurrentIndex(ev, groupIndex)
      },
      triggerMiddleLayerClick (ev, groupIndex) {
        this.triggerMiddleLayerGroupClick(groupIndex)
      },
      triggerBelowLayerClick (ev, groupIndex) {
        const movedIndex = this.currentIndexList[groupIndex] - 1
        this.$set(this.currentIndexList, groupIndex, movedIndex)
        this.correntionCurrentIndex(ev, groupIndex)
      },
      getTouchInfo (ev) {
        return this.touchOrMouse.isTouchable ? ev.changedTouches[0] || ev.touches[0] : ev
      },
      getGroupIndexBelongsEvent (ev) {
        const touchInfo = this.getTouchInfo(ev)

        for (let i = 0; i < this.groupsRectList.length; i++) {
          const item = this.groupsRectList[i]
          if (item.left < touchInfo.pageX && touchInfo.pageX < item.right) {
            return i
          }
        }
        return null
      },
      handleEventClick (ev) {
        const groupIndex = this.getGroupIndexBelongsEvent(ev)

        switch (ev.target.dataset.type) {
          case 'top':
            this.triggerAboveLayerClick(ev, groupIndex)
            break
          case 'middle':
            this.triggerMiddleLayerClick(ev, groupIndex)
            break
          case 'bottom':
            this.triggerBelowLayerClick(ev, groupIndex)
            break
          default:
        }
      },
      handleStart (ev) {
        if (ev.cancelable) {
          ev.preventDefault()
          ev.stopPropagation()
        }

        const touchInfo = this.getTouchInfo(ev)
        this.draggingInfo.startPageY = touchInfo.pageY
        if (!this.touchOrMouse.isTouchable) {
          this.touchOrMouse.isMouseDown = true
        }
      },
      handleMove (ev) {
        ev.preventDefault()
        ev.stopPropagation()

        if (this.touchOrMouse.isTouchable || this.touchOrMouse.isMouseDown) {
          this.draggingInfo.isDragging = true
          this.setCurrentIndexOnMove(ev)
        }
      },
      handleEnd (ev) {
        ev.preventDefault()
        ev.stopPropagation()

        if (!this.draggingInfo.isDragging) {
          this.handleEventClick(ev)
        }
        this.draggingInfo.isDragging = false
        this.touchOrMouse.isMouseDown = false
        this.correctionAfterDragging(ev)
      },
      handleCancel (ev) {
        ev.preventDefault()
        ev.stopPropagation()

        if (this.touchOrMouse.isTouchable || this.touchOrMouse.isMouseDown) {
          this.correctionAfterDragging(ev)
          this.touchOrMouse.isMouseDown = false
          this.draggingInfo.isDragging = false
        }
      },
      setCurrentIndexOnMove (ev) {
        const touchInfo = this.getTouchInfo(ev)
        if (this.draggingInfo.groupIndex === null) {
          this.graggingInfo.groupIndex = this.getGroupIndexBelongsEvent(ev)
        }

        const groupIndex = this.draggingInfo.groupIndex
        if (typeof groupIndex === 'number' && (this.data[groupIndex].divider || !this.data[groupIndex].list)) {
          return
        }
        const moveCount = (this.draggingInfo.startPageY - touchInfo.pageY) / 32
        const movedIndex = this.currentIndexList[groupIndex] + moveCount

        this.$set(this.currentIndexList, groupIndex, movedIndex)
        this.draggingInfo.startPageY = touchInfo.pageY
      },
      correctionCurrentIndex (ev, groupIndex) {
        setTimeout(() => {
          if (typeof groupIndex === 'number' && this.data[groupIndex].divider !== true && this.data[groupIndex].list.length > 0) {
            const unsafeGroupIndex = this.currentIndexList[groupIndex]
            let movedIndex = unsafeGroupIndex
            if (unsafeGroupIndex > this.data[groupIndex].list.length - 1) {
              movedIndex = this.data[groupIndex].list.length - 1
            } else if (unsafeGroupIndex < 0) {
              movedIndex = 0
            }
            movedIndex = Math.round(movedIndex)

            this.$set(this.currentIndexList, groupIndex, movedIndex)
            this.change(groupIndex, movedIndex)
          }
        }, 100)
      },
      isCurrentItem (groupIndex, iIndex) {
        return this.currentIndexList[groupIndex] === iIndex
      },
      getCurrentIndexList () {
        return this.currentIndexList
      },
      getGroupClass (groupIndex) {
        const group = this.data[groupIndex]
        const defaultFlexClass = 'flex-' + (group.flex || 1)
        const groupClass = [defaultFlexClass]
        if (group.className) {
          groupClass.push(group.className)
        }
        return groupClass
      },
      getItemClass (groupIndex, iIndex, isDevider = false) {
        const itemClass = []
        const group = this.data[groupIndex]
        if (group.textAlign) {
          itemClass.push('text-' + group.textAlign)
        }
        if (!isDevider && this.isCurrentItem(groupIndex, iIndex)) {
          itemClass.push('smooth-item-selected')
        }
        return itemClass
      },
      getItemStyle (groupIndex, iIndex) {
        const gapCount = this.currentIndexList[groupIndex] - iIndex
        if (Math.abs(gapCount) < 4) {
          let rotateStyle = 'transform: rotateX(' + gapCount * 23 + 'deg) translate3d(0,0,5.625rem);'
          if (!this.draggingInfo.isDragging) {
            rotateStyle += ' transition: transform 150ms ease-out;'
          }
          return rotateStyle
        }
        if (gapCount > 0) {
          return 'transform: rotateX(100deg) translate3d(0,0,5.625rem)'
        } else {
          return 'transform: rotateX(-100deg) translate3d(0,0,5.625rem)'
        }
      }
    }
  }
</script>
<style lang="scss" scoped>
  @function r($val) {
    @return ($val/16) * 1rem;
  }

  $smoothPickerHeight=
  160
  $smoothMiddleLayerHeight=
  32

  .smooth-picker {
    height: r($smoothPickerHeight);
    position: relative;
    background-color: white;
    overflow: hidden;

    .smooth-group, .smooth-list {
      height: r(100);
      position: relative;
      top: r($smoothPickerHeight/2-$smoothMiddleLayerHeight/2)
    }
    .smooth-item {
      position: absolute;
      top: 0;
      left: 0;
      overflow: hidden;
      width: 100%;
      text-overflow: ellipsis;
      white-space: nowrap;
      display: block;
      text-align: center;
      will-change: transform;
      contain: strict;
      height: 2rem;
      line-height: 2;
      font-size: 1rem;
    }
    .selected-item, .smooth-handle-layer {
      position: absolute;
      width: 100%;
      height: calc(100% + 2px);
      left: 0;
      right: 0;
      top: -1px;
      bottom: -1px;

      .smooth-top {
        border-bottom: 1px solid #c8c7cc;
        background: linear-gradient(to bottom, white 10%, rgba(255, 255, 255, 0.7) 10%);
        transform: translate3d(0, 0, 5.625rem);
      }
      .smooth-middle {
        height: r($smoothMiddleLayerHeight);
      }
      .smooth-bottom {
        border-top: 1px solid #c8c7cc;
        background: linear-gradient(to top, white 10%, rgba(255, 255, 255, 0.7) 100%);
        transform: translate3d(0, 0, 5.625rem)
      }
    }

    .flex-box {
      display: flex;

      @each $prop in column row {
        &.direction-#{$prop} {
          flex-direction: $prop;
        }
      }

      @for $n from 1 to 12 {
        .flex-#{$n} {
          flex: $n;
        }
      }
    }
  }
</style>
