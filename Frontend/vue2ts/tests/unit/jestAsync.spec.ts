import axios from 'axios'

function getData1() {
    return axios.get('http://www.dell-lee.com/react/api/demo.json')
}

function getData2(fn: Function) {
    axios.get('http://www.dell-lee.com/react/api/demo.json').then(res => {
        fn(res)
    })
}

function get404() {
    return axios.get('http://www.dell-lee.com/react/api/404.json')
}

describe('jest async testcase', () => {
    it('getData1 方法1', (done) => {
        getData1().then(res => {
            expect(res.data).toEqual({
                success: true
            })
            done()  // 如果不加 done，还没执行到 .then 方法，测试用例已经结束了
        })
    })

    it('getData1 方法2', () => {
        return getData1().then(res => {
            expect(res.data).toEqual({

                success: true
            })
        })
    })

    it('getData2 方法2', (done) => {
        getData2((res: any) => {
            expect(res.data).toEqual({
                success: true
            })
            done()
        })
    })

    it('getData1 方法3', async () => {
        const res = await getData1()
        expect(res.data).toEqual({
            success: true
        })
    })

    /*********** 重点关注 ***********/
    it('get404', (done) => {
        expect.assertions(1)
        get404().catch(r => {
            expect(r.toString()).toMatch('404')
            done()
        })
    })
})