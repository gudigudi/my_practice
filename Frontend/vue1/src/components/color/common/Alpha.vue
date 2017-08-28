<template>
  <div class="color__c-alpha">
    <div class="color__c-alpha__checkboard-wrap">
      <checkboard></checkboard>
    </div>
    <div class="color__c-alpha__gradient" :style="{background: gradientColor}"></div>
    <div class="color__c-alpha__container" ref="container"
         @mousedown="handleMouseDown" @touchmove="handleChange" @touchstart="handleChange">
      <div class="color__c-alpha__pointer" :style="{left: colors.a * 100 + '%'}">
        <div class="color__c-alpha__picker"></div>
      </div>
    </div>
  </div>
</template>
<script>
  import Checkboard from './Checkboard.vue'

  export default {
    components: {Checkboard},
    name: 'alpha',
    props: {
      value: Object,
      onChange: Function
    },
    computed: {
      colors () {
        return this.value
      },
      gradientColor () {
        let rgba = this.colors.rgba
        let rgbStr = [rgba.r, rgba.g, rgba.b].join(',')
        return 'linear-gradient(to right, rgba(' + rgbStr + ', 0) 0%, rgba(' + rgbStr + ', 1) 100%)'
      }
    },
    methods: {
      handleChange (e, skip) {
        !skip && e.preventDefault()

        let container = this.$refs.container
        let containerWidth = container.clientWidth

        let xOffset = container.getBoundingClientRect().left + window.pageXOffset
        let pageX = e.pageX || (e.touches ? e.touches[0].pageX : 0)
        let left = pageX - xOffset

        let a
        if (left < 0) {
          a = 0
        } else if (left > containerWidth) {
          a = 1
        } else {
          a = Math.round(left * 100 / containerWidth) / 100
        }

        if (this.colors.a !== a) {
          this.$emit('change', {
            h: this.colors.hsl.h,
            s: this.colors.hsl.s,
            l: this.colors.hsl.l,
            a: a,
            source: 'rgba'
          })
        }
      },
      handleMouseDown (e) {
        this.handleChange(e, true)
        window.addEventListener('mousemove', this.handleChange)
        window.addEventListener('mouseup', this.handleMouseUp)
      },
      handleMouseUp () {
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
  @mixin color__c-alpha() {
    position: absolute;
    top: 0px;
    right: 0px;
    bottom: 0px;
    left: 0px;
  }

  @each $i in alpha, alpha__gradient {
    .color__c-#{$i} {
      @include color__c-alpha;
    }
  }

  .color__c-alpha__checkboard-wrap {
    @include color__c-alpha;
    overflow: hidden;
  }

  .color__c-alpha__container {
    cursor: pointer;
    position: relative;
    z-index: 2;
    height: 100%;
    margin: 0 3px;
  }

  .color__c-alpha__pointer {
    z-index: 2;
    position: absolute;
  }

  .color__c-alpha__picker {
    cursor: pointer;
    width: 4px;
    border-radius: 1px;
    height: 8px;
    box-shadow: 0 0 2px rgba(0, 0, 0, .6);
    background: #fff;
    margin-top: 1px;
    transform: translateX(-2px);
  }
</style>
