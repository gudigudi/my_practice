<template>
  <div>
    <template v-for="item in config">
      <div v-if="nativeBtns[item]">
        <a href="javascrit:;" :title="lang[item].title"
           :class="{'disabled':states[item].disabled, 'active':states[item].active}"
           @click="clickHandler(item,null)" unselectable="on">
          <i :class="[nativeBtns[item].class]"></i>
        </a>
      </div>
      <div v-if="item==='divider' || item=== '|'">
        <a href="javascript:;" class="divider"></a>
      </div>
      <component v-if="customBtns.indexOf(item) != -1" :is="item" :comp-name="item"></component>
    </template>
  </div>

</template>
<script>
  import color from './Color.vue'
  import fontName from './Font-Name.vue'
  import fontSize from './Font-Size.vue'
  import switchView from './Switch-View.vue'
  import element from './Element.vue'
  import table from './Table.vue'
  import undo from './Undo.vue'
  import link from './Link.vue'
  import emoji from './Emoji.vue'
  import picture from './Picture.vue'

  let nativeBtns = {
    removeFormat: {class: 'icon-eraser'},
    bold: {class: 'icon-bold'},
    italic: {class: 'icon-italic'},
    underline: {class: 'icon-underline'},
    strikeThrough: {class: 'icon-strikethrough'},
    superscript: {class: 'icon-superscript'},
    subscript: {class: 'icon-subscript'},
    indent: {class: 'icon-indent'},
    outdent: {class: 'icon-outdent'},
    justifyLeft: {class: 'icon-align-left'},
    justifyCenter: {class: 'icon-align-center'},
    justifyRight: {class: 'icon-align-right'},
    justifyFull: {class: 'icon-align-justify'},
    insertOrderedList: {class: 'icon-list-ol'},
    insertUnorderedList: {class: 'icon-list-ul'}
  }

  let customBtns = [
    'foreColor',
    'backColor',
    'fontName',
    'fontSize',
    'switchView',
    'element',
    'table',
    'undo',
    'link',
    'emoji',
    'picture'
  ]

  export default {
    data () {
      return {
        nativeBtns,
        customBtns,
        lang: this.$store.state.lang.toolbar,
        config: this.$store.state.config.toolbar
      }
    },
    computed: {
      states () {
        return this.$store.state.toolbarStates
      }
    },
    methods: {
      clickHandler (name, value) {
        this.$store.dispatch('execCommand', {name, value})
      }
    }
  }
</script>
<style lang="scss" scoped>
  .toolbar {
    border-bottom: 1px solid #ddd;
    user-select: none;
    display: table;
    width: 100%;
    font-size: 0;
    letter-spacing: -4px;
    & > div {
      position: relative;
      display: inline-block;
      font-size: initial;
      letter-spacing: initial;
      & > a {
        display: inline-block;
        padding: 10px 12px;
        color: rgba(0, 0, 0, 0.6);
      }
      a.divider {
        width: 0;
        height: 26px;
        margin: 0 5px;
        padding: 0;
        border-right: 1px solid #ddd;
        vertical-align: middle;
      }
      a.active {
        background: #eee;
        color: #000;
      }
      & > a:not(.disabled):hover {
        background: #eee;
        color: #000;
      }
    }
  }
</style>
