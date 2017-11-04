<template>
  <div class="tips" v-show="show" transition="fade">

    <div class="tips-close" @click="closeTips">x</div>

    <div class="tips-header">
      <slot name="header">
        <p class="title">{{tips.title}}</p>
      </slot>
    </div>

    <div class="tips-body">
      <slot name="body">
        <p class="notice">{{tips.text}}</p>
      </slot>
    </div>

    <div class="tips-footer">
      <a href="javascript:;" v-if="tips.showBtn" @click="yes">{{tips.btnText}}</a>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'tips',
    data () {
      return {
        show: false
      }
    },
    computed: {
      tips: {
        get () {
          let tips = this.tipsOptions || {}
          tips = {
            title: tips.title || '提示',
            text: tips.text || '',
            showBtn: tips.showBtn || true,
            btnText: tips.btn || '确定'
          }
          return tips
        }
      }
    },
    methods: {
      showTips () {
        let self = this
        self.show = true

        setTimeout(() => {
        }, 2000)
      },
      closeTips () {
        this.show = false
      },
      yes () {
        this.show = false
        this.$emit('yes', {name: 'wenzi', age: 36})
      }
    }
  }
</script>
<style lang="scss" scoped>
  .tips {
    position: fixed;
    left: 10px;
    bottom: 10px;
    z-index: 1001;
    -webkit-overflow-scrolling: touch;
    max-width: 690px;
    width: 260px;
    padding: 10px;
    background: #fff;
    box-shadow: 0 0 10px #888;
    border-radius: 4px;

    .tips-close {
      position: absolute;
      top: 0;
      right: 0;
      width: 20px;
      height: 20px;
      line-height: 20px;
      text-align: center;
    }
    .tips-header {
      text-align: center;
      font-size: 25px;
    }
  }
</style>
