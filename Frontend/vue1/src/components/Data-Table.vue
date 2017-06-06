<template>
  <div class="data-table">
    <h1>{{headerTitle}}</h1>
    <br>
    <table class="table table-bordered table-condensed table-info table-hover">
      <thead>
      <tr>
        <th v-for="column in columns" class="label-cell sortable-cell" :id="column" @click="sort">{{column}}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="obj in filteredData" @click="clickRow" :id="obj.id">
        <td class="label-cell" v-for="property in obj">{{property}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
  export default {
    name: 'data-table',
    props: {
      data: {
        required: true,
        default: []
      },
      columns: {
        required: true
      },
      headerTitle: {
        required: false,
        default: ''
      },
      clickRow: {
        required: true
      }
    },
    data () {
      return {
        filteredData: []
      }
    },
    created () {
      this.filteredData = this.filterByColumns(this.data)
    },
    methods: {
      sort (el) {
        console.log('Implement some sweet sorting here!')
      },
      filterByColumns (data) {
        let newData = []
        for (let i = 0; i < data.length; i++) {
          for (let j = 0; j < this.columns.length; j++) {
            if (!newData[i]) {
              newData[i] = {[this.columns[i]]: data[i][this.columns[j]]}
            } else {
              newData[i][this.columns[j]] = data[i][this.columns[j]]
            }
          }
        }
        return newData
      }
    }
  }
</script>
