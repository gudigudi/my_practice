<template>

</template>
<script>
  import {validators} from '../utils/validators'

  export default {
    name: 'input-tag',
    props: {
      tags: {
        type: Array,
        default: () => []
      },
      placeholder: {
        type: String,
        default: ''
      },
      onChange: {
        type: Function
      },
      readOnly: {
        type: Boolean,
        default: false
      },
      validate: {
        type: String,
        default: ''
      }
    },
    data () {
      return {
        newTag: ''
      }
    },
    methods: {
      focusNewTag () {
        if (this.readOnly) {
          return
        }
        this.$el.querySelector('.new-tag').focus()
      },
      addNew (tag) {
        if (tag && !this.tags.includes(tag) && this.validateIfNeeded(tag)) {
          this.tags.push(tag)
          this.tagChange()
        }
        this.newTag = ''
      },
      validateIfNeeded (tagValue) {
        if (this.validate === '' || this.validate === undefined) {
          return true
        } else if (Object.keys(validators).indexOf(this.validate) > -1) {
          return validators[this.validate].test(tagValue)
        }
        return true
      },
      remove (index) {
        this.tags.splice(index, 1)
        this.tagChange()
      },
      removeLastTag () {
        if (this.newTag) {
          return
        }
        this.tags.pop()
        this.tagChange()
      },
      tagChange () {
        if (this.onChange) {
          this.onChange(JSON.parse(JSON.stringify(this.tags)))
        }
      }
    }
  }
</script>
<style lang="scss" scoped></style>
