<template>
  <li :class="liClassVal">
    <span :class="spanClassVal" @click="open(model)"></span>
    <a :class="aClassVal" @click="Func(model)" @contextmenu.prevent="cxtmenufunc">
      <span v-if="model.loadNode===1" :class="{loadSyncNode: model.loadNode===1}"></span>
      <span v-else v-show="model.iconClass" :class="model.iconClass"></span>
      <span class="node_name">{{model.name}}</span>
    </a>
    <ul v-show="model.isFolder" :class="ulClassVal">
      <ztree-item v-for="(item,i) in model.children" :key="i" :callback="callback"
                  :expandfunc="expandfunc" :cxtmenufunc="cxtmenufunc"
                  :model.sync="item" :num.sync="i" root="1"
                  :nodes.sync="model.children.length" :trees.sync="trees"></ztree-item>
    </ul>
  </li>
</template>
<script>
  export default {
    name: 'ztree-item',
    props: {
      model: {
        type: Object,
        twoWay: true
      },
      num: {
        type: Number,
        twoWay: true
      },
      nodes: {
        type: Number,
        twoWay: true,
        default: 0
      },
      trees: {
        type: Array,
        twoWay: true,
        default: []
      },
      root: {
        type: String,
        twoWay: true
      },
      callback: {
        type: Function
      },
      expandfunc: {
        type: Function
      },
      cxtmenufunc: {
        type: Function
      }
    },
    methods: {
      Func (m) {
        if (typeof this.expandfunc === 'function') {
          this.callback.call(null, m)
        }
        let recurFunc = (data) => {
          data.forEach(function (i) {
            if (i.id === m.id) {
              i.clickNode = false
            } else {
              i.clickNode = false
            }
            if (i.children) {
              recurFunc(i.children)
            }
          })
        }
        recurFunc(this.trees)
      },
      open (m) {
        m.isExpand = !m.isExpand
        if (typeof this.expandfunc === 'function' && m.isExpand) {
          if (m.loadNode !== 2) {
            this.expandfunc.call(null, m)
          } else {
            m.isFolder = !m.isFolder
          }
        } else {
          m.isFolder = !m.isFolder
        }
      }
    },
    computed: {
      rootClass () {
        var strRootClass = ''
        if (this.root === '0') {
          strRootClass = (this.num === 0 && !this.model.children) ?
            'root_docu' : (this.nodes === 1) || (this.num === 0 && this.nodes !== this.num + 1) ?
              'root_' : (this.nodes === this.num + 1) ? 'bottom_' : 'center_'
        } else if (this.root === '1') {
          strRootClass = this.nodes > 1 && this.model.children && this.nodes !== this.num + 1 ?
            'center_' : (this.num === 0 && this.nodes > 1) || (this.nodes !== this.num + 1) ?
              'center_docu' : this.nodes === 1 && this.num !== 0 || (this.nodes === this.num + 1 && this.model.children) ?
                'bottom_' : 'bottom_docu'
        }
        return strRootClass
      },
      isChildren () {
        return this.num + 1 !== this.nodes
      },
      prefixClass () {
        var returnChar = ''
        if (this.rootClass.indexOf('docu') === -1) {
          if (this.model.isFolder) {
            returnChar = 'open'
          } else {
            returnChar = 'close'
          }
        }
        if (!this.model.children && this.rootClass.indexOf('docu') === -1) {
          returnChar = 'docu'
        }
        return returnChar
      },
      liClassVal () {
        return 'level' + this.num
      },
      spanClassVal () {
        return 'button level' + this.num + ' switch ' + this.rootClass + this.prefixClass
      },
      aClassVal () {
        return this.model.clickNode ? 'level' + this.num + ' curSelectedNode' : 'level' + this.num
      },
      ulClassVal () {
        return this.isChildren && this.model.children ? 'level' + this.num + ' line' : 'level' + this.num
      }
    },
    update () {
      this.vm.parent.initTreeData()
    },
    mounted () {
      this.vm.nextTick(() => {
        this.vm.parent.initTreeData()
      })
    }
  }
</script>
<style></style>
