<template>
  <div ref="item" class="grid-layout" :style="mergedStyle">
    <slot></slot>
    <grid-item v-show="isDragging"
               :x="placeholder.x"
               :y="placeholder.y"
               :w="placeholder.w"
               :h="placeholder.h"
               :i="placeholder.i"
               class="grid-placeholder"></grid-item>
  </div>
</template>
<script>
  import GridItem from './Grid-Item.vue';

  let elementResizeDetectorMaker = require('element-resize-detector')

  export default {
    components: {GridItem},
    name: 'grid-layout',

    props: {
      autoSize: {
        type: Boolean,
        default: true
      },
      colNum: {
        type: Number,
        default: 12
      },
      rowHeight: {
        type: Number,
        default: 150
      },
      maxRows: {
        type: Number,
        default: Infinity
      },
      margin: {
        type: Array,
        default: () => {
          return [10, 10]
        }
      },
      isDraggable: {
        type: Boolean,
        default: true
      },
      isResizable: {
        type: Boolean,
        default: true
      },
      useCssTransforms: {
        type: Boolean,
        default: true
      },
      verticalCompact: {
        type: Boolean,
        default: true
      },
      layout: {
        type: Array,
        required: true
      }
    },
    data () {
      return {
        width: null,
        mergedStyle: {},
        lastLayoutLength: 0,
        isDragging: false,
        placeholder: {
          x: 0, y: 0, w: 0, h: 0, i: 0
        }
      }
    },
    created () {
      this.resizeEventHandler = (eventType, i, x, y, h, w) => {
        this.resizeEvent(eventType, i, x, y, h, w)
      }
      this.dragEventHandler = (eventType, i, x, y, h, w) => {
        this.dragEvent(eventType, i, x, y, h, w)
      }

      this.vm.$on('resizeEvent', this.resizeEventHandler)
      this.vm.$on('dragEvent', this.dragEventHandler)
    },
    beforeDestroy () {
      this.vm.$off('resizeEvent', this.resizeEventHandler)
      this.vm.$off('dragEvent', this.dragEventHandler)
      window.removeEventListener('resize', this.onWindowResize)
    },
    mounted () {
      this.vm.$nextTick(() => {
        validateLayout(this.layout)
        this.vm.$nextTick(() => {
          if (this.width === null) {
            this.onWindowResize()
            window.addEventListener('resize', this.onWindowResize)
          }
          compact(this.layout, this.verticalCompact)

          this.updateHeight()
          this.vm.$nextTick(() => {
            let erd = elementResizeDetectorMaker({
              strategy: 'scroll'
            })
            erd.listenTo(this.vm.$refs.item, () => {
              this.onWindowResize()
            })
          })
        })
        window.onload = () => {
          if (this.width === null) {
            this.onWindowResize()
            window.addEventListener('resize', this.onWindowResize)
          }
          compact(this.layout, this.verticalCompact)

          this.updateHeight()
          this.vm.$nextTick(() => {
            let erd = elementResizeDetectorMaker({
              strategy: 'scroll'
            })
            erd.listenTo(this.vm.$refs.item, () => {
              this.onWindowResize()
            })
          })
        }
      })
    },
    watch: {
      width () {
        this.vm.$nextTick(() => {
          this.vm.$emit('updateWidth', this.width)
          this.updateHeight()
        })
      },
      layout () {
        this.layoutUpdate()
      },
      rowHeight () {
        this.vm.$emit('setRowHeight', this.rowHeight)
      },
      isDraggable () {
        this.vm.$emit('setDraggable', this.isDraggable)
      },
      isResizable () {
        this.vm.$emit('setResizable', this.isResizable)
      }
    },
    methods: {
      layoutUpdate () {
        if (this.layout !== undefined && this.layout.length !== this.lastLayoutLength) {
          this.lastLayoutLength = this.layout.length
          compact(this.layout, this.verticalCompact)

          this.vm.$emit('updateWidth', this.width)
          this.updateHeight()
        }
      },
      updateHeight () {
        this.mergedStyle = {
          height: this.containerHeight()
        }
      },
      onWindowResize () {
        if (this.vm.$refs !== null && this.vm.$refs.item !== null && this.vm.$refs.item !== undefined) {
          this.width = this.vm.$refs.item.offsetWidth
        }
      },
      containerHeight () {
        if (!this.autoSize) return
        return bottom(this.layout) * (this.rowHeight + this.margin[1]) + this.margin[1] + 'px'
      },
      dragEvent (eventName, id, x, y, h, w) {
        if (eventName === 'dragmove' || eventName === 'dragstart') {
          this.isDragging = true
          this.placeholder.i = id
          this.placeholder.x = x
          this.placeholder.y = y
          this.placeholder.h = h
          this.placeholder.w = w
          this.vm.$emit('updateWidth', this.width)
        } else {
          this.isDragging = false
        }
        var l = getLayoutItem(this.layout, id)
        if (l === null) {
          l = {x: 0, y: 0}
        }
        l.x = x
        l.y = y
        this.layout = moveEvement(this.layout, l, x, y, true)
        compact(this.layout, this.verticalCompact)
        this.vm.$emit('compact')
        this.updateHeight()
      },
      resizeEvent (eventName, id, x, y, h, w) {
        if (eventName === 'resizemove' || eventName === 'resizetart') {
          this.isDragging = true
          this.placeholder.i = id
          this.placeholder.x = x
          this.placeholder.y = y
          this.placeholder.h = h
          this.placeholder.w = w
          this.vm.$emit('updateWidth', this.width)
        } else {
          this.isDragging = false
        }
        var l = getLayoutItem(this.layout, id)
        if (l === null) {
          l = {h: 0, w: 0}
        }
        l.h = h
        l.w = w
        compact(this.layout, this.verticalCompact)
        this.vm.$emit('compact')
        this.updateHeight()
      }
    }
  }
</script>
<style lang="scss" scoped>
  .grid-layout {
    position: relative;
    transition: height 200ms ease;
  }
</style>
