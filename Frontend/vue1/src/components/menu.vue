<template>
  <div class="list-todo">
    <a class="list-todo activeListClass list">
      <span class="icon-lock"></span>
      <span class="count-list"></span>
      星期一
    </a>
    <a class="link-list-new">
      <span class="icon-plus"></span>
      新增
    </a>
  </div>
</template>
<script>
  export default{
    name: 'EuMenu'
  }
</script>
<style lang="scss">
  @import "../scss/menu.scss";
</style>
