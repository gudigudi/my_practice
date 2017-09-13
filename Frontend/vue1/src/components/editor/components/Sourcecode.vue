<template>
  <div class="code" v-show="currentView==='sourcecode'">
    <textarea ref="codemirror">{{content}}</textarea>
  </div>
</template>
<script>
  export default {
    name: 'sourcecode',
    data () {
      return {
        editor: null
      }
    },
    computed: {
      currentView () {
        return this.$store.state.currentView
      },
      content () {
        return this.$store.state.content
      }
    },
    watch: {
      currentView (val) {
        if (val === 'design') {
          this.updateContent(this.$refs.codemirror.value)
        }
      }
    },
    methods: {
      updateContent (content) {
        this.$store.dispatch('updateContent', content)
      }
    }
  }
</script>
