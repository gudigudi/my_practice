<template>
  <div id="app">
    <ip-input :ip.sync="ip"
              :on-change="onIpChange"
              :on-blur="onIpBlur"></ip-input>
    <data-table :data="data"
                :columns="columns"
                :headerTitle="'Data Driven For Vuejs'"
                :clickRow="clickRow"
                v-if="!loading"></data-table>
    <max-min-input :max="100" :min="-100" ></max-min-input>
  </div>
</template>
<script>
  import IpInput from './components/IP-Input'
  import DataTable from './components/Data-Table'
  import MaxMinInput from './components/Max-Min-Input'

  export default {
    name: 'app',
    components: {
      MaxMinInput,
      DataTable,
      IpInput
    },
    data () {
      return {
        ip: '127.0.0.1',
        changeEvents: [],
        blurEvents: [],
        data: [],
        loading: true,
        columns: ['id', 'name', 'username', 'email', 'phone', 'website'],
        options: {
          showCheckbox: true,
          search: {
            useInitial: true,
            useEnglish: false,
            customFilter: null
          }
        }
      }
    },
    methods: {
      onIpChange: function (val) {
        this.changeEvents.push({
          timestamp: new Date().toString(),
          val: val
        })
        console.log('ip change:' + val)
      },
      onIpBlur: function (val) {
        this.blurEvents.push({
          timestamp: new Date().toString(),
          val: val
        })
        console.log(val)
      },
      clickRow: function (el) {
        console.log(`You clicked row ${el.target.parentElement.id}!`)
      }
    }
  }
</script>
<style lang="scss">
</style>
