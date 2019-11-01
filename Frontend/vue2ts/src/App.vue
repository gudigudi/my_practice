<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>|
      <router-link to="/button-demo">Button Demo</router-link>|
      <router-link to="/container-demo">Container Demo</router-link>|
      <router-link to="/form-demo">Form Demo</router-link>|
      <router-link to="/layout-demo">Layout Demo</router-link>|
    </div>
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

<style scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
  font-size: 32px;
  line-height: 1.2;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #fc9153;
}
</style>
