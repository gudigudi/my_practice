<template>
  <div class="paging-demo">
    <el-input v-model="query"></el-input>
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="cons.page"
      :page-sizes="[10, 20, 30, 50]"
      :page-size.sync="cons.pageSize"
      layout="prev, pager, next, sizes"
      :total="cons.totalCount">
    </el-pagination>
  </div>
</template>

<script lang="ts">
import { usePaging } from '@/hooks/paging-query';
import { ref, Ref, watch, createComponent } from '@vue/composition-api';

export default createComponent({
  setup() {
    const { conditions: cons, handleSizeChange, handleCurrentChange } = usePaging();
    const query: Ref<string> = ref('');
    watch([
      () => cons.page,
      () => cons.pageSize,
      () => query.value,
    ], ([val1, val2, val3]) => {
      console.log('conditions changed，do search', val1, val2, val3);
    });
    return {
      cons,
      query,
      handleSizeChange,
      handleCurrentChange,
    };
  },
});
</script>