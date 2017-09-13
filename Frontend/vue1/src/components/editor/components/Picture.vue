<template>
  <div>
    <a href="javascript:;" :title="lang.title" :class="{'disabled':disabled}" @click="toggle">
      <i class="icon-file-image-o"></i>
    </a>
  </div>
</template>
<script>
  export default {
    name: 'my-picture',
    data () {
      return {
        lang: this.$store.state.lang.picture
      }
    },
    computed: {
      disabled () {
        return this.$store.state.toolbarStates.picture.disabled
      }
    },
    methods: {
      updatePopupDisplay (current) {
        this.$store.dispatch('updatePopupDisplay', current)
      },
      toggle () {
        !this.disabled && this.updatePopupDisplay('picture')
      }
    }
  }
</script>
