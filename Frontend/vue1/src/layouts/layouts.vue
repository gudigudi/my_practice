<template>
  <section class="container">
    <section class="menu">
      <eu-menu></eu-menu>
    </section>
    <section class="content-container"></section>
  </section>
</template>

<script>
  import EuMenu from '../components/menu'
  export default {
    components: {EuMenu},
    name: 'layouts'
  }
</script>

<style lang="scss">
  @import "../scss/layouts";
</style>
