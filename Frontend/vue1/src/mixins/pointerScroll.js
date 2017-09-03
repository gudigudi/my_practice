export default {
  watch: {
    typeAheadPointer () {
      this.maybeAdjustScroll()
    }
  },
  methods: {
    maybeAjustScroll () {
      let pixelsToPointerTop = this.pixelsToPointerTop()
      let pixelsToPointerBottom = this.pixelsToPointerBottom()

      if (pixelsToPointerTop <= this.viewport().top) {
        return this.scrollTo(pixelsToPointerTop)
      } else if (pixelsToPointerBottom >= this.viewport().bottom) {
        return this.scrollTo(this.viewport().top + this.pointerHeight())
      }
    },
    pixelsToPointerTop () {
      let pixelsToPointerTop = 0
      if (this.$refs.dropdownMenu) {
        for (let i = 0; i < this.typeAheadPointer; i++) {
          pixelsToPointerTop += this.$refs.dropdownMenu.children[i].offsetHeight
        }
      }
      return pixelsToPointerTop
    },
    pixelsToPointerBottom () {
      return this.pixelsToPointerTop() + this.pointerHeight()
    },
    pointerHeight () {
      let element = this.$refs.dropdownMenu ? this.$refs.dropdownMenu.children[this.typeAheadPointer] : false
      return element ? element.offsetHeight : 0
    },
    viewport () {
      return {
        top: this.$refs.dropdownMenu ? this.$refs.dropdownMenu.scrollTop : 0,
        bottom: this.$refs.dropdownMenu ? this.$refs.dropdownMenu.offsetHeight + this.$refs.dropdownMenu.scrollTop : 0
      }
    },
    scrollTo (position) {
      return this.$refs.dropdownMenu ? this.$refs.dropdownMenu.scrollTop = position : null
    }
  }
}
