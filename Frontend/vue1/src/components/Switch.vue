<template>
  <label class="switch" :class="{toggled, disabled}">
    <input type="checkbox" class="switch-input" @change.stop="toggle"/>
    <span class="switch-core" :style="switchcoreStyle"></span>
    <div v-if="labels">
      <span class="switch-label left">{{labelChecked}}</span>
      <span class="switch-label right">{{labelUnchecked}}</span>
    </div>
  </label>
</template>
<script>
  const DEFAULT_CHECKED_COLOR = '#75C791'
  const DEFAULT_UNCHECKED_COLOR = '#bfcbd9'

  const DEFAULT_CHECKED_LABEL = 'on'
  const DEFAULT_UNCHECKED_LABEL = 'off'

  const CORE_SIZE = 20
  const DEFAULT_HEIGHT = 22
  const DEFAULT_WIDTH = 50

  const objectHas = (object, title) => {
    return typeof object === 'object' && object.hasOwnProperty(title)
  }

  export default {
    name: 'switch',
    props: {
      value: {
        type: Boolean,
        default: false
      },
      disabled: {
        type: Boolean,
        default: false
      },
      sync: {
        type: Boolean,
        default: false
      },
      scale: {
        type: Number,
        default: 1
      },
      color: {
        type: [String, Object],
        validator (value) {
          if (typeof value === 'object') {
            return value.checked || value.unchecked
          }
          return typeof value === 'string'
        }
      },
      labels: {
        type: [Boolean, Object],
        default: false,
        validator (value) {
          if (typeof value === 'object') {
            return value.checked || value.unchecked
          }
          return typeof value === 'boolean'
        }
      },
      width: {
        type: Number,
        default: 50
      }
    },
    computed: {
      colorChecked () {
        if (typeof this.color !== 'object') {
          return this.color || DEFAULT_CHECKED_COLOR
        }
        return objectHas(this.color, 'checked')
          ? this.color.checked
          : DEFAULT_CHECKED_COLOR
      },
      colorUnchecked () {
        return objectHas(this.color, 'unchecked')
          ? this.color.unchecked
          : DEFAULT_UNCHECKED_COLOR
      },
      colorCurrent () {
        return this.toggled ? this.colorChecked : this.colorUnchecked
      },
      labelChecked () {
        return objectHas(this.labels, 'checked')
          ? this.labels.checked
          : DEFAULT_CHECKED_LABEL
      },
      labelUnchecked () {
        return objectHas(this.labels, 'unchecked')
          ? this.labels.unchecked
          : DEFAULT_UNCHECKED_LABEL
      },
      coreStyle () {
        return {
          'background-color': this.colorCurrent,
          'border-color': this.colorCurrent,
          '--switch-width': this.width + 'px',
          '--switch-transform-distance': (this.width - CORE_SIZE) + 'px'
        }
      }
    },
    watch: {
      value (value) {
        if (this.sync) {
          this.toggled = value
        }
      }
    },
    data () {
      return {
        toggled: this.value
      }
    },
    methods: {
      toggle (event) {
        this.toggled = !this.toggled
        this.$emit('change', {value: this.toggled, srcEvent: event})
      }
    }
  }
</script>
<style lang="scss" scoped>
  switch {
    display: inline-block;
    position: relative;
    overflow: hidden;
    vertical-align: middle;
    user-select: none;
    cursor: pointer;
    line-height: 22px;
    height: 22px;

    .switch-input {
      display: none;
    }
    .switch-label {
      position: absolute;
      top: 0;
      font-size: 10px;
      font-weight: 600;
      line-height: 22px;
      height: 22px;
      color: white;
      &.left {
        left: 10px;
      }
      &.right {
        right: 10px;
      }
    }
    switch-core {
      margin: 0;
      display: inline-block;
      position: relative;
      border: 1px solid #bfcbd9;
      outline: 0;
      border-radius: 12px;
      box-sizing: border-box;
      background: #bfcbd9;
      transition: border-color .3s, background-color .3s;
      user-select: none;
      width: 50px;
      width: var(--switch-width);
      height: 22px;

      &:before {
        display: block;
        content: '';
        overflow: hidden;
        transform: translate(2px, 2px);
        top: 0;
        left: 0;
        position: absolute;
        border-radius: 100%;
        transition: transform .3s;
        width: 16px;
        height: 16px;
        z-index: 20;
        background-color: #fff;
      }
    }
    &.toggled {
      .switch-core {
        &:before {
          transform: translate(30px, 2px);
          transform: translate(var(--switch-transform-distance), 2px);
        }
      }
    }
    &.disabled {
      pointer-events: none;
      cursor: not-allowed;
      opacity: 0.6;
    }
  }
</style>
