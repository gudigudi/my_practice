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
<style lang="scss" scoped>
  .editor {

    height: 100%;
    display: flex;
    flex-direction: column;
    border: 1px solid #ddd;

    h1, h2, h3, h4, h5, p {
      margin: 0;
      padding: 0;
    }
    ul, ol {
      list-style: none;
      margin: 0;
      padding: 0;
    }
    a {
      color: black;
      text-decoration: none;
    }
    hr {
      margin: 10px 0;
    }
    label {
      font-weight: normal;
    }
    html {
      font-size: 16px;
    }
    img {
      max-width: 100%;
    }

    &[contenteditable="true"] {
      ul {
        list-style-type: disc;
        margin-left: 1em;
      }
      ol {
        list-style-type: decimal;
        margin-left: 1em;
      }
    }
  }
</style>
