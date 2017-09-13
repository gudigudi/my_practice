<template>
  <div>
    <a href="javascript:;" class="select font-select" :class="{'disabled': disabled}" @click="toggle">
      <span>{{val || fonts[0].abbr || fonts[0].val}}</span>
      <i :class="{'triangle-down': !display,'triangle-up':display}"></i>
    </a>
    <div class="toolbar-dropdown select-dropdown font-name" v-show="display" :style="{left:left +'px',top:top+'px'}">
      <ul>
        <li v-for="font in fonts" @click="clickHandler(font)">
          <a href="javascript:;" :style="{fontFamily: font.val + ', sans-serif'}">{{font.abbr || font.val}}</a>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'font-name',
    data () {
      return {
        fonts: this.$store.state.config.fontName,
        val: ''
      }
    },
    computed: {
      disabled () {
        return this.$store.state.toolbarStates.fontName.disabled
      },
      display () {
        return this.$store.state.toolbarStates.fontName.showPopup
      }
    },
    methods: {
      updatePopupDisplay (current) {
        this.$store.dispatch('updatePopupDisplay', current)
      },
      toggle () {
        !this.disabled && this.updatePopupDisplay('fontName')
      },
      clickHandler (font) {
        this.val = font.abbr || font.val
        this.$store.dispatch('execCommand', {name: 'fontName', value: font.val + ', sans-serif'})
        this.updatePopupDisplay()
      }
    }
  }
</script>
<style lang="scss" scoped>
  .font-select {
    width: 100px;
    span {
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
  }

  .font-name {
    li {
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
