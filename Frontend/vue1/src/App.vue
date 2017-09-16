<template>
  <div id="app">
    <label for="locale">locale</label>
    <select v-model="locale" id="locale">
      <option>en</option>
      <option>ch</option>
    </select>
    <p>{{ $t('hello') }}</p>
    <h1>{{ $t('lala') }}</h1>
    <router-view></router-view>
  </div>
</template>
<script>
  export default {
    name: 'app',
    data () {
      return {
        locale: 'en'
      }
    },
    watch: {
      locale (val) {
        this.$i18n.locale = val
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
