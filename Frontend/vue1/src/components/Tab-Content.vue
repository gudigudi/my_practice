<template>
  <section class="tab-container" role="tabpanel"
           :id="hash" :aria-hidden="!active">
    <slot></slot>
  </section>
</template>
<script>
  export default {
    name: 'tab-content',
    props: {
      title: {
        type: String,
        default: ''
      },
      icon: {
        type: String,
        default: ''
      },
      beforeChange: {
        type: Function
      },
      id: String,
      route: {
        type: [String, Object]
      },
      transitionName: String,
      transitionMode: String
    },
    computed: {
      isValidParent () {
        return this.$parent.$options.name === 'tab'
      },
      hash () {
        return `#${this.id}`
      }
    },
    data () {
      return {
        active: false,
        validationError: null
      }
    }
  }
</script>
<style>

</style>
