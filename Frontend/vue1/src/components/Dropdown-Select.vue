<template>

</template>
<script>
  export default {
    name: 'dropdown-select',

    props: {
      value: {
        default: null
      },
      options: {
        type: Array,
        default: () => []
      },
      maxHeight: {
        type: String,
        default: '400px'
      },
      searchable: {
        type: Boolean,
        default: true
      },
      multiple: {
        type: Boolean,
        default: false
      },
      placeholder: {
        type: String,
        default: ''
      },
      transition: {
        type: String,
        default: 'fade'
      },
      clearSearchOnSelect: {
        type: Boolean,
        default: true
      },
      closeOnSelect: {
        type: Boolean,
        default: true
      },
      label: {
        type: String,
        default: 'label'
      },
      getOptionLabel: {
        type: Function,
        default: function (option) {
          if (typeof option === 'object') {
            if (this.label && option[this.label]) {
              return option[this.label]
            }
          }
          return option
        }
      },
      onChange: {
        type: Function,
        default:
          (val) => {
            this.$emit('input', val)
          }
      },
      taggable: {
        type: Boolean,
        default: false
      },
      pushTags: {
        type: Boolean,
        default: false
      },
      createOption: {
        type: Function,
        default: function (newOption) {
          if (typeof this.mutableOptions[0] === 'object') {
            newOption = {[this.label]: newOption}
          }
          this.$emit('option:created', newOption)
          return newOption
        }
      },
      resetOnOptionsChange: {
        type: Boolean,
        default: false
      },
      noDrop: {
        type: Boolean,
        default: false
      },
      inputId: {
        type: String
      }
    },
    data () {
      return {
        search: '',
        open: false,
        mutableValue: null,
        mutableOptions: []
      }
    },
    watch: {
      value (val) {
        this.mutableValue = val
      },
      mutableValue (val, old) {
        if (this.multiple) {
          this.onChange ? this.onChange(val) : null
        } else {
          this.onChange && val !== old ? this.onChange(val) : null
        }
      },
      options (val) {
        this.mutableOptions = val
      },
      mutableOptions () {
        if (!this.taggable && this.resetOnOptionsChange) {
          this.mutableValue = this.multiple ? [] : null
        }
      },
      multiple (val) {
        this.mutableValue = val ? [] : null
      }
    },
    created () {
      this.mutableValue = this.value
      this.mutableOptionsthis.options.slice(0)
      this.mutableLoading = this.loading

      this.$on('option:created', this.maybePushTag)
    },
    methods: {}
  }
</script>
<style lang="scss" scoped></style>
