import { shallowMount, createWrapper, mount, createLocalVue } from '@vue/test-utils'
import MyFooter from '@/components/MyFooter.vue'
import Button from 'ant-design-vue'

describe('MyFooter.vue', () => {
    it('renders component', () => {
        const wrapper = shallowMount(MyFooter);

        const text = wrapper.find('.info').text();
        const html = wrapper.find('.info').html();
        const classes = wrapper.find('.info').classes();
        const element = wrapper.find('.info').element;

        expect(text).toEqual('Click to modify');
        expect(html).toEqual('<p class="info">Click to modify</p>');
        expect(classes).toEqual(['info']);
        expect(element).toBeInstanceOf(HTMLParagraphElement);

        expect(wrapper).toMatchSnapshot();
    });
    it('should modify the text after calling modify', () => {
        const wrapper = shallowMount(MyFooter);

        (wrapper.vm as any).modify();

        expect(wrapper.vm.$data.info).toEqual('Modified by click');
    });
    it('should modify the text after clicking the button', async () => {
        const wrapper = mount(MyFooter);

        wrapper.find('.ant-btn').trigger('click');
        await wrapper.vm.$nextTick(); // Wait until trigger events have been handled

        const text = wrapper.find('.info').text();
        expect(text).toEqual('Modified by click');
    });
    it('should handle interactions', () => {
        const wrapper = shallowMount(MyFooter, {
            propsData: { info: 'Click to modify' }
        });

        (wrapper.vm as any).modify();
        expect(wrapper.vm.$data.info).toEqual('Modified by click');
        // expect(wrapper.emitted().modify).toEqual([
        //     ['Modified by click']
        // ]);
    });
})
