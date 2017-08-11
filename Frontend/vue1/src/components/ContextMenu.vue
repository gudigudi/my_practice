<template>
  <div id="context-menu">
    <span class="context-menu-title context-menu-listwrapper">
      <li v-for="item in contextMenuData.menulists" class="context-menu-list">
        <button @click.stop="fnHandler(item)">
          <i :class="item.icoName"></i>
          <span>{{item.btnName}}</span>
        </button>
      </li>
    </span>
  </div>
</template>
<script>
  export default {
    name: 'context-menu',
    props: {
      contextMenuData: {
        type: Object,
        required: false,
        default: {
          axios: {
            x: null,
            y: null
          },
          menulists: [
            {
              fnHandler: 'adddata',
              icoName: 'fa fa-home fa-fw',
              btnName: 'New'
            },
            {
              fnHandler: 'savedata',
              icoName: 'fa fa-home fa-fw',
              btnName: 'Save'
            }
          ]
        }
      },
      transferIndex: {
        type: Number,
        default: 0
      }
    },
    watch: {
      'contextMenuData.axios' (val) {
        let x = val.x
        let y = val.y
        let _this = this
        let index = _this.transferIndex
        let menu = document.getElementsByClassName('contextMenuTitle')[index]
        menu.style.display = 'block'
        menu.style.left = x + 'px'
        menu.style.top = y + 'px'
        document.addEventListener('mouseup', function () {
          menu.style.display = 'none'
        }, false)
      }
    },
    methods: {
      fnHandler (item) {
        this.$emit(item.fnHandler)
      }
    }
  }
</script>
<style lang="scss" scoped>
  .context-menu-listwrapper {
    box-shadow: 2px 2px 2px #cccccc;
    display: none;
    position: fixed;
    z-index: 999;
    top: 0;
    left: 0;
    .context-menu-list {
      width: 150px;
      height: 32px;
      border-radius: 4px;
      background: #F3F3F3;
      text-decoration: none;
      list-style: none;
      button {
        cursor: pointer;
        width: 100%;
        height: 100%;
        display: block;
        outline: 0;
        border: 0;
        i, span {
          float: left;
        }
        i {
          padding: 0 10px 0 10px;
        }
        &:hover {
          box-shadow: 0px 1px 3px rgba(34, 25, 25, 0.2);
          color: #ffffff;
          border-radius: 4px;
          background: linear-gradient(bottom, #5a6a76, #2e3940);
        }
      }
    }
  }
</style>
