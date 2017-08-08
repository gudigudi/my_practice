<template>
  <label class="v-switch" :class="{toggled, disabled}">
    <input type="checkbox" class="v-switch-input" @change.stop="toggle"/>
    <span class="v-switch-core" :style="switchcoreStyle"></span>
    <div v-if="labels">
      <span class="v-switch-label v-left">{{labelChecked}}</span>
      <span class="v-switch-label v-right">{{labelUnchecked}}</span>
    </div>
  </label>
</template>
<script>

  const DEFAULT_CHECKED_COLOR = '#75C791'


  export default {
    name: 'VButton',
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
      stylecoreStyle () {
        if (typeof this.color !== 'object') {
          return this.color ||
        }
      }
    }
  }
</script>
