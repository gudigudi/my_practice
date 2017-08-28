<template>
  <div class="color__swatches" :data-pick="pick">
    <div class="color__swatches__box">
      <div class="color__swatches__color-group" v-for="group in defaultColors">
        <div class="color__swatches__color-it" v-for="c in group"
             :data-color="c" :style="{background: c}" @click="handleClick(c)">
          <div class="color__swatches__pick" v-show="c===pick">
            <svg style="width: 24px;height: 24px;" viewBox="0 0 24 24">
              <path d="M21,7L9,19L3.5,13.5L4.91,12.09L9,16.17L19.59,5.59L21,7Z"/>
            </svg>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import color from '../../mixins/color'
  import material from 'material-colors'

  const colorMap = [
    'red', 'ink', 'purple', 'deepPurple',
    'indigo', 'blue', 'lightBlue', 'cyan',
    'teal', 'green', 'lightGreen', 'lime',
    'yellow', 'amber', 'orange', 'deepOrange',
    'brown', 'blueGrey'
  ]
  const colorLevel = ['900', '700', '500', '300', '100']
  let defaultColors = (() => {
    let colors = []

    colorMap.forEach((type) => {
      let typeColor = []

      colorLevel.forEach((level) => {
        typeColor.push(material[type][level].toUpperCase())
      })
      colors.push(typeColor)
    })
    return colors
  })()

  export default {
    name: 'swatches',
    mixins: [color],
    computed: {
      pick () {
        return this.colors.hex
      }
    },
    data () {
      return {
        defaultColors: defaultColors
      }
    },
    methods: {
      handlerClick (c) {
        this.colorChange({
          hex: c,
          source: 'hex'
        })
      }
    }
  }
</script>
<style lang="scss" scoped>
  .color__swatches {
    width: 320px;
    height: 240px;
    overflow-y: scroll;
    background-color: #fff;
    box-shadow: 0 2px 10px rgba(0, 0, 0, .12), 0 2px 5px rgba(0, 0, 0, .16);
  }

  .color__swatches__box {
    padding: 16px 0 6px 16px;
    overflow: hidden;
  }

  .color__swatches__color-group {
    padding-bottom: 10px;
    width: 40px;
    float: left;
    margin-right: 10px;
  }

  .color__swatches__color-it {
    width: 40px;
    height: 24px;
    cursor: pointer;
    background: #880e4f;
    margin-bottom: 1px;
    overflow: hidden;
    border-radius: 2px 2px 0 0;
  }

  .color__swatches__pick {
    fill: rgb(255, 255, 255);
    margin-left: 8px;
    display: block;
  }
</style>
