<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>
<script>
  export default {
    name: 'app',
    data () {
      return {
        data: 'lala'
      }
    },
    methods: {
      logic () {
        console.log('do the logic about hello page')
      }
    }
  }
</script>
<style lang="scss">
</style>
