<template>
  <div class="select-search">
    <div class="select-header" @click="isExpand=!isExpand">
      <input type="text" autocomplete="off" readonly
             :placeholder="placeholder" :value="placeholderValue"
             @keydown.down.prevent="selectChildWidthArrowDown"
             @keydown.up.prevent="selectChildWidthArrowUp"
             @keydown.enter="selectChildWidthEnter">
      <i class="fzicon" :class="isExpand?'fz-ad-jiantou1':'fz-ad-jiantou'"></i>
    </div>
    <div class="select-body" v-if="isExpand">
      <input type="text" placeholder="关键字" autocomplete="off"
             v-model="searchVal"
             @blur="resetDefaultStatus" @keydown.esc="resetDefaultStatus"
             @keydown.down.prevent="selectChildWidthArrowDown"
             @keydown.up.prevent="selectChildWidthArrowUp"
             @keydown.enter="selectChildWidthEnter">
      <transition name="el-fade-in-linear" mode="out-in">
        <div class="typeahead-filter" v-show="typeaheadData">
          <transition-group tag="ul" name="el-fade-in-linear">
            <li v-for="(item,index) in typeaheadData"
                :key="index" :class="item.active ? 'active' : ''"
                @mouseenter="setActiveClass(index)"
                @mouseleave="setActiveClass(index)" @click="selectChild(index)">
              <a href="javascript:;">{{item.text}}</a>
            </li>
          </transition-group>
          <p class="noFound" v-show="typeaheadData && typeaheadData.length===0">未能查询到，请重新输入!</p>
        </div>
      </transition>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'selectSearch',
    data: function () {
      return {
        placeholderValue: '',
        isExpand: false,
        searchVal: '',
        resultVal: '',
        searchList: [],
        currentIndex: -1
      }
    },
    computed: {
      typeaheadData () {
        let temp = []
        if (this.searchVal === '') {
          return this.mapData
        } else {
          this.currentIndex = -1
          this.mapData.forEach(item => {
            item.active = false
            if (item.text.indexOf(this.searchVal.toLowerCase().trim()) !== -1) {
              temp.push(item)
            }
          })
          return temp
        }
      }
    },
    props: {
      placeholder: {
        type: String,
        default: '--请选择--'
      },
      mapData: {
        type: Array,
        default: function () {
          return [
            {
              text: 'apple',
              value: 0
            },
            {
              text: 'baidu',
              value: 1
            },
            {
              text: 'cnode',
              value: 2
            },
            {
              text: 'datu',
              value: 3
            },
            {
              text: 'ebay',
              value: 4
            },
            {
              text: 'firefox',
              value: 5
            },
            {
              text: 'google',
              value: 6
            },
            {
              text: 'haier',
              value: 7
            },
            {
              text: 'iphone',
              value: 8
            },
            {
              text: 'java',
              value: 9
            }
          ]
        }
      }
    },
    methods: {
      resetDefaultStatus () {
        this.searchVal = ''
        this.resultVal = ''
        this.currentIndex = -1
        this.typeaheadData.forEach(item => {
          this.$set(item, 'active', false)
        })
      },
      setActiveClass (index) {
        this.mapData.forEach((item, innerIndex) => {
          if (index === innerIndex) {
            this.$set(item, 'active', true)
            this.currentIndex = index
          } else {
            this.$set(item, 'avtive', false)
          }
        })
      },
      selectChildWidthArrowDown () {
        if (this.currentIndex < this.typeaheadData.length) {
          this.currentIndex++
          this.typeaheadData.forEach((item, index) => {
            this.currentIndex === index ? this.$set(item, 'active', true) : this.$set(item, 'active', false)
          })
        }
      },
      selectChildWidthArrowUp () {
        if (this.currentIndex > 0) {
          this.currentIndex--
          this.typeaheadData.forEach((item, index) => {
            this.currentIndex === index ? this.$set(item, 'avtive', true) : this.$set(item, 'avtive', false)
          })
        }
      },
      selectChildWidthEnter () {
        if (this.typeaheadData.length === 1) {
          this.searchVal = this.typeaheadData[0].text
          this.searchVal = this.typeaheadData[0].value
          this.placeholderValue = this.typeaheadData[0].text
          this.isExpand = false
          this.$emit('selectValue', {
            text: this.searchVal,
            value: this.resultVal
          })
        } else {
          this.typeaheadData.forEach(item => {
            if (this.searchVal === item.text || item.active === true) {
              this.searchVal = item.text
              this.placeholderValue = item.text
              this.resultVal = item.value
              this.isExpand = false
              this.$emit('selectValue',
                {
                  text: this.searchVal,
                  value: this.resultVal
                }
              )
            }
          })
        }
        this.resetDefaultStatus()
      },
      selectChild (index) {
        this.mapData.forEach((item, innerIndex) => {
          this.searchVal = item.text
          this.resultVal = item.value
          this.placeholderValue = item.text
          this.isExpand = false
        })
        this.$emit('selectValue', {
          text: this.searchVal,
          value: this.resultVal
        })
        this.resetDefaultStatus()
      }
    }
  }
</script>
<style scoped lang="scss">
  .el-fade-in-linear-enter-active,
  .el-fade-in-linear-leave-active,
  .faede-in-linear-enter-active,
  .faede-in-linear-leave-active {
    transition: opacity .2s linear;
  }

  .el-fade-in-enter,
  .el-fade-in-leave-active,
  .el-fade-in-linear-enter,
  .el-fade-in-linear-leave,
  .el-fade-in-linear-leave-active,
  .fade-in-linear-enter,
  .fade-in-linear-leave,
  .fade-in-linear-leave-active {
    opacity: 0;
  }

  .select-search {
    a {
      color: #333;
      text-decoration: none;
      padding: 5px;
    }
    ul {
      list-style: none;
      padding: 6px 0;
      margin: 0;
      overflow: visible;
      li {
        display: block;
        width: 100%;
        padding: 8px 10px;
        position: relative;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        color: #48576a;
        height: 36px;
        line-height: 1.5;
        box-sizing: border-box;
        cursor: pointer;
        font-size: 14px;
        &.active {
          background-color: #20a0ff;
          a {
            color: #fff;
          }
        }
      }
    }
    .select-header {
      position: relative;
      border-radius: 4px;
      border: 1px solid #bfcbd9;
      outline: 0;
      padding: 0 8px;
      > input {
        border: none;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        width: 100%;
        outline: 0;
        box-sizing: border-box;
        color: #1f2d3d;
        font-size: inherit;
        height: 36px;
        line-height: 1;
      }
      > i {
        transition: all .3s linear;
        display: inline-block;
        position: absolute;
        right: 3%;
        top: 50%;
        transform: translateY(-50%);
      }
    }
    .select-body {
      z-index: 1000;
      border-radius: 2px;
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
      box-sizing: border-box;
      margin: 5px 0;
      padding: 8px;
      > input {
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        background-color: #fff;
        background-image: none;
        border-radius: 4px;
        border: 1px solid #bfcbd9;
        box-sizing: border-box;
        color: #1f2d3d;
        font-size: inherit;
        height: 36px;
        line-height: 1;
        outline: 0;
        padding: 3px 10px;
        transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
        width: 100%;
        display: inline-block;
      }
    }
  }
</style>
