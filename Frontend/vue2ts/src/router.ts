import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/Home.vue'),
    },
    {
      path: '/container-demo',
      name: 'container-demo',
      component: () => import('@/views/ContainerDemo.vue'),
    },
    {
      path: '/layout-demo',
      name: 'layout-demo',
      component: () => import('@/views/LayoutDemo.vue'),
    },
    {
      path: '/button-demo',
      name: 'button-demo',
      component: () => import('@/views/ButtonDemo.vue'),
    },
    {
      path: '/form-demo',
      name: 'form-demo',
      component: () => import('@/views/FormDemo.vue'),
      children: [
        {
          path: '/form-demo/radio-demo',
          name: 'radio-demo',
          component: () => import('@/components/elementui/form/RadioDemo.vue'),
        },
        {
          path: '/form-demo/checkbox-demo',
          name: 'checkbox-demo',
          component: () =>
            import('@/components/elementui/form/CheckboxDemo.vue'),
        },
        {
          path: '/form-demo/input-demo',
          name: 'input-demo',
          component: () => import('@/components/elementui/form/InputDemo.vue'),
        },
        {
          path: '/form-demo/inputnumber-demo',
          name: 'inputnumber-demo',
          component: () =>
            import('@/components/elementui/form/InputNumberDemo.vue'),
        },
        {
          path: '/form-demo/select-demo',
          name: 'select-demo',
          component: () => import('@/components/elementui/form/SelectDemo.vue'),
        },
        {
          path: '/form-demo/cascader-demo',
          name: 'cascader-demo',
          component: () =>
            import('@/components/elementui/form/CascaderDemo.vue'),
        },
        {
          path: '/form-demo/switch-demo',
          name: 'switch-demo',
          component: () => import('@/components/elementui/form/SwitchDemo.vue'),
        },
        {
          path: '/form-demo/slider-demo',
          name: 'slider-demo',
          component: () => import('@/components/elementui/form/SliderDemo.vue'),
        },
        {
          path: '/form-demo/timepicker-demo',
          name: 'timepicker-demo',
          component: () =>
            import('@/components/elementui/form/TimePickerDemo.vue'),
        },
      ],
    },
  ],
});
