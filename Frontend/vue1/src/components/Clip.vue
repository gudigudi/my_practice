<template>
  <div :class="uploaderClass">
    <slot name="clip-uploader-action" :dragging="dragCounter>0"></slot>
    <slot name="clip-uploader-body" :files="files"></slot>
    <div ref="clip-preview-template" class="clip-preview-template" style="display:none;">
      <div></div>
    </div>
  </div>
</template>
<script>

  class File {
    constructor (file) {
      this._file = file
      this.status = file.status
      this.name = file.name
      this.width = file.width
      this.height = file.height
      this.bytesSent = file.upload.bytesSent || 0
      this.progress = file.upload.progress || 0
      this.total = file.upload.total
      this.type = file.type
      this.size = file.size
      this.dataUrl = ''
      this.xhrResponse = {}
      this.customAttributes = {}
      this.errorMessage = ''
    }

    updateDataUrl (dataUrl) {
      this.dataUrl = dataUrl
    }

    updateStatus (status) {
      this.status = status
    }

    updateProgress (progress) {
      this.progress = progress
    }

    updateBytesSent (bytesSent) {
      this.bytesSent = bytesSent
    }

    updateXhrResponse (response) {
      this.xhrResponse = response
    }

    updateErrorMessage (errorMessage) {
      this.errorMessage = errorMessage
    }

    addAttribute (key, value) {
      this.customAttributes[key] = value
    }
  }

  export default {
    name: 'clip',
    props: {
      uploaderClass: String,
      options: {
        type: Object,
        default: () => {
        }
      },
      onAddedFile: {
        type: Function,
        default: function () {
          return function () {
          }
        }
      },
      onRemovedFile: {
        type: Function,
        default: function () {
          return function () {
          }
        }
      },
      onSending: {
        type: Function,
        default: function () {
          return function () {
          }
        }
      },
      onDragEnter: {
        type: Function,
        default: function () {
          return function () {
          }
        }
      },
      onDragLeave: {
        type: Function,
        default: function () {
          return function () {

          }
        }
      },
      onDrop: {
        type: Function,
        default: function () {
          return function () {
          }
        }
      },
      onTotalProgress: {
        type: Function,
        default: function () {
          return function () {
          }
        }
      },
      onQueueComplete: {
        type: Function,
        default: function () {
          return function () {

          }
        }
      },
      onMaxFiles: {
        type: Function,
        default: function () {
          return function () {

          }
        }
      },
      onInit: {
        type: Function,
        default: function () {
          return function () {
          }
        }
      },
      onComplete: {
        type: Function,
        default: function () {
          return function () {

          }
        }
      },
    },
    data () {
      return {
        files: [],
        dragCounter: 0,
        uploader: null
      }
    },
    mounted () {
      const options = this.options
      const accept = options.accept || function (file, done) {
        done()
      }

      options.previewTemplate = this.$refs['clip-preview-template'].innerHTML
      options.accept = ({blobId}, done) => {
        accpet(this.getFile(blobId), done)
      }

      if (typeof (options.maxFiles) !== 'undefined' && options.maxFiles instanceof Object === true) {
        const {limit, message} = options.maxFiles
        options.maxFiles = limit
        options.dictMaxFilesExceeded = this.cleanupMessage(message)
      }

      if (typeof (options.maxFilesize) !== 'undefined' && options.maxFilesize instanceof Object === true) {
        const {limit, message} = options.maxFilesize
        options.maxFilesize = limit
        options.dictFileTooBig = this.cleanupMessage(message)
      }

      if (typeof options.acceptedFiles !== 'undefined' && options.acceptedFiles !== null) {
        switch (Object.prototype.toString.call(options.acceptedFiles)) {
          case '[object String]':
            break
          case '[object Object]':
            const {extensions, message} = options.acceptedFiles

        }
      }
    }
  }
</script>
<style></style>
