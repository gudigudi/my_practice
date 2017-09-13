<template>
  <div>
    <a href="javascript:;" :title="lang.title" @click="clickHandler">
      <i class="icon-code"></i>
    </a>
  </div>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    name: 'switch-view',
    data () {
      return {
        editor: null,
        lang: this.$store.state.lang.switchview
      }
    },
    methods: Object.assign({}, mapActions([
      'switchview',
      'updateToolbarDisabledStates',
      'updatePopupDisplay'
    ]), {
      clickHandler () {
        this.switchview()
        this.updateToolbarDisabledStates()
        this.updatePopupDisplay()
      }
    })
  }
</script>
