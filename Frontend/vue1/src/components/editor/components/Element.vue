<template>
  <div>
    <a href="javascript:;" class="select" :class="{'disabled':disabled}" @click="toggle">
      <span>{{val || arr[0]}}</span>
      <i :class="{'triangle-down': !display,'triangle-up':display}"></i>
    </a>
    <div class="toolbar-dropdown select-dropdown format-block" @click="selectItem" v-show="display">
      <a href="javascript:;" v-for="item in arr">{{item}}</a>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'element',
    data () {
      return {
        arr: ['p', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6'],
        val: ''
      }
    },
    computed: {
      disabled () {
        return this.$store.state.toolbarStates.element.disabled
      },
      display () {
        return this.$store.state.toolbarStates.element.showPopup
      }
    },
    methods: {
      updatePopupDisplay (current) {
        this.$store.dispatch('updatePopupDisplay', current)
      },
      toggle () {
        !this.disabled && this.updatePopupDisplay('element')
      },
      selectItem (event) {
        let tagName = event.target.innerHTML.trim()
        this.val = tagName
        this.$store.dispatch('execCommand', {name: 'formatBlock', value: tagName})
        this.updatePopupDisplay()
      }
    }
  }
</script>
<style lang="scss" scoped>
  .format-block {
    a {
      display: block;
      padding: 6px;
      border-bottom: 1px solid #ddd;
      &:last-child {
        border-bottom: none;
      }
      &:hover {
        background: #d5e1f2;
        border-color: #a3bde3;
      }
    }
  }
</style>
