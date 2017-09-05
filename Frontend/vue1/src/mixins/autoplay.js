const autoplay = {
  props: {
    autoplay: {
      type: Boolean,
      default: false
    },
    autoplayTimeout: {
      type: Number,
      default: 2000
    },
    autoplayHoverPause: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      autoplayInterval: null
    }
  },
  mounted () {
    if (!this.$isServer && this.autoplayHoverPause) {
      this.$el.addEventListener('mouseenter', this.pauseAutoplay)
      this.$el.addEventListener('mouseleave', this.startAutoplay)
    }
    this.startAutoplay()
  },
  destroyed () {
    if (!this.$isServer) {
      this.$el.removeEventListener('mouseenter', this.pauseAutoplay)
      this.$el.removeEventListener('mouseleave', this.pauseAutoplay)
    }
  },
  methods: {
    pauseAutoplay () {
      if (this.autoplayInterval) {
        this.autoplayInterval = clearInterval(this.autoplayInterval)
      }
    },
    startAutoplay () {
      if (this.autoplay) {
        this.autoplayInterval = setInterval(this.advancePage, this.autoplayTimeout)
      }
    }
  }
}
export {autoplay}
