<template>
  <div v-if="treeDataSource.length>0" class="ztree_content_wrap">
    <div class="ztree_background left">
      <ul class="ztree">
        <ztree-item v-for="(model,i) in treeDataSource" :key="i" :model.sync="m"
                    :num.sync="i" root="0" :nodes.sync="treeDataSource.length"
                    :callback="func" :expandfunc="expandfunc"
                    :cxtmenufunc="contextmenu" :trees.sync="treeDataSource"></ztree-item>
      </ul>
    </div>
  </div>
</template>
<script>
  import ZtreeItem from './Ztree-Item.vue'

  export default {
    components: {ZtreeItem},
    name: 'ztree',
    data () {
      return {
        treeDataSource: []
      }
    },
    props: {
      list: {
        type: Array,
        twoWay: true
      },
      func: {
        type: Function,
        default: () => {
        }
      },
      expandfunc: {
        type: Function,
        default: () => {
        }
      },
      contextmenu: {
        type: Function,
        default: () => {
          console.log('default click contextmenu')
        }
      },
      isOpen: {
        type: Boolean,
        twoWay: true,
        default: false
      }
    },
    methods: {
      initTreeData () {
        const tempList = JSON.parse(JSON.stringify(this.list))
        const recurrenceFunc = (data) => {
          data.forEach((m) => {
            m.clickNode = false
            m.children = m.children || []
            if (m.children.length > 0) {
              m.isFolder = this.isOpen
              m.isExpand = this.isOpen
              m.loadNode = 0
              recurrenceFunc(m.children)
            } else {
              delete m.children
            }
          })
        }
        recurrenceFunc(tempList)
        this.treeDataSource = tempList
        console.log(JSON.parse(JSON.stringify(this.treeDataSource)))
      }
    },
    update () {
      this.initTreeData()
    },
    mounted () {
      this.vm.nextTick(() => {
        this.initTreeData()
      })
    }
  }
</script>
<style lang="scss" scoped>
  div.ztree_content_wrap {
    height: 380px;
    div.left {
      float: left;
      width: 100%;
    }
    div.ztree_background {
      width: 100%;
      height: 500px;
      text-align: left;
    }
  }

  ul.ztree {
    border: 1px solid #ddd;
    background: #ffffff;
    width: 100%;
    height: auto;
    overflow-y: scroll;
    overflow-x: auto;
    &.zTreeDragUL {
      margin: 0;
      padding: 0;
      position: absolute;
      width: auto;
      height: auto;
      overflow: hidden;
      background-color: #cfcfcf;
      border: 1px #00b83f dotted;
      opacity: 0.8;
      filter: alpha(opacity=80);
    }
  }
  .ztree {
    margin: 0;
    padding: 5px;
    color: #333;
  }

  .zTreeMask {
    z-index: 10000;
    background-color: #cfcfcf;
    opacity: 0.0;
    filter: alpha(opacity=0);
    position: absolute;
  }

  .loadSyncNode {
    width: 16px;
    height: 16px;
    position: relative;
    display: inline-block;
    background-image: url('data:image/gif;base64,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');
  }
</style>
