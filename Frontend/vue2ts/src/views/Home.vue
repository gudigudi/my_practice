<template>
  <div class="home">
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App" />
    <Hooks/>
    <PagingDemo/>
    <user-select :user.sync="user" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import HelloWorld from '@/components/HelloWorld.vue';
import Hooks from '@/components/Hooks.vue';
import PagingDemo from '@/components/PagingDemo.vue';
import UserSelect from '@/components/UserSelect.vue';

import { userSearch, IOption } from '@/hooks/user-search';

@Component({
  components: {
    HelloWorld,
    Hooks,
    PagingDemo,
    UserSelect,
},
})
export default class Home extends Vue {}


// export default createComponent({
//   setup (props, { emit, root }) {
//     const { items, handleUserSearch } = userSearch({ root })
//   }
// })
</script>
