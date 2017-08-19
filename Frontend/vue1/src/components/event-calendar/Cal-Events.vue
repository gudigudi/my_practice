<template>
  <div class="events-wrapper" :style="bgColor">
    <h2 class="date">{{dayEventsTitle}}</h2>
    <div class="cal-events">
      <slot>
        <div v-for="(event,index) in events" class="event-item">
          <cal-event-item :event="event" :index="index" :locale="locale"></cal-event-item>
        </div>
      </slot>
    </div>
  </div>
</template>
<script>
  import CalEventItem from './Cal-Event-Item.vue'

  export default {
    components: {CalEventItem},
    name: 'cal-events',
    data () {
      return {}
    },
    props: {
      dayEvents: {
        type: Object,
        required: true
      },
      locale: {
        type: String,
        required: true
      },
      color: {
        type: String,
        required: true
      }
    },
    computed: {
      dayEventTitle () {
        if (this.dayEvents.date !== 'all') {
          let tempDate
          if (this.dayEvents.events.length !== 0) {
            tempDate = Date.parse(new Date(this.dayEvents.events[0].date))
            return this.vm.$moment(tempDate, 'dd/MM/yyyy')
          } else {
            tempDate = this.vm.$moment(Date.parse(new Date(this.dayEvents.date)), 'dd/MM/yyyy')
            return `${tempDate} Not Have Events`
          }
        } else {
          return 'All Events'
        }
      },
      events () {
        return this.dayEvents.events
      },
      bgColor () {
        return {backgroundColor: this.color}
      }
    },
    methods: {
      dateTimeFormatter (data, format) {
        this.vm.$moment(data, format)
      }
    }
  }
</script>
