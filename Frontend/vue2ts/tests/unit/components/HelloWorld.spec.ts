import { shallowMount, Wrapper } from '@vue/test-utils'
import HelloWorld from '@/components/HelloWorld.vue'

let wrapper: Wrapper<Vue>

afterEach(() => {
    //TODO reset prop "count"
    (wrapper.vm as any).setCount(0)
    wrapper.destroy();
});

describe('HelloWorld.vue', () => {
    it('renders props.msg when passed', () => {
        const msg = 'new message'
        wrapper = shallowMount(HelloWorld, {
            propsData: { msg }
        })
        expect(wrapper.props('msg')).toBe(msg)
        expect(wrapper.find('p').text()).toBe('count:' + '0')
        expect(wrapper).toMatchSnapshot()
    })
    it('getter/setter of prop count', async () => {
        wrapper = shallowMount(HelloWorld);

        await (wrapper.vm as any).setCount(10)

        expect(wrapper.find('p').text()).toBe('count:' + '10')
    })
    it('click button to add and sub count', async () => {
        wrapper = shallowMount(HelloWorld);

        const btns = wrapper.findAll('button')
        let btnAdd = btns.at(0)
        let btnSub = btns.at(1)

        await btnAdd.trigger('click')
        expect(wrapper.find('p').text()).toBe('count:' + '1')
        await btnSub.trigger('click')
        expect(wrapper.find('p').text()).toBe('count:' + '0')
    })
})
