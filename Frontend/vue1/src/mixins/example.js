let cache = null

export default {
  methods: {
    sendEnterPage () {
      console.log('enter page', cache)
      cache = this.$route
    },
    sendLeavePage () {
      console.log('leave page', cache)
    }
  },
  mounted () {
    this.sendEnterPage()
  },
  destroyed () {
    this.sendLeavePage()
  }
}
