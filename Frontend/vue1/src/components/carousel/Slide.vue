<template>
  <div class="carousel-slide">
    <slot></slot>
  </div>
</template>
<script>
  export default {
    name: 'slide',
    data () {
      return {
        width: null
      }
    }
  }
</script>
<style lang="scss" scoped>
  .carousel-slide {
    flex-basis: inherit;
    flex-grow: 0;
    flex-shrink: 0;
    user-select: none;
  }
</style>
