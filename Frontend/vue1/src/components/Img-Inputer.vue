<template>

</template>
<script>
  export default {
    name: 'img-inputer',
    props: {
      type: {
        type: String,
        default: 'img'
      },
      accept: {
        type: String,
        default: 'image/*,video/*;'
      },
      capture: {
        type: Boolean,
        default: true
      },
      id: {
        type: String,
        default: ''
      },
      onChange: {
        type: Function,
        default: null
      },
      readonly: {
        type: Boolean,
        default: false
      },
      readonlyTipText: {
        type: String,
        default: 'unchangable'
      },
      bottomText: {
        type: String,
        default: 'click or drop to change'
      },
      placeholder: {
        type: String,
        default: 'click or drop to choose image'
      },
      value: {
        default: undefined
      },
      icon: {
        type: String,
        default: ''
      },
      customerIcon: {
        type: String,
        default: ''
      },
      maxSize: {
        type: Number,
        default: 5120
      },
      size: {
        type: String,
        default: ''
      },
      imgSrc: {
        type: String,
        default: ''
      },
      nhe: {
        type: Boolean,
        default: false
      },
      noMask: {
        type: Boolean,
        default: false
      },
      theme: {
        type: String,
        default: ''
      },
      name: {
        type: String,
        default: 'file'
      }
    },
    data () {
      return {
        inputId: '',
        file: [],
        dataUrl: '',
        fileName: '',
        errText: ''
      }
    },
    computed: {}

  }
</script>
<style lang="scss" scoped>

</style>
