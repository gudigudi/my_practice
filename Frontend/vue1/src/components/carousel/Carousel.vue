<template>
  <div class="carousel">
    <div class="carousel-wrapper">
      <div class="carousel-inner" :style="`
      transform: translateX(${currentOffset}px);
      transition: ${transitionStyle}
      flex-basis: ${slideWidth}px;
      visibility: ${slideWidth ? 'visible' : 'hidden'}
`">
        <slot></slot>
      </div>
    </div>
    <pagination v-if="paginationEnabled && pageCount > 0"></pagination>
    <navigation v-if="navigationEnabled" :clickTargetSize="navigationClickTargetSize"
                :nextLabel="navigationNextLabel" :prevLabel="navigationPrevLabel"></navigation>
  </div>
</template>
<script>
  import {autoplay} from '../../mixins/autoplay'
  import Pagination from './Pagination.vue'
  import Navigation from './Navigation.vue'

  export default {
    name: 'carousel',
    components: {
      Navigation,
      Pagination
    },
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
        type: Number,
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
        return (slideCount - (this.currentPerPage - 1))
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
        window.addEventListener('resize', this.$_.debounce(this.computeCarouselWidth, 16))

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
    methods: {
      advancePage (direction) {
        if (direction && direction === 'backward' && this.canAdvanceBackward) {
          this.goToPage(this.currentPage - 1)
        } else if ((!direction || (direction && direction !== 'backward')) && this.canAdvanceForward) {
          this.goToPage(this.currentPage + 1)
        }
      },
      attachMutationObserver () {
        if (MutationObserver) {
          const config = {attributes: true, data: true}
          this.mutationObserver = new window.MutationObserver(() => {
            this.vm.$nextTick(() => {
              this.computeCarouselWidth()
            })
          })
          if (this.$parent.$el) {
            this.mutationObserver.observe(this.$parent.$el, config)
          }
        }
      },
      detachMutationObserver () {
        if (this.mutationObserver) {
          this.mutationObserver.disconnect()
        }
      },
      getBrowserWidth () {
        this.browserWidth = window.innerWidth
        return this.browserWidth
      },
      getCarouselWidth () {
        this.carouselWidth = (this.$el && this.$el.clientWidth) || 0
        return this.carouselWidth
      },
      getSlideCount () {
        this.slideCount = (
          this.$slots &&
          this.$slots.default &&
          this.$slots.default.filter(slot => slot.tag && slot.tag.indexOf('slide') > -1).length) || 0
      },
      goToPage (page) {
        if ((page >= 0) && (page <= this.pageCount)) {
          this.currentPage = page
          this.$emit('pageChange', this.currentPage)
        }
      },
      handleMousedown (e) {
        if (!e.touches) {
          e.preventDefault()
        }
        this.mousedown = true
        this.dragStartX = 'ontouchstart' in window ? e.touches[0].clientX : e.clientX
      },
      handleMouseup () {
        this.mousedown = false
        this.dragOffset = 0
      },
      handleMousemove (e) {
        if (!this.mousedown) {
          return
        }

        const eventPosX = 'ontouchstart' in window ? e.touches[0].clientX : e.clientX
        const deltaX = this.dragStartX - eventPosX

        this.dragOffset = deltaX

        if (this.dragOffset > this.minSwipeDistance) {
          this.handleMouseup()
          this.advancePage()
        } else if (this.dragOffset < -this.minSwipeDistance) {
          this.handleMouseup()
          this.advancePage('backward')
        }
      },
      computeCarouselWidth () {
        this.getSlideCount()
        this.getBrowserWidth()
        this.getCarouselWidth()
        this.setCurrentPageInBounds()
      },
      setCurrentPageInBounds () {
        if (!this.canAdvanceForward) {
          const setPage = (this.pageCount - 1)
          this.currentPage = (setPage >= 0) ? setPage : 0
        }
      }
    }
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
