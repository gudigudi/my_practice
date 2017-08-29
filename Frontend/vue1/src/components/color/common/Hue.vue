<template>
  <div :class="['color__c-hue', directionClass]">
    <div class="color__c-hue__container" ref="container" @mousedown="handleMouseDown" @touchmove="handleChange"
         @touchstart="handleChange">
      <div class="color__c-hue__pointer" :style="{top: pointerTop,left:pointerLeft}">
        <div class="color__c-hue__picker"></div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'hue',
    props: {
      value: Object,
      direction: {
        type: String,
        default: 'horizontal'
      }
    },
    computed: {
      colors () {
        return this.value
      },
      directionClass () {
        return {
          'color__c-hue--horizontal': this.direction === 'horizontal',
          'color__c-hue--vertical': this.direction === 'vertical'
        }
      },
      pointerTop () {
        if (this.direction === 'vertical') {
          return -((this.colors.hsl.h * 100) / 360) + 100 + '%'
        } else {
          return 0
        }
      },
      pointerLeft () {
        if (this.direction === 'vertical') {
          return 0
        } else {
          return (this.colors.hsl.h * 100) / 360 + '%'
        }
      }
    },
    methods: {
      handleChange (e, skip) {
        !skip && e.preventDefault()

        let container = this.$refs.container
        let containerWidth = container.clientWidth
        let containerHeight = container.clientHeight

        let xOffset = container.getBoundingClientRect().left + window.pageXOffset
        let yOffset = container.getBoundingClientRect().top + window.pageYOffset
        let pageX = e.pageX || (e.touches ? e.touches[0].pageX : 0)
        let pageY = e.pageY || (e.touches ? e.touches[0].pageY : 0)
        let left = pageX - xOffset
        let top = pageY - yOffset

        let h
        let percent

        if (this.direction === 'vertical') {
          if (top < 0) {
            h = 359
          } else if (top > containerHeight) {
            h = 0
          } else {
            percent = -(top * 100 / containerHeight) + 100
            h = (360 * percent / 100)
          }

          if (this.colors.hsl.h !== h) {
            this.$emit('change', {
              h: h,
              s: this.colors.hsl.s,
              l: this.colors.hsl.l,
              a: this.colors.hsl.a,
              source: 'hsl'
            })
          }
        } else {
          if (left < 0) {
            h = 0
          } else if (left > containerWidth) {
            h = 359
          } else {
            percent = left * 100 / containerWidth
            h = (360 * percent / 100)
          }

          if (this.colors.hsl.h !== h) {
            this.$emit('change', {
              h: h,
              s: this.colors.hsl.s,
              l: this.colors.hsl.l,
              a: this.colors.hsl.a,
              source: 'hsl'
            })
          } else {
            if (left < 0) {
              h = 0
            } else if (left > containerWidth) {
              h = 359
            } else {
              percent = left * 100 / containerWidth
              h = (360 * percent / 100)
            }

            if (this.colors.hsl.h !== h) {
              this.$emit('change', {
                h: h,
                s: this.colors.hsl.s,
                l: this.colors.hsl.l,
                a: this.colors.hsl.a,
                source: 'hsl'
              })
            }
          }
        }
      },
      handleMouseDown (e) {
        this.handleChange(e, true)
        window.addEventListener('mousemove', this.handleChange)
        window.addEventListener('mouseup', this.handleMouseUp)
      },
      handleMouseUp (e) {
        this.unbindEventListeners()
      },
      unbindEventListeners () {
        window.removeEventListener('mousemove', this.handleChange)
        window.removeEventListener('mouseup', this.handleMouseUp)
      }
    }
  }
</script>
<style lang="scss" scoped>
  .color__c-hue {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    border-radius: 2px;
  }

  .color__c-hue--horizontal {
    background: linear-gradient(to right, #f00 0%, #ff0 17%, #0f0 33%, #00f 67%, #f0f 83%, #f00 100%);
  }

  .color__c-hue--vertical {
    background: linear-gradient(to top, #f00 0%, #ff0 17%, #0f0 33%, #00f 67%, #f0f 83%, #f00 100%);
  }

  .color__c-hue__container {
    cursor: pointer;
    margin: 0 2px;
    position: relative;
    height: 100%;
  }

  .color__c-hue__pointer {
    z-index: 2;
    position: absolute;
  }

  .color__c-hue__picker {
    cursor: pointer;
    margin-top: 1px;
    width: 4px;
    border-radius: 1px;
    height: 8px;
    box-shadow: 0 0 2px rgba(0, 0, 0, .6);
    background: #fff;
    transform: translateX(-2px);
  }
</style>
