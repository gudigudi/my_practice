<template>
  <div @click="focusNewTag()" v-bind:class="{'read-only': readOnly}"
       class="input-tag-wrapper">
    <span v-for="(tag, index) in tags" class="input-tag">
      <span>{{tag}}</span>
      <a v-if="!readOnly" @click.prevent.stop="remove(index)"
         class="remove"></a>
    </span>
    <input type="text" v-if="!readOnly" v-bind:placeholder="placeholder"
           v-model="newTag" v-on:keydown.delete.stop="removeLastTag()"
           v-on:keydown.enter.188.prevent.stop="addNew(newTag)" class="new-tag"/>
  </div>
</template>
<script>
  import {validators} from '../utils/validators'

  export default {
    name: 'input-tag',
    props: {
      tags: {
        type: Array,
        default: () => []
      },
      placeholder: {
        type: String,
        default: ''
      },
      onChange: {
        type: Function
      },
      readOnly: {
        type: Boolean,
        default: false
      },
      validate: {
        type: String,
        default: ''
      }
    },
    data () {
      return {
        newTag: ''
      }
    },
    methods: {
      focusNewTag () {
        if (this.readOnly) {
          return
        }
        this.$el.querySelector('.new-tag').focus()
      },
      addNew (tag) {
        if (tag && !this.tags.includes(tag) && this.validateIfNeeded(tag)) {
          this.tags.push(tag)
          this.tagChange()
        }
        this.newTag = ''
      },
      validateIfNeeded (tagValue) {
        if (this.validate === '' || this.validate === undefined) {
          return true
        } else if (Object.keys(validators).indexOf(this.validate) > -1) {
          return validators[this.validate].test(tagValue)
        }
        return true
      },
      remove (index) {
        this.tags.splice(index, 1)
        this.tagChange()
      },
      removeLastTag () {
        if (this.newTag) {
          return
        }
        this.tags.pop()
        this.tagChange()
      },
      tagChange () {
        if (this.onChange) {
          this.onChange(JSON.parse(JSON.stringify(this.tags)))
        }
      }
    }
  }
</script>
<style lang="scss" scoped>
  .input-tag-wrapper {
    background-color: #fff;
    border: 1px solid #ccc;
    overflow: hidden;
    padding-left: 4px;
    padding-top: 4px;
    cursor: text;
    text-align: left;
    -webkit-appearance: textfield;
    -moz-appearance: textfield;
    appearance: textfield;
    .input-tag {
      background-color: #cde69c;
      border-radius: 2px;
      border: 1px solid #a5d24a;
      color: #638421;
      display: inline-block;
      font-size: 13px;
      font-weight: 400;
      margin-bottom: 4px;
      margin-right: 4px;
      padding: 3px;
      .remove {
        cursor: pointer;
        font-weight: bold;
        color: #638421;
        &:hover {
          text-decoration: none;
        }
        &:before {
          content: " x";
        }
      }
    }
    .new-tag {
      background: transparent;
      border: 0;
      color: #777;
      font-size: 13px;
      font-weight: 400;
      margin-bottom: 6px;
      margin-top: 1px;
      outline: none;
      padding: 4px;
      padding-left: 0;
      width: 80px;
    }
    .read-only {
      cursor: default;
    }
  }
</style>
