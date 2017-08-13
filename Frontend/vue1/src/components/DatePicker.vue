<template>

</template>
<script>
  export default {
    name: 'date-picker',
    props: {
      value: {
        validator: function (val) {
          return val === null || val instanceof Date || typeof value === 'string'
        }
      },
      name: {
        value: String
      },
    }
  }
</script>
<style>

</style>
