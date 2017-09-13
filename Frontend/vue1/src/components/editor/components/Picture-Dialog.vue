<template>
  <div class="dialog picture-dialog" v-show="display" @click="hideDialog"
       :style="{width: ctnW+'px', height: ctnH+'px'}">
    <div class="wrap" @click.stop>
      <div class="dialog-header">{{lang.title}}
        <a href="javascript:;" class="close" @click="hideDialog">&times;</a>
      </div>
      <div class="dialog-body">
        <form ref="form">
          <input type="file" name="image" ref="file">
        </form>
      </div>
      <div class="dialog-footer">
        <div class="btn-box">
          <button class="btn" @click="hideDialog">{{lang.cancel}}</button>
          <button class="btn" @click="certainHandler">{{lang.ok}}</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'picture-dialog',
    data () {
      return {
        ctnW: window.innerWidth,
        ctnH: window.innerHeight,
        lang: this.$store.state.lang.pictureDialog
      }
    },
    computed: {
      display () {
        return this.$store.state.toolbarStates.picture.showPopup
      }
    },
    mounted () {
      window.addEventListener('resize', function () {
        this.ctnW = window.innerWidth
        this.ctnH = window.innerHeight
      }.bind(this), false)
    },
    methods: {
      updatePopupDisplay (current) {
        this.$store.dispatch('updatePopupDisplay', current)
      },
      hideDialog () {
        this.updatePopupDisplay()
      },
      certainHandler (event) {
        let url = ''
        let obj = this.$refs.file
        let form = this.$refs.form
        let fileuploadUrl = this.$store.state.config.fileuploadUrl
        if (navigator.userAgent.indexOf('MSIE') >= 1) {
          url = obj.value
        } else {
          if (obj.files.length !== 0 && obj.files.item(0).type.indexOf('image') !== -1) {
            url = window.URL.createObjectURL(obj.files.item(0))
          }
        }
        if (url) {
          if (fileuploadUrl) {
            let formData = new FormData(form)
            let xhr = new XMLHttpRequest();
            xhr.open('POST', fileuploadUrl)
            xhr.send(formData)
            xhr.onload = function () {
              this.$store.dispatch('execCommand', {name: 'insertHTML', value: `<img src="${xhr.responseText}">`})
              this.hideDialog()
            }.bind(this)
            xhr.onerror = (err) => {
              window.alert(err)
            }
          } else {
            this.$store.dispatch('execCommand', {name: 'insertHTML', value: `<img src="${url}">`})
            this.hideDialog()
          }
        } else {
          window.alert(this.lang.invalidFile)
        }
      }
    }
  }
</script>
<style lang="scss" scoped>
  .picture-dialog .wrap {
    width: 500px;
  }
</style>
