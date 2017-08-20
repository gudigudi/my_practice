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
  }
</script>
<style lang="scss" scoped>
  .expandIcon {
    background-position: -74px -36px;
    line-height: 0;
    margin: 0;
    width: 16px;
    height: 16px;
    display: inline-block;
    vertical-align: middle;
    border: 0 none;
    cursor: pointer;
    outline: none;
    position: absolute;
    top: 4px;
    background-color: transparent;
    background-repeat: no-repeat;
    background-attachment: scroll;
    background-image: url("../../assets/image/ztreestandard.png");
  }

  * {
    padding: 0;
    margin: 0;
    font-size: 15px;
    font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
  }

  li {
    position: relative;
    padding: 0;
    margin: 0;
    list-style: none;
    line-height: 24px;
    text-align: left;
    white-space: nowrap;
    outline: 0;
    ul {
      margin: 0;
      padding: 0 0 0 18px;
      &.line {
        background: url('../../assets/image/line_conn.gif') 0 0 repeat-y;
      }
    }
    a {
      padding: 1px 3px 0 5px;
      margin: 0;
      cursor: pointer;
      height: 17px;
      color: #fff;
      background-color: transparent;
      text-decoration: none;
      vertical-align: top;
      display: inline-block;
      &:hover {
        text-decoration: underline;
        color: blue;
      }
      &.curSelectedNode {
        padding-top: 0px;
        background-color: #191d22;
        color: #fff;
        height: 24px;
        border: 1px solid #191d22;
        opacity: 0.8;
      }
      &.curSelectedNode_Edit {
        padding-top: 0px;
        background-color: #FFE6B0;
        color: black;
        height: 16px;
        border: 1px #FFB951 solid;
        opacity: 0.8;
      }
      &.tmpTargetNode_inner {
        padding-top: 0px;
        background-color: #316AC5;
        color: white;
        height: 16px;
        border: 1px #316AC5 solid;
        opacity: 0.8;
        filter: alpha(opacity=80)
      }
      &.tmpTargetNode_prev {
      }
      &.tmpTargetNode_next {
      }

      input.rename {
        height: 14px;
        width: 80px;
        padding: 0;
        margin: 0;
        font-size: 12px;
        border: 1px solid #7ec4cc;
        *border: 0px;
      }
    }
    span {
      line-height: 16px;
      margin-right: 2px;
      top: 3px;
      display: inline-block;
      &.button {
        line-height: 0;
        margin: 0;
        width: 16px;
        height: 16px;
        display: inline-block;
        vertical-align: middle;
        border: 0 none;
        cursor: pointer;
        outline: none;
        background-color: transparent;
        background-repeat: no-repeat;
        background-attachment: scroll;
        background-image: url('../../assets/image/ztreestandard.png');
        *background-image: url('../../assets/image/ztreestandard.gif');
        &.chk {
          width: 13px;
          height: 13px;
          margin: 0 3px 0 0;
          cursor: auto;
          &.checkbox_false_full {
            background-position: 0 0;
          }
          &.checkbox_false_full_focus {
            background-position: 0 -14px;
          }
          &.checkbox_false_part {
            background-position: 0 -28px;
          }
          &.checkbox_false_part_focus {
            background-position: 0 -42px;
          }
          &.checkbox_false_disable {
            background-position: 0 -56px;
          }
          &.checkbox_true_full {
            background-position: -14px 0;
          }
          &.checkbox_true_full_focus {
            background-position: -14px -14px;
          }
          &.checkbox_true_part {
            background-position: -14px -28px;
          }
          &.checkbox_true_part_focus {
            background-position: -14px -42px;
          }
          &.checkbox_true_disable {
            background-position: -14px -56px;
          }
          &.radio_false_full {
            background-position: -28px 0;
          }
          &.radio_false_full_focus {
            background-position: -28px -14px;
          }
          &.radio_false_part {
            background-position: -28px -28px;
          }
          &.radio_false_part_focus {
            background-position: -28px -42px;
          }
          &.radio_false_disable {
            background-position: -28px -56px;
          }
          &.radio_true_full {
            background-position: -42px 0;
          }
          &.radio_true_full_focus {
            background-position: -42px -14px;
          }
          &.radio_true_part {
            background-position: -42px -28px;
          }
          &.radio_true_part_focus {
            background-position: -42px -42px;
          }
          &.radio_true_disable {
            background-position: -42px -56px;
          }
        }
        &.switch {
          width: 18px;
          height: 18px;
        }
        &.root_open {
          background-position: -92px -54px;
        }
        &.root_close {
          background-position: -74px -54px;
        }
        &.roots_open {
          background-position: -92px 0;
        }
        &.roots_close {
          background-position: -74px 0;
        }
        &.center_open {
          background-position: -92px -18px;
        }
        &.center_close {
          background-position: -74px -18px;
        }
        &.bottom_open {
          background-position: -92px -36px;
        }
        &.bottom_close {
          background-position: -74px -36px;
        }
        &.noline_open {
          background-position: -92px -72px;
        }
        &.noline_close {
          background-position: -74px -72px;
        }
        &.root_docu {
          background: none;
        }
        &.roots_docu {
          background-position: -56px 0;
        }
        &.center_docu {
          background-position: -56px -18px;
        }
        &.bottom_docu {
          background-position: -56px -36px;
        }
        &.noline_docu {
          background: none;
        }

        &.ico_open {
          margin-right: 2px;
          background-position: -110px -16px;
          vertical-align: top;
          *vertical-align: middle;
        }
        &.ico_close {
          margin-right: 2px;
          background-position: -110px 0;
          vertical-align: top;
          *vertical-align: middle;
        }
        &.ico_docu {
          margin-right: 2px;
          background-position: -110px -32px;
          vertical-align: top;
          *vertical-align: middle;
        }
        &.edit {
          margin-right: 2px;
          background-position: -110px -48px;
          vertical-align: top;
          *vertical-align: middle;
        }
        &.remove {
          margin-right: 2px;
          background-position: -110px -64px;
          vertical-align: top;
          *vertical-align: middle;
        }
      }
    }
  }

  ul.tmpTargetzTree {
    background-color: #ffe6b0;
    opacity: 0.8;
    filter: alpha(opacity=80);
  }

  span.tmpzTreeMove_arrow {
    width: 16px;
    height: 16px;
    display: inline-block;
    padding: 0;
    margin: 2px 0 0 1px;
    border: 0 none;
    position: absolute;
    background-color: white;
    background-repeat: no-repeat;
    background-attachment: scroll;
    background-position: -110px -80px;
    background-image: url('../../assets/image/ztreestandard.png');
    *background-image: url('../../assets/image/ztreestandard.gif');
  }
</style>
