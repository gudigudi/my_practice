<template>
  <div class="color__material">
    <editable-input class="color__material__hex" label="hex" v-model="colors.hex"
                    :style="{borderColor: colors.hex}" @change="onChange"></editable-input>
    <div class="color__material__split">
      <div class="color__material__third">
        <editable-input label="r" v-model="colors.rgba.r" @change="onChange"></editable-input>
      </div>
      <div class="color__material__third">
        <editable-input label="g" v-model="colors.rgba.g" @change="onChange"></editable-input>
      </div>
      <div class="color__material__third">
        <editable-input label="b" v-model="colors.rgba.b" @change="onChange"></editable-input>
      </div>
    </div>
  </div>
</template>
<script>
  import color from '../../mixins/color'
  import EditableInput from './common/Editable-Input.vue'

  export default {
    components: {EditableInput},
    name: 'material',
    mixins: [color],
    methods: {
      onChange (data) {
        if (!data) {
          return
        }
        if (data.hex) {
          this.isValidHex(data.hex) && this.colorChange({
            hex: data.hex,
            source: 'hex'
          })
        } else if (data.r || data.g || data.b) {
          this.colorChange({
            r: data.r || this.colors.rgba.r,
            g: data.g || this.colors.rgba.g,
            b: data.b || this.colors.rgba.b,
            a: data.a || this.colors.rgba.a,
            source: 'rgba'
          })
        }
      }
    }
  }
</script>
<style lang="scss" scoped>
  .color__material {
    width: 98px;
    height: 98px;
    padding: 16px;
    font-family: 'Open Sans', "Helvetica Neue", Helvetica, Arial, sans-serif;
    position: relative;
    border-radius: 2px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, .12), 0 2px 5px rgba(0, 0, 0, .16);
    background-color: #fff;
    .color__editable-input__input {
      width: 100%;
      height: 30px;
      margin-top: 12px;
      font-size: 15px;
      color: #333;
    }
    .color__editable-input__label {
      position: absolute;
      top: 0;
      left: 0;
      font-size: 11px;
      color: #999;
      text-transform: capitalize;
    }
  }

  .color__material__hex {
    border-bottom-width: 2px;
    border-bottom-style: solid;
  }

  .color__material__split {
    display: flex;
    margin-right: -10px;
    padding-top: 11px;
  }

  .color__material__third {
    flex: 1;
    padding-right: 10px;
  }
</style>
