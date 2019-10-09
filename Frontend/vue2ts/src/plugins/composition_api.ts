import Vue from 'vue';
import plugin from '@vue/composition-api';

Vue.use(plugin);

declare module '@vue/composition-api/dist/component/component' {
    interface SetupContext {
        readonly refs: { [key: string]: Vue | Element | Vue[] | Element[] };
    }
}
