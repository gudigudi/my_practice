<template>

</template>
<script>
  import _ from 'lodash'

  export default {
    name: 'TreeViewItem',
    props: {
      data: Object,
      'max-depth': Number,
      'current-depth': Number
    },
    data () {
      return {
        open: this.currentDepth < this.maxDepth
      }
    },
    methods: {
      isOpen () {
        return this.open
      },
      toggleOpen () {
        this.open = !this.open
      },
      getKey (value) {
        if (Number.isInteger(value.key)) {
          return value.key + ':'
        } else {
          return '"' + value.key + '":'
        }
      },
      getValue (value) {
        if (Number.isFinite(value.value)) {
          return value.value
        }
        if (_.isNull(value.value)) {
          return 'null'
        }
        if (_.isString(value.value)) {
          return '"' + value.value + '"'
        }
        return value.value
      },
      getValueType (value) {
        let prefix = 'tree-view-item-value-'

        if (Number.isFinite(value.value)) {
          return prefix + 'number'
        }
      }
    }
  }
</script>
<style>

</style>
