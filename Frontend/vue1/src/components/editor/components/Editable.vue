<template>
  <div class="design" v-show="currentView==='design'">
    <div contenteditable="true" spellcheck="false" @click="updatePopupDisplay" @keydown="keydownHandler"
         @keydown="keyupHandler"></div>
  </div>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    name: 'editable',
    data(){
      return{
        timer: null,
        inited: false,
        cache: '',
        lang: this.$store.state.lang.editable
      }
    },
    computed:{
      currentView(){
        return this.$store.state.currentView
      },
      content(){
        return this.$store.state.content
      },
      command(){
        return this.$store.state.command
      }
    },
    watch:{
      currentView(val){
        if(val==='sourceCode'){
          clearTimeout(this.timer)
          this.updateContent(this.$el.children[0].innerHTML)
          this.updateToolbarActiveStates()
        }else{
          this.updateToolbarActiveStates(document)
        }
      },
      content(val){
        this.$el.children[0].innerHTML!=val && (this.$el.children[0].innerHTML=val)
      },
      command(data){
        this.exec(data.name,data.value)
      }
    },
    mounted(){
      this.selectionChange()
    },
    methods: Object.assign({}, mapActions({
      'updateContent',
      'updateToolbarActiveStates',
      'updatePopupDisplay',
      'callAction'
    }),{
      keydownHandler(event){
        if(event.ctrlKey&&(event.keyCode==89 || event.keyCode==90)){
          event.preventDefault()
          event.keyCode==89 && this.callAction('redo')
          event.keyCode==90 && this.callAction('undo')
        }
      },
      keyupHandler(){
        clearTimeout(this.timer)
        this.timer=setTimeout(function () {
          this.updateContent(this.$el.children[0].innerHTML)
        }.bind(this),500)
      },
      selectionChange(){
        document.addEventListener('selectionchange',function () {
          if(this.$el.children[0].contains(window.getSelection().focusNode)){
            this.updateToolbarActiveStates(document)
          }
        }.bind(this),false)
        if(navigator.userAgent.indexOf('firefox')!==-1){
          let oSel=this.iframeWin.getSelection()
          let focusOffset=-1
          setInterval(function () {
            if(oSel&&oSel.rangeCount){
              if (focusOffset!==oSel.focusOffset){
                focusOffset=oSel.focusOffset
                this.updateToolbarActiveStates(this.iframeDoc)
              }
            }else{
              oSel=window.getSelection()
            }
          }.bind(this),300)
        }
      },
      exec(name,value){
        let range=this.getRange()
        let container=range.commonAncestorContainer
        if(!this.$el.children[0].contains(container)){
          return
        }
        if(document.queryCommandSupported('styleWithCss')){
          document.execCommand('styleWithCss',false,true)
        }
        this[name] ? this[name](name,value) : document.execCommand(name,false,value)
        this.updateToolbarActiveStates(document)
        this.updateContent(this.$el.children[0].innerHTML)
      },
      insertHTML(name,value){

      }
    })
  }
</script>
<style lang="scss" scoped>
  .design > div {
    height: 100%;
    outline: none;
    overflow-y: auto;
    padding: 8px;
    box-sizing: border-box;
  }
</style>
