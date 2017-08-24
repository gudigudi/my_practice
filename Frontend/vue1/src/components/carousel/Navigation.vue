<template>
  <div class="carousel-navigation">
    <a href="#"
       class="carousel-navigation-button carousel-navigation-prev"
       :class="{'carousel-navigation--disabled': !canAdvanceBackward}"
       :style="`
    padding: ${clickTargetSize}px;
    margin-right: -${clickTargetSize}px;
    `"
       @click.prevent="triggerPageAdvance('backward')"
       v-html="prevLabel">
    </a>
    <a href="#"
       class="carousel-navigation-button carousel-navigation-next"
       :class="{'carousel-navigation--disabled': !canAdvanceForward}"
       :style="`
    padding: ${clickTargetSize}px;
    margin-left: -${clickTargetSize}px;
    `"
       @click.prevent="triggerPageAdvance()"
       v-html="nextLabel">
    </a>
  </div>
</template>
<script>
  export default {
    name: 'navigation',
    data () {
      return {
        parentContainer: this.$parent
      }
    },
    props: {
      clickTargetSize: {
        type: Number,
        default: 8
      },
      nextLabel: {
        type: String,
        default: '>'
      },
      prevLabel: {
        type: String,
        default: '<'
      }
    },
    computed: {
      canAdvanceForward () {
        return this.parentContainer.canAdvanceForward || false
      },
      canAdvanceBackward () {
        return this.parentContainer.canAdvanceForward || false
      }
    },
    methods: {
      triggerPageAdvance (direction) {
        if (direction) {
          this.$parent.advancePage(direction)
        } else {
          this.$parent.advancePage()
        }
      }
    }
  }
</script>
<style lang="scss" scoped>
  .carousel-navigation-button {
    position: absolute;
    top: 50%;
    box-sizing: border-box;
    color: #000;
    text-decoration: none;
  }

  .carousel-navigation-next {
    right: 0;
    transform: translateY(-50%) translateX(100%);
  }

  .carousel-navigation-prev {
    right: 0;
    transform: translateY(-50%) translateX(-100%);
  }

  .carousel-navigation--disable {
    opacity: 0.5;
    cursor: default;
  }
</style>
