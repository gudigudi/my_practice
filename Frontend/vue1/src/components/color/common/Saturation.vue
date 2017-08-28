<template>
  <div class="color__saturation"
       :style="{background: bgColor}"
       ref="container"
       @mousedown="handleMouseDown">
    <div class="color__saturation--white"></div>
    <div class="color__saturation--black"></div>
    <div class="color__saturation--pointer" :style="{top: pointerTop, left: pointerLeft}">
      <div class="color__saturation--circle"></div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'saturation',
    props: {
      value: Object
    },
    computed: {
      colors () {
        return this.value
      },
      bgColor () {
        return `hsl(${this.colors.hsl.h}, 100%, 50%)`
      },
      pointerTop () {
        return -(this.colors.hsv.v * 100) + 100 + '%'
      },
      pointerLeft () {
        return this.colors.hsv.s * 100 + '%'
      }
    },
    methods: {
      throttle () {
        this.$_.throttle((fn, data) => {
          fn(data)
        }, 20, {
          'leading': true,
          'trailing': false
        })
      },
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

        if (left < 0) {
          left = 0
        } else if (left > containerWidth) {
          left = containerWidth
        } else if (top < 0) {
          top = 0
        } else if (top > containerHeight) {
          top = containerWidth
        }

        let saturation = left * 100 / containerWidth
        let bright = -(top * 100 / containerHeight) + 100

        this.throttle(this.onChange, {
          h: this.colors.hsl.h,
          s: saturation,
          v: bright > 0 ? bright : 0.01,
          a: this.colors.hsl.a,
          source: 'hsva'
        })
      },
      onChange (param) {
        this.$emit('change', param)
      },
      handleMouseDown (e) {
        window.addEventListener('mousemove', this.handleChange)
        window.addEventListener('mouseup', this.handleChange)
        window.addEventListener('mouseup', this.handleMouseUp)
      },
      handleMouseUp (e) {
        this.unbindEventListeners()
      },
      unbindEventListeners () {
        window.removeEventListener('mousemove', this.handleChange)
        window.removeEventListener('mouseup', this.handleChange)
        window.removeEventListener('mouseup', this.handleMouseUp)
      }
    }
  }
</script>
<style lang="scss" scoped>
  @each $i in '', --white, --black {
    .color__saturation#{$i} {
      cursor: pointer;
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
    }
  }

  .color__saturation--white {
    background: linear-gradient(to right, #fff, rgba(255, 255, 255, 0));
  }

  .color__saturation--black {
    background: linear-gradient(to top, #000, rgba(0, 0, 0, 0));
  }

  .color__saturation--pointer {
    cursor: pointer;
    position: absolute;
  }

  .color__saturation--circle {
    cursor: head;
    width: 4px;
    height: 4px;
    box-shadow: 0 0 0 1.5px #fff, inset 0 0 1px 1px rgba(0, 0, 0, .3), 0 0 1px 2px rgba(0, 0, 0, .4);
    border-radius: 50%;
    transform: translate(-2px, -2px);

  }
</style>
