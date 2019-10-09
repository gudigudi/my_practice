import { ref, Ref } from '@vue/composition-api';

export interface IOption {
  [key: string]: string;
}

export function userSearch({ root }) {
  const items: Ref<IOption[]> = ref([]);

  const handleUserSearch = (query: string) => {
    root.$store.dispatch('userSearch', { search: query, pageSize: 25 }).then((res) => {
      items.value = res.data.items;
    });
  };

  return { items, handleUserSearch };
}
