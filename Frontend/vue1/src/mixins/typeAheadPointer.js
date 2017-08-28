export default {
  data () {
    return {
      typeAheadPinter: -1
    }
  },
  watch: {
    filteredOptions () {
      this.typeAheadPinter = 0
    }
  },
  methods: {
    typeAheadUp () {
      if (this.typeAheadPinter > 0) {
        this.typeAheadPinter--
        if (this.maybeAdjustScroll) {
          this.maybeAdjustScroll()
        }
      }
    },
    typeAheadDown () {
      if (this.typeAheadPinter < this.filteredOptions.length - 1) {
        this.typeAheadPinter++
        if (this.maybeAdjustScroll) {
          this.maybeAdjustScroll()
        }
      }
    },
    typeAheadSelect () {
      if (this.filteredOptions[this.typeAheadPinter]) {
        this.select(this.filteredOptions[this.typeAheadPinter])
      } else if (this.taggable && this.search.length) {
        this.select(this.search)
      }

      if (this.clearSearchOnSelect) {
        this.search = ''
      }
    }
  }
}
