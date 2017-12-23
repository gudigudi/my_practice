<template>
  <div class="security-code-wrap">
    <label :for="`code-${uuid}`">
      <ul :class="`{theme}-container security-code-container`">
        <li class="field-wrap" v-for="(item, index) in length" :key="index">
          <i class="char-field">{{value[index] || placeholder}}</i>
        </li>
      </ul>
    </label>
    <input type="tel" class="input-code" autocomplete="off" autocorrect="off" autocapitalize="off"
           ref="input" v-model="value"
           :id="`code-${uuid}`" :name="`code-${uuid}`" :maxlength="length"
           @keyup="handleInput($event)">
  </div>
</template>
<script>
  export default {
    name: 'security-code',
    props: {
      length: {
        type: Number,
        default: 4
      },
      placeholder: {
        type: String,
        default: '-'
      },
      theme: {
        type: String,
        default: 'block'
      }
    },
    data () {
      return {
        value: ''
      }
    },
    computed: {
      uuid () {
        return Math.random().toString(36).substring(3, 8)
      }
    },
    methods: {
      hideKeyboard () {
        document.activeElement.blur() // ios hide keyboard
        this.$refs.input.blur() // android hide keyboard
      },
      handleSubmit () {
        this.$emit('input', this.value)
      },
      handleInput (e) {
        this.$refs.input.value = this.value
        if (this.value.length >= this.length) {
          this.hideKeyboard()
        }
        this.handleSubmit()
      }
    }
  }
</script>

<style scoped lang="scss">
  .security-code-wrap {
    overflow: hidden;
  }

  .security-code-container {
    margin: 0;
    padding: 0;
    display: flex;
    .field-wrap {
      list-style: none;
      display: block;
      height: 40px;
      width: 40px;
      line-height: 40px;
      font-size: 16px;
      .char-field {
        font-style: normal;
      }
    }
  }

  .block-container {
    justify-content: center;
    .field-wrap {
      background-color: #fff;
      margin: 2px;
      color: #000;
    }
  }

  .line-conntainer {
    position: relative;
    background-color: #fff;
    &:after {
      box-sizing: border-box;
      content: "";
      width: 200%;
      height: 200%;
      transform: scale(.5);
      position: absolute;
      border: 1px solid #d9d9d9;
      top: 0;
      left: 0;
      transform-origin: 0 0;
      border-radius: 4px;
    }
    .field-wrap {
      flex: 1;
      position: relative;
      &:not(:last-child):after {
        content: "";
        width: 1px;
        height: 50%;
        position: absolute;
        right: 0;
        top: 25%;
        background-color: #d9d9d9;
        transform: scaleX(.5);
      }
    }
  }
</style>
