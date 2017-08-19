<template>
  <div class="wrapper">
    <h3 class="title">{{index + 1}}. {{event.title}}</h3>
    <p class="time">{{dateTimeFormatter(Date.parse(new Date(event.data)), 'dd/MM/yyyy')}}</p>
    <p class="desc">{{event.desc}}</p>
  </div>
</template>
<script>
  export default {
    name: 'cal-event-item',
    data () {
      return {}
    },
    props: {
      event: {
        type: Object,
        required: true
      },
      index: {
        type: Number,
        required: true
      },
      locale: {
        type: String,
        required: true
      }
    },
    methods: {
      dateTimeFormatter (data, format) {
        this.vm.$moment(data, format)
      }
    }
  }
</script>
