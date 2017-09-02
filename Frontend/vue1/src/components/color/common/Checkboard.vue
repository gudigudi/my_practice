<template>
  <div class="color__c-checkboard" :style="{background: bgStyle}"></div>
</template>
<script>
  function renderCheckboard (c1, c2, size) {
    if (typeof document === 'undefined') {
      return null
    }
    let canvas = document.createElement('canvas')
    canvas.width = canvas.height = size * 2
    let ctx = canvas.getContext('2d')

    if (!ctx) {
      return null
    }
    ctx.fillStyle = c1
    ctx.fillRect(0, 0, canvas.width, canvas.height)
    ctx.fillStyle = c2
    ctx.fillRect(0, 0, size, size)
    ctx.translate(size, size)
    ctx.fillRect(0, 0, size, size)
    return canvas.toDataURL()
  }

  function getCheckboard (c1, c2, size) {
    let key = c1 + ',' + c2 + ',' + size
    if (_checkboardCache[key]) {
      return _checkboardCache[key]
    } else {
      let checkboard = renderCheckboard(c1, c2, size)
      _checkboardCache[key] = checkboard
      return checkboard
    }
  }

  let _checkboardCache = {}

  export default {
    name: 'checkboard',
    props: {
      size: {
        type: [Number, String],
        default: 8
      },
      white: {
        type: String,
        default: '#fff'
      },
      grey: {
        type: String,
        default: '#e6e6e6'
      }
    },
    computed: {
      bgStyle () {
        return 'url(' + getCheckboard(this.white, this.grey, this.size) + ') center left'
      }
    }

  }
</script>
<style lang="scss" scoped>
  .color__c-checkboard {
    position: absolute;
    top: 0px;
    right: 0px;
    bottom: 0px;
    left: 0px;
  }
</style>
