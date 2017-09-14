<template>
  <section class="container">
    <section class="menu">
      <my-menu></my-menu>
    </section>
    <section class="content-container"></section>
  </section>
</template>

<script>
  import Menu from '../components/Menu'

  export default {
    components: {Menu},
    name: 'layouts'
  }
</script>

<style lang="scss">
  @import "../style/layouts";
</style>
