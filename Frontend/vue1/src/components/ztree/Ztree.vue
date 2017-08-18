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
  }

  .ztree {
    margin: 0;
    padding: 5px;
    color: #333;
    * {
      padding: 0;
      margin: 0;
      font-size: 15px;
      font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
    }
    li {
      position:;
    }

  }

</style>
