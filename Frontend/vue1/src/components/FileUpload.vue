<template>
  <input type="file" name="fileUpload" @change="onFileChange">
</template>
<script>
  export default {
    name: 'fileupload',
    props: {
      target: String,
      action: {
        type: String,
        default: 'POST'
      }
    },
    data () {
      return {
        file: null
      }
    },
    methods: {
      uploadProgress (event) {
        if (event.lengthComputable) {
          let percentComplete = Math.round(event.loaded * 100 / event.total)
          this.$emit('progress', percentComplete)
        } else {
          this.$emit('progress', false)
        }
      },
      onFileChange (event) {
        if (!this.target || this.target === '') {
          console.log('Please provide the target url')
        } else if (!this.action || this.action === '') {
          console.log('Please provide file upload action (POST/PUT)')
        } else if (this.action !== 'POST' && this.action !== 'PUT') {
          console.log('File upload component only allows POST and PUT Actions')
        } else {
          let files = event.target.files || event.dataTransfer.files

          if (!files.length) {
            return
          }

          this.file = files[0]
          let formData = new FormData()
          formData.append('file', this.file)

          var xhr = new XMLHttpRequest()
          xhr.open(this.action, this.target)

          xhr.onloadstart = function (e) {
            this.$emit('start', e)
          }
          xhr.onloadend = function (e) {
            this.$emit('finish', e)
          }
          xhr.upload.onprogress = this.uploadProgress
          xhr.send(formData)
        }
      }
    }
  }
</script>
