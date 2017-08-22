<template>
  <div class="tree-view-wrapper">
    <tree-view-item class="tree-view-item-root" :data="parsedData"
                    :max-depth="allOptions.maxDepth" :current-depth="0"></tree-view-item>
  </div>
</template>
<script>
  import TreeViewItem from './TreeViewItem.vue'

  export default {
    name: 'tree-view',
    components: {
      TreeViewItem
    },
    props: ['data', 'options'],
    methods: {
      transformValue: function (valueToTransform, keyForValue) {
        return {
          key: keyForValue,
          type: 'value',
          value: valueToTransform
        }
      },
      transformArray: function (arrayToTransform, keyForArray) {
        return {
          key: keyForArray,
          type: 'array',
          children: this.generateChildFromCollection(arrayToTransform)
        }
      },
      transformObject: function (objectToTransform, keyForObject, isRootObject = false) {
        return {
          key: keyForObject,
          type: 'object',
          isRoot: isRootObject,
          children: this.generateChildFromCollection(objectToTransform)
        }
      },
      generateChildFromCollection: function (collection) {
        return this.$_.map(collection, (value, keyOrIndex) => {
          if (this.$_.isPlainObject(value)) {
            return this.transformObject(value, keyOrIndex)
          }
          if (this.$_.isArray(value)) {
            return this.transformArray(value, keyOrIndex)
          }
          if (!this.$_.isPlainObject(value) && !this.$_.isArray(value)) {
            return this.transformValue(value, keyOrIndex)
          }
        })
      }
    },
    computed: {
      allOptions: function () {
        return this.$_.extend({}, {
          rootObjectKey: 'root',
          maxDepth: 4
        }, (this.options || {}))
      },
      parsedData: function () {
        if (!this.$_.isPlainObject(this.data) && !this.$_.isArray(this.data)) {
          return this.transformValue(this.data, this.allOptions.rootObjectKey)
        }
        return this.transformObject(this.data, this.allOptions.rootObjectKey, true)
      }
    }
  }
</script>
<style lang="scss" scoped>
  .tree-view-wrapper {
    overflow: auto;
  }

  .tree-view-item-root {
    margin-left: 0 !important;
    .tree-view-item-leaf > .tree-view-item {
      margin-left: 0 !important;
    }
  }
</style>
