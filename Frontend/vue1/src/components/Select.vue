<template>

</template>
<script>
  import pointerScroll from "../mixins/pointerScroll";
  import typeAheadPointer from "../mixins/typeAheadPointer";
  import ajax from "../mixins/ajax";

  export default {
    name: 'my-select',
    mixins: [pointerScroll, typeAheadPointer, ajax],
    props: {
      value: {
        type: Object,
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
        default: (option) => {
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
        default: (val) => {
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
        default: (newOption) => {
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
    computed: {
      dropdownClasses () {
        return {
          open: this.dropdownOpen,
          single: !this.multiple,
          searching: this.searching,
          searchable: this.searchable,
          unsearchable: !this.searchable,
          loading: this.mutableLoading
        }
      },
      clearSearchOnBlur () {
        return this.clearSearchOnSelect && !this.multiple
      },
      searching () {
        return !!this.search
      },
      dropdownOpen () {
        return this.noDrop ? false : this.open && !this.mutableLoading
      },
      searchPlaceholder () {
        if (this.isValueEmpty && this.placeholder) {
          return this.placeholder
        }
      },
      filteredOptions () {
        let options = this.mutableOptions.filter((option) => {
          if (typeof option === 'object' && option.hasOwnProperty(this.label)) {
            return option[this.label].toLowerCase().indexOf(this.search.toLowerCase()) > -1
          }
          return option.toLowerCase().indexOf(this.search.toLowerCase()) > -1
        })
        if (this.taggable && this.search.length && !this.optionExists(this.search)) {
          options.unshift(this.search)
        }
        return options
      },
      isValueEmpty () {
        if (this.mutableValue) {
          if (typeof  this.mutableValue === 'object') {
            return !Object.keys(this.mutableValue).length
          }
          return !this.mutableValue.length
        }
        return true
      },
      valueAsArray () {
        if (this.multiple) {
          return this.mutableValue
        } else if (this.mutableValue) {
          return [this.mutableValue]
        }
        return []
      }
    },
    created () {
      this.mutableValue = this.value
      this.mutableOptions = this.options.slice(0)
      this.mutableLoading = this.loading
      this.$on('option:created', this.maybePushTag)
    },
    methods: {
      select (option) {
        if (this.isOptionSelected(option)) {
          this.deselect(option)
        } else {
          if (this.taggable && !this.optionExists(option)) {
            option = this.createOption(option)
          }
          if (this.multiple && !this.mutableValue) {
            this.mutableValue = [option]
          } else if (this.multiple) {
            this.mutableValue.push(option)
          } else {
            this.mutableValue = option
          }
        }
        this.onAfterSelect(option)
      },
      deselect (option) {
        if (this.multiple) {
          let ref = -1
          this.mutableValue.forEach((val) => {
            if (val === option || typeof  val === 'object' && val[this.label] === option[this.label]) {
              ref = val
            }
          })
          let index = this.mutableValue.indexOf(ref)
          this.mutableValue.splice(index, 1)
        } else {
          this.mutableValue = null
        }
      },
      onAfterSelect (option) {
        if (this.closeOnSelect) {
          this.open = !this.open
          this.$refs.search.blur()
        }
        if (this.clearSearchOnSelect) {
          this.search = ''
        }
      },
      toggleDropdown (e) {
        if (e.target === this.$refs.openIndicator || e.target === this.$refs.search || e.target === this.$refs.toggle || e.target === this.$el) {
          if (this.open) {
            this.$refs.search.blur()
          } else {
            this.open = true
            this.$refs.search.focus()
          }
        }
      },
      isOptionSelected (option) {
        if (this.multiple && this.mutableValue) {
          let selected = false
        }
      }
    }
  }
</script>
<style lang="scss" scoped>
  .select {
    position: relative;
    font-family: sans-serif;
    box-sizing: border-box;
    * {
      box-sizing: border-box;
    }
    &.open-indicator {
      position: absolute;
      bottom: 6px;
      right: 10px;
      display: inline-block;
      cursor: pointer;
      pointer-events: all;
      transition: all 150ms cubic-bezier(1.000, -0.115, 0.975, 0.855);
      transition-timing-function: cubic-bezier(1.000, -0.115, 0.975, 0.855);
      opacity: 1;
      transition: opacity .1s;
      height: 20px;
      width: 10px;
      &:before {
        border-color: rgba(60, 60, 60, .5);
        border-style: solid;
        border-width: 3px 3px 0 0;
        content: '';
        display: inline-block;
        height: 10px;
        width: 10px;
        vertical-align: top;
        transform: rotate(133deg);
        transition: all 150ms cubic-bezier(1.000, -0.115, 0.975, 0.855);
        transition-timing-function: cubic-bezier(1.000, -0.115, 0.975, 0.855);
        box-sizing: inherit;
      }
    }
  }
</style>
