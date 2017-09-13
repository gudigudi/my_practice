<template>
  <div class="design" v-show="currentView=== 'design'">
    <iframe src="iframePath" frameborder="0" @load="init"></iframe>
  </div>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    data(){
      return{
        iframeEle: null,
        iframeWin: null,
        iframeDoc: null,
        iframeBody: null,
        timer:null,
        inited: false,
        cache: '',
        iframePath: this.$store.state.config.iframePath,
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
          this.updateContent(this.iframeBody.innerHTML)
          this.updateToolbarActiveStates()
        }else{
          this.updateToolbarActiveStates(this.iframeDoc)
        }
      },
      content(val){
        if(this.inited){
          this.iframeBody.innerHTML!==val && (this.iframeBody.innerHTML=val)
        }else{
          this.cache=val
        }
      },
      command(data){
        this.exec(data.name,data.value)
      }
    },
    created(){
      if(!this.iframePath){
        throw this.lang.iframeUrl
      }
    },
    methods: Object.assign({}, mapActions([
      'updateContent',
      'updateToolbarActiveStates',
      'updatePopupDisplay',
      'callAction'
    ]),{
      init(event){
        this.iframeEle=event.target
        this.iframeWin=this.iframeEle.contentWindow
        this.iframeDoc=this.iframeWin.document
        this.iframeBody=this.iframeWin.document.body
        this.inited=true
        if(this.cache){
          this.iframeBody.innerHTML!==this.cache && (this.iframeBody.innerHTML=this.cache)
          this.cache=''
        }
        this.addEvent()
      },
      addEvent(){
        this.selectionChange()
        this.iframeDoc.addEventListener('click',this.updatePopupDisplay,false)
        this.iframeBody.addEventListener('keydown',this.keydownHandler,false)
        this.iframeBody.addEventListener('keyup',this.keyupHandler,false)
      },
      keydownHandler(event){
        if(event.ctrlKey&&(event.keyCode==89 || event.keyCode==90)){
          event.preventDefault()
          event.keyCode==89 && this.callAction('redo')
          event.keyCode==90 && this.callAction('undo')
        }
      },
      keyupHandler(event){
        clearTimeout(this.timer)
        this.timer=setTimeout(function () {
          this.updateContent(this.iframeBody.innerHTML)
        }.bind(this),500)
      },
      selectionChange(){
        this.iframeDoc.addEventListener('selectionchange',function () {
          this.updateToolbarActiveStates(this.iframeDoc)
        }.bind(this),false)

        if (navigator.userAgent.indexOf('firefox')!==-1){
          let oSel= this.iframeWin.getSelection()
          let focusOffset=-1
          setInterval(function () {
            if(oSel&&oSel.rangeCount){
              if (focusOffset !== oSel.focusOffset){
                focusOffset=oSel.focusOffset
                this.updateToolbarActiveStates(this.iframeDoc)
              }
            }else{
              oSel=this.iframeWin.getSelection()
            }
          }.bind(this),300)
        }
      },
      exec(name,value){
        if(document.queryCommandSupported('styleWithCss')){
          this.iframeDoc.execCommand('styleWithCss',false,true)
        }
        this[name] ? this[name](name,value):this.iframeDoc.execCommand(name,false,value)
        this.updateToolbarActiveStates(this.iframeDoc)
        this.updateContent(this.iframeBody.innerHTML)
      },
      insertHTML(name,value){
        let oSel=this.iframeWin.getSelection()
        let oRange=this.getRange()
        if (!oRange)return
        let node = null
        let frag=this.iframeDoc.createDocumentFragment()
        let obj=this.iframeDoc.createElement('div')
        obj.innerHTML=value
        while(obj.firstChild){
          node=obj.firstChild
          frag.appendChild(node)
        }
        oRange.insertNode(frag)
        oRange.setStartAfter(node)
        oRange.collapse(true)
        oSel.removeAllRanges()
        oSel.addRange(oRange)
      },
      fontSize(name,value){
        let selection=this.iframeWin.getSelection()
        let range=this.getRange()
        if(!range || range.collapsed){
          return
        }
        let childNodes=range.cloneContents().childNodes
        if(childNodes.length===1&& childNodes[0].nodeType===1 && childNodes[0].tagName.toLowerCase()==='span'){
          let span=range.extractContents().childNodes[0]
          span.style.fontSize=value
          range.insertNode(span)
          range.selectNode(span)
          selection.removeAllRanges()
          selection.addRange(range)
        }else{
          if(navigator.userAgent.indexOf('chrome') !== -1 && navigator.userAgent.indexOf('edge')===-1){
            if (document.queryCommandSupported('styleWithCss')) {
              this.iframeDoc.execCommand('styleWithCss',false,true)
            }
            this.iframeDoc.execCommand('fontSize',false,7)
            let container=range.commonAncestorContainer
            container.nodeType===3 && (container=container.parentNode)
            container.tagName.toLowerCase()==='span'&&(container=container.parentNode)

            Array.prototype.forEach.call(container.getElementsByTagName('span'),(span)=>{
              if(span.style.fontSize.trim()==='xx-large'){
                span.style.fontSize=value
              }
              span.normalize()
            })
          }else{
            if(document.queryCommandSupported('styleWithCss')){
              this.iframeDoc.execCommand('styleWithCss',false,false)
            }

            this.iframeDoc.execCommand('fontSize',false,7)

            let fontList= [],spanList=[]
            let container=range.commonAncestorContainer
            container.tagName.toLowerCase()==='font'&&(container=container.parentNode)
            fontList=container.getElementsByTagName('font')
            for(let i=0;i<fontList.length;i++){
              let font=fontList[i]
              let span=document.createElement('span')
              Array.prototype.forEach.call(font.attributes,(attr)=>{
                attr.nodeName==='size' ? span.style.fontSize=value : span.setAttribute(attr.nodeName,attr.nodeValue)
              })

              span.innerHTML=font.innerHTML
              span.querySelectorAll('span').length!==0 && vueUtil.command.format(span,'span','fontSize')
              span.normalize()

              font.parentNode.replaceChild(span,font)
              spanList.push(span)
            }

            range.setStartBefore(spanList[0])
            range.setEndAfter(spanList[spanList.length-1])
            selection.removeAllRanges()
            selection.addRange()
          }
        }
      },
      formatBlock(name,value){
        let ua=navigator.userAgent.toLowerCase()
        if(ua.indexOf('gecko')!==-1 || ua.indexOf('msie')!==-1){
          let range=this.getRange()
          if(!range||range.collapsed){
            window.alert(this.lang.ieMsg)
          }else{
            this.iframeDoc.execCommand('formatblock',false,value)
          }
        }
      }
    })
  }
</script>
