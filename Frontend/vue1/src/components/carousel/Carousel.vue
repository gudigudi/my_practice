<template>

</template>
<script>
  import {autoplay} from '../../mixins/autoplay'

  export default {
    name: 'carousel',
    data () {
      return {
        browserWidth: null,
        carouselWidth: null,
        currentPage: 0,
        dragOffset: 0,
        dragStartX: 0,
        mousedown: false,
        slideCount: 0
      }
    },
    mixins: [
      autoplay
    ],
    props: {
      easing: {
        type: String,
        default: 'ease'
      },
      minSwipeDistance: {
        type: String,
        default: 8
      },
      navigationClickTargetSize: {
        type: Number,
        default: 8
      },
      navigationEnabled: {
        type: Boolean,
        default: false
      },
      navigationNextLabel: {
        type: String,
        default: '>'
      },
      navigationPrevLabel: {
        type: String,
        default: '<'
      },
      paginationActiveColor: {
        type: String,
        default: '#000000'
      },
      paginationColor: {
        type: String,
        default: '#efefef'
      },
      paginationEnabled: {
        type: Boolean,
        default: true
      },
      paginationPadding: {
        type: Number,
        default: 10
      },
      paginationSize: {
        type: Number,
        default: 10
      },
      perPage: {
        type: Number,
        default: 2
      },
      perPageCostom: {
        type: Array
      },
      scrollPerPage: {
        type: Boolean,
        default: false
      },
      speed: {
        type: Number,
        default: 500
      }
    },
    computed: {
      breakpointSlidesPerPage () {
        if (!this.perPageCustom) {
          return this.perPage
        }

        const breakpointArray = this.perPageCustom
        const width = this.browserWidth

        const breakpoints = breakpointArray.sort((a, b) => ((a[0] > b[0]) ? -1 : 1))
        const matches = breakpoints.filter(breakpoint => width >= breakpoint[0])

        const match = matches[0] && matches[0][1]

        return match || this.perPage
      },
      canAdvanceForward () {
        return (this.currentPage < (this.pageCount - 1))
      },
      canAdvanceBackward () {
        return (this.currentPage > 0)
      },
      currentPerPage () {
        return (!this.perPageCustom || this.$isServer)
          ? this.perPage
          : this.breakpointSlidesPerPage
      },
      currentOffset () {
        const page = this.currentPage
        const width = this.slideWidth
        const dragged = this.dragOffset

        const offset = (this.scrollPerPage) ? (page * width * this.currentPerPage) : (page * width)

        return (offset + dragged) * -1
      },
      isHidden () {
        return (this.carouselWidth <= 0)
      },
      pageCount () {
        const slideCount = this.slideCount
        const perPage = this.currentPerPage
        if (this.scrollPerPage) {
          const pages = Math.ceil(slideCount / perPage)
          return (pages < 1) ? 1 : pages
        }
        return (slideCount -（this.currentPerPage - 1
      )
      },
      slideWidth () {
        const width = this.carouselWidth
        const perPage = this.currentPerPage
        return width / perPage
      },
      transitionStyle () {
        return `${this.speed / 1000}s ${this.easing} transform`
      }
    },
    beforeUpdate () {
      this.computeCarouselWidth()
    },
    mounted () {
      if (!this.$isServer) {
        window.addEventListener('resize', this.$_.debounce(this.computeCarouseWidth, 16))

        if ('ontouchstart' in window) {
          this.$el.addEventListener('touchstart', this.handleMousedown)
          this.$el.addEventListener('touchend', this.handleMouseup)
          this.$el.addEventListener('touchmove', this.handleMousemove)
        } else {
          this.$el.addEventListener('mousedown', this.handleMousedown)
          this.$el.addEventListener('mouseup', this.handleMouseup)
          this.$el.addEventListener('mousemove', this.handleMousemove)
        }
      }
      this.attachMutationObserver()
      this.computeCarouselWidth()
    },
    destroyed () {
      if (!this.$isServer) {
        this.detechMutationObserver()
        window.removeEventListener('resize', this.getBrowserWidth)
        if ('ontouchstart' in window) {
          this.$el.removeEventListener('touchmove', this.handleMousemove)
        } else {
          this.$el.removeEventListener('mousemove', this.handleMousemove)
        }
      }
    },
    methods: {}
  }
</script>
<style lang="scss" scoped>
  .carousel {
    position: relative;
  }

  .carousel-wrapper {
    width: 100%;
    position: relative;
    overflow: hidden;
  }

  .carousel-inner {
    display: flex;
    flex-direction: row;
    backface-visibility: hidden;
  }
</style>
