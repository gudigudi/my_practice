<template>
  <div class="footer">
    <p class="info">{{ info }}</p>
    <a-button @click="modify">Modify</a-button>
  </div>
</template>

<script lang="ts">
import { Component, Prop, PropSync, Vue, Emit } from "vue-property-decorator";
import { Button } from "ant-design-vue";

@Component({ components: { "a-button": Button } })
export default class MyFooter extends Vue {
  @Prop(String)
  public readonly msg: string | undefined;

  public info: string = "Click to modify";

  @Emit()
  modify() {
    this.info = "Modified by click";
  }
}
</script>

<style lang="scss">
</style>
