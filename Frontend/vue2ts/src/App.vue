<template>
  <div id="app">
    <router-view />
    <p>use v-lazy-container work with raw HTML</p>
    <div v-lazy-container="{ selector: 'img' }">
      <img data-src="//domain.com/img1.jpg" />
      <img data-src="//domain.com/img2.jpg" />
      <img data-src="//domain.com/img3.jpg" />
    </div>
    <p>custom error and loading placeholder image</p>
    <div v-lazy-container="{ selector: 'img', error: 'xxx.jpg', loading: 'xxx.jpg' }">
      <img data-src="//domain.com/img1.jpg" />
      <img data-src="//domain.com/img2.jpg" />
      <img data-src="//domain.com/img3.jpg" />
    </div>
    <div v-lazy-container="{ selector: 'img' }">
      <img data-src="//domain.com/img1.jpg" data-error="xxx.jpg" />
      <img data-src="//domain.com/img2.jpg" data-loading="xxx.jpg" />
      <img data-src="//domain.com/img3.jpg" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, PropSync, Vue } from "vue-property-decorator";

@Component
export default class App extends Vue {}
</script>
<style lang="scss" scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>