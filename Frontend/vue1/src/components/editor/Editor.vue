<template>
  <div class="editor" :class="classList" :id="id">
    <template v-for="item in comps">
      <component :is="item"></component>
    </template>
  </div>
</template>
<script>
  import Toolbar from './components/Toolbar.vue'
  import Iframe from './components/Iframe.vue'
  import Editable from './components/Editable.vue'
  import Sourcecode from './components/Sourcecode.vue'
  import PictureDialog from './components/Picture-Dialog.vue'

  export default {
    name: 'editor',
    components: {
      Toolbar,
      Iframe,
      Editable,
      Sourcecode,
      PictureDialog
    },
    methods: {
      setContent (content) {
        this.$store.dispatch('updateContent', content)
      },
      getContent (content) {
        return this.$store.state.content
      }
    }
  }
</script>

