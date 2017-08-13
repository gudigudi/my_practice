<template>
  <div id="app">

    <!--<file-upload></file-upload>-->
    <!--<loading size="massive" message="Loading..."></loading>-->
    <!--<layouts></layouts>-->
    <!--<router-view></router-view>-->
    <input v-model="data"/>
    <p>{{data}}</p>
    {{countFilterA}}
    {{countFilterB}}
    <p>module-a:
      <button @click="increateA">{{a.count}}</button>
    </p>
    <p>module-b:
      <button @click="increateB">{{b.count}}</button>
    </p>
    <button @click="increateAsyncA">actions</button>
    <select-search></select-search>
    <tree-view></tree-view>
    <app-footer></app-footer>
  </div>
</template>
<script>
  import Layouts from './layouts/layouts'
  import FileUpload from './components/FileUpload'
  import Loading from './components/Loading'
  import AppFooter from './AppFooter'

  import example from './mixins/example'

  import {mapState, mapGetters, mapMutations, mapActions} from 'vuex'
  import SelectSearch from './components/selectSearch.vue'
  import TreeView from './components/treeview/TreeView.vue'

  export default {
    name: 'app',
    components: {
      TreeView,
      SelectSearch,
      AppFooter,
      Loading,
      FileUpload,
      Layouts
    },
    computed: {
      ...mapState([
        'a',
        'b'
      ]),
      ...mapGetters([
        'countFilterA',
        'countFilterB'
      ])
    },
    data () {
      return {
        data: 'lala'
      }
    },
    methods: {
      ...mapMutations([
        'increateA',
        'increateB'
      ]),
      ...mapActions([
        'increateAsyncA'
      ]),
      logic () {
        console.log('do the logic about hello page')
      }
    },
    mixins: [example],
    mounted () {
      this.logic()
    }
  }
</script>
<style lang="scss">
</style>
