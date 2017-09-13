<template>
  <div>
    <a href="javascript:;" class="select" :class="{'disabled':disabled}" @click="toggle">
      <span>{{val || fontSize[0]}}</span>
      <i :class="{'triangle-down':!display, 'triangle-up': display}"></i>
    </a>
    <div class="toolbar-dropdown select-dropdown font-size" v-show="display">
      <ul>
        <li v-for="size in fontSize" @click="clickHandler(size)">
          <a href="javascript:;">{{size}}</a>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'font-size',
    data () {
      return {
        fontSize: this.$store.state.config.fontSize,
        val: ''
      }
    },
    computed: {
      disabled () {
        return this.$store.state.toolbarStates.fontSize.disabled
      },
      display () {
        return this.$store.state.toolbarStates.fontSize.showPopup
      }
    },
    methods: {
      updatePopupDisplay (current) {
        this.$store.dispatch('updatePopupDisplay', current)
      },
      toggle () {
        !this.disabled && this.updatePopupDisplay('fontSize')
      },
      clickHandler (size) {
        this.val = size
        this.$store.dispatch('execCommand', {name: 'fontSize', value: size})
        this.updatePopupDisplay()
      }
    }
  }
</script>
<style lang="scss" scoped>
  .font-size {
    li {
      padding: 6px;
      border-bottom: 1px solid #ddd;
      &:last-child {
        border-bottom: none;
      }
      &:hover {
        background-color: #d5e1f2;
        border-color: #a3bde3;
      }
    }
  }
</style>
