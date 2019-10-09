<template>
  <div class="hooks-one">
    <h2>{{ msg }}</h2>
    <p>count is {{ count }}</p>
    <p>plusOne is {{ plusOne }}</p>
    <button @click="increment">count++</button>
  </div>
</template>

<script lang="ts">
import { setTitle } from '@/hooks/title';
import { ref, computed, watch, onMounted, Ref, createComponent } from '@vue/composition-api';

export default createComponent({
  props: {
    name: String,
  },
  setup(props) {
    const count: Ref<number> = ref(0);
    // computed
    const plusOne = computed(() => count.value + 1);
    // method
    const increment = () => { count.value++; };
    // watch
    watch(() => count.value * 2, (val) => {
      console.log(`count * 2 is ${val}`);
    });
        // lifecycle
    onMounted(() => {
      console.log('onMounted');
      setTitle('首页');
    });
        // expose bindings on render context
    return {
      count,
      plusOne,
      increment,
      msg: `hello ${props.name}`,
    };
  },
});
</script>