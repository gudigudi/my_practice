<script lang='ts'>
import { ref, computed, Ref, watch, createComponent } from '@vue/composition-api';
import { userSearch, IOption } from '@/hooks/user-search';

export default createComponent({
  setup(props, { emit, root }) {
    const isFirstFoucs: Ref<boolean> = ref(false);
    const showCheckbox: Ref<boolean> = ref(true);
    // computed
    // 当前选中选项
    const chooseItems: Ref<string | string[]> = ref(computed(() => props.user));
    // 选项去重(包含对象的情况)
    const uniqueOptions = computed(() => {
      const originArr: IOption[] | any = props.customSearch ? props.options : items.value;
      const newArr: IOption[] = [];
      const strArr: string[] = [];
      originArr.forEach((item: IOption) => {
        if (!strArr.includes(JSON.stringify(item))) {
          strArr.push(JSON.stringify(item));
          newArr.push(item);
        }
      });
      return newArr;
    });
    // watch
    watch(() => chooseItems.value, (val) => {
      emit('update:user', val);
      emit('change', val);
    });
        // methods
    const remoteMethod = (query: string) => {
      // 可抛出去自定义，也可使用内部集成好的方法处理 remote
      if (props.customSearch) {
        emit('search', query);
      } else {
        handleUserSearch(query);
      }
    };
    const handleFoucs = (event) => {
      if (isFirstFoucs.value) {
        return false;
      }
      remoteMethod(event.target.value);
      isFirstFoucs.value = true;
    };
    const handleOptionClick = (item) => {
      emit('option-click', item);
    };
    // 显示勾选状态，若是单选则无需显示 checkbox
    const isChecked = (value: string) => {
      let checked: boolean = false;
      if (typeof chooseItems.value === 'string') {
        showCheckbox.value = false;
        return false;
      }
      chooseItems.value.forEach((item: string) => {
        if (item === value) {
          checked = true;
        }
      });
      return checked;
    };
    return {
      isFirstFoucs, showCheckbox, // ref
      uniqueOptions, chooseItems, // computed
      handleUserSearch, remoteMethod, handleFoucs, handleOptionClick, isChecked, // methods
    };
  },
});
</script>