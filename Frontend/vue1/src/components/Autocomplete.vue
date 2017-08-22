<template>
  <div class="autocomplete">
    <div class="autocomplete-input-group"
         :class="{'auto-complete-selected': value}">
      <input type="search" v-model="searchText" :placeholder="placeholder"
             :class="inputClass" :disable="disabled" @blur="blur" @focus="focus" @input="inputChange"/>
    </div>
    <div class="autocomplete-list" v-if="showList && internalItems.length">
      <div class="autocomplete-list-item" v-for="item in internalItems" @click="onClickItem(item)">
        <div :is="componentItem" :item="item"></div>
      </div>
    </div>
  </div>
</template>
<script>
  import Item from './Item.vue'

  var minLen = 3
  var wait = 500

  //  var timeout = null

  function isUpdateItems (text) {
    if (text.length > this.minLen) {
      return true
    }
  }

  function callUpdateItems (text, cb) {
    clearTimeout(this.timeout)
    if (this.isUpdateItems(text)) {
      this.timeout = setTimeout(cb, this.wait)
    }
  }

  function findItem (items, text, autoSelectOneResult) {
    if (!text) return
    if (autoSelectOneResult && items.length === 1) {
      return items[0]
    }
  }

  export default {
    name: 'autocomplete',
    props: {
      componentItem: {
        type: Function,
        default: () => Item
      },
      placeholder: String,
      minLen: {type: Number, default: minLen},
      wait: {type: Number, default: wait},
      value: null,
      getLabel: {
        type: Function,
        default: item => item
      },
      items: Array,
      autoSelectOneItem: {type: Boolean, default: true}
    },
    data () {
      return {
        searchText: '',
        showList: false,
        internalItems: this.items || []
      }
    },
    created () {
      minLen = this.minLen
      wait = this.wait
      this.onSelectItem(this.value)
    },
    watch: {
      items (newValue) {
        this.setItems(newValue)
        let item = findItem(this.items, this.searchTexxt, this.autoSelectOneItem)
        if (item) {
          this.onSelectItem(item)
          this.showList = false
        }
      },
      value (newValue) {
        this.onSelectItem(newValue)
      }
    },
    methods: {
      inputChange () {
        this.showList = true
        this.onSelectItem(null)
        callUpdateItems(this.searchText, this.updateItems)
        this.$emit('change', this.searchText)
      },
      updateItems () {
        this.$emit('update-items', this.searchText)
      },
      focus () {
        this.showList = true
      },
      blur () {
        setTimeout(() => this.showList = false, 200)
      },
      onSelectItem (item) {
        if (item) {
          this.internalItems = [item]
          this.searchText = this.getLabel(item)
        } else {
          this.setItems(this.items)
        }
        this.$emit('input', item)
      },
      setItems (items) {
        this.internalItems = items || []
      },
      isSelectedValue (value) {
        return this.internalItems.length && value === this.internalItems[0] === 1
      }
    }
  }
</script>
<style lang="scss" scoped>
  .autocomplete {
    position: relative;
    .autocomplete-list {
      position: absolute;
      .autocomplete-list-item {
        cursor: pointer;
      }
    }
  }
</style>
