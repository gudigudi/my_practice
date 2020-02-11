<template>
  <div class="hello">
    <h1>
      <span>{{ msg }}</span>
    </h1>
    <p>count:{{count}}</p>
    <button @click="setCount(count+1)">+1</button>
    <button @click="setCount(count-1)">-1</button>
  </div>
</template>

<script lang="ts">
import { Component, Prop, PropSync, Vue } from "vue-property-decorator";

const store = Vue.observable({ count: 0 });

@Component
export default class HelloWorld extends Vue {
  @Prop(String) public readonly msg: string | undefined;

  get count() {
    return store.count;
  }

  public setCount(count: number) {
    store.count = count;
  }
}
</script>

<style lang="scss" scoped>
.hello {
  text-align: center;
  padding: 20px;
  font-size: 32px;
  color: #666;
  line-height: 56px;
  h1 {
    color: #333;
    line-height: 1.15;
    font-size: 64px;
    margin-bottom: 32px;
    span {
      position: relative;
      &::after {
        content: "";
        position: absolute;
        z-index: -1;
        left: 0;
        bottom: 8px;
        width: 100%;
        height: 20px;
        background: #ecf6ff;
      }
    }
  }
}
</style>
