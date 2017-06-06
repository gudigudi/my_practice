<template>
  <input type="range" :max="max" :min="min">
</template>
<script>
  export default {
    name: 'max-min-input',
    props: {
      max: {
        type: Number,
        default () {
          return 10
        }
      },
      min: {
        type: Number,
        default () {
          return 1
        }
      }
    }
  }
</script>
<style></style>
