export default {
  props: {
    loading: {
      type: Boolean,
      default: false
    },
    onSearch: {
      type: Function,
      default: (search, loading) => {
      }
    }
  },
  data () {
    return {
      mutableLoading: false
    }
  },
  watch: {
    search () {
      if (this.search.length > 0) {
        this.onSearch(this.search, this.toggleLoading)
        this.$emit('search', this.search, this.toggleLoading)
      }
    },
    loading (val) {
      this.mutableLoading = val
    }
  },
  methods: {
    toggleLoading (toggle = null) {
      if (toggle === null) {
        return this.mutableLoading = !this.mutableLoading
      }
      return this.mutableLoading = toggle
    }
  }
}
