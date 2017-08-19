<template>

</template>
<script>
  export default {
    name: 'cal-panel',
    date () {
      return {}
    },
    props: {
      events: {
        type: Array,
        required: true
      },
      calendar: {
        type: Object,
        required: true
      },
      selectedDay: {
        type: String,
        required: false
      }
    },
    computed: {
      dayList () {
        let firstDay = new Date(this.calendar.params.curYear + '/' + (this.calendar.params.curMonth + 1) + '/01')
        let startTimestamp = firstDay - 1000 * 60 * 60 * 24 * firstDay.getDay()
        let item, status, tempArr = [], tempItem

        for (let i = 0; i < 42, i++) {
          item = new Date(startTimestamp + i * 1000 * 60 * 60 * 24)
          if (this.calendar.params.curMonth === item.getMonth()) {
            status = 1
          } else {
            status = 0
          }
          tempItem = {
            date: `${item.getFullYear()}/${item.getMonth() + 1}/${item.getDate()}`,
            status: status
          }
          this.events().forEach((event) => {
            if (isEqualDateStr(event.date, tempItem.date)) {
              tempItem.title = event.title
              tempItem.desc = event.desc || ''
            }
          })
          tempArr.push(tempItem)
        }
        return tempArr
      },
      today () {
        let dateObj = new Date()
        return `${}`
      }
    }
  }
</script>
