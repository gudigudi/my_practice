<template>
  <div>
    <a href="javascript:;" :title="lang.addLink" :class="{'disabled':linkState,'active':display}" @click="toggle">
      <i class="icon-link"></i>
    </a>
    <a href="javascript:;" :title="lang.cancelLink" :class="{'disabled':unLinkState}" @click="unLinkHandler">
      <i class="icon-unlink"></i>
    </a>
    <div class="popover link-popover" v-show="display">
      <div class="pop-arrow"></div>
      <div class="pop-header">{{lang.addLink}}</div>
      <div class="pop-body">
        <div class="input-box">
          <input type="text" class="input" v-model="linkVal">
          <button class="btn" @click="linkHandler">{{lang.ok}}</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'link',
    data () {
      return {
        width: 240,
        height: 90,
        linkVal: '',
        lang: this.$store.state.lang.link
      }
    },
    computed: {
      linkState () {
        return this.$store.state.toolbarStates.link.disabled
      },
      unLinkState () {
        return this.$store.state.toolbarStates.unlink.disabled
      },
      display () {
        return this.$store.state.toolbarStates.link.showPopup
      }
    },
    methods: {
      updatePopupDisplay (current) {
        this.$store.dispatch('updatePopupDisplay', current)
      },
      execCommand (data) {
        this.$store.dispatch('execCommand', data)
      },
      toggle () {
        !this.disabled && this.updatePopupDisplay('link')
      },
      checkValid () {
        let link = this.linkVal
        if (link.indexOf('http://' === -1)) {
          link = 'http://' + link
        }
        return link
      },
      linkHandler () {
        let link = this.checkValid()
        this.execCommand({name: 'CreateLink', value: link})
        this.updatePopupDisplay()
      },
      unLinkHandler () {
        this.execCommand({name: 'Unlink', value: null})
      }
    }
  }
</script>
<style lang="scss" scoped>
  .link-popover {
    width: 240px;
    height: 90px;
    transform: translateX(-50%);
    margin-left: 25%;
  }
</style>
