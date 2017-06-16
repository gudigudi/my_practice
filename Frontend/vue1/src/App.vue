<template>
  <div id="app">
    <layouts></layouts>
    <!--<router-view></router-view>-->
  </div>
</template>
<script>
  import Layouts from './layouts/layouts'
  export default {
    name: 'app',
    components: {
      Layouts
    },
    data () {
      return {}
    },
    methods: {}
  }
</script>
<style lang="scss">
</style>
