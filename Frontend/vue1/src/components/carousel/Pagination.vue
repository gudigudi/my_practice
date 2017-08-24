<template>
  <div v-show="parentContainer.pageCount>1" class="carousel-pagination">
    <div class="carousel-dot-container">
      <div class="carousel-dot"
           :class="{'carousel-dot--active':(index===parentContainer.currentPage)}"
           :style="`
           margin-top: ${parentContainer.paginationPadding *2}px;
           padding: ${parentContainer.paginationPadding}px;
           `"
           @click="parentContainer.goToPage(index)"
           v-for="(page,index) in parentContainer.pageCount">
        <div class="carousel-dot-inner"
             :style="`
        width: ${parentContainer.paginationSize}px;
        height: ${parentContainer.paginationSize}px;
        background: ${(index===parentContainer.currentPage) ? parentContainer.paginationActiveColor : parentContainer.paginationColor};
        `">
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'pagination',
    data () {
      return {
        parentContainer: this.$parent
      }
    }
  }
</script>
<style lang="scss" scoped>
  .carousel-pagination {
    width: 100%;
    float: left;
    text-align: center;
  }

  .carousel-dot-container {
    display: inline-block;
    margin: 0 auto;
  }

  .carousel-dot {
    float: left;
    cursor: pointer;
  }

  .carousel-dot-inner {
    border-radius: 100%;
  }
</style>
