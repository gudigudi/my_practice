import { Province, Doc, Producer } from '@/province';

function AdapteProvinceDoc(obj: { name: string, demand: number, price: number, producers: Array<{ name: string, cost: number, production: number }> }) {
    const doc: Doc = {} as Doc;
    doc.name = obj.name;
    doc.demand = obj.demand;
    doc.price = obj.price;
    doc.producers = Array<Producer>();

    for (const p of obj.producers) {
        const producer: Producer = {} as Producer;
        producer.name = p.name;
        producer.cost = p.cost;
        producer.production = p.production;
        doc.producers.push(producer);
    }
    return doc;
}

describe('producer', () => {
    it('setter/getter', () => {
        const data = {
            name: 'Asia',
            producers: [
                { name: 'Byzantium', cost: 10, production: 9 },
                { name: 'Attalia', cost: 12, production: 10 },
                { name: 'Sinope', cost: 10, production: 6 },
            ],
            demand: 30,
            price: 20,
        };
        const province = new Province(AdapteProvinceDoc(data));
        const producerData = data.producers[0];
        let producer = new Producer(province, producerData);
        producer.name = "hello"
        expect(producer.name).toBe('hello')
        producer.cost = 100
        expect(producer.cost).toBe(100)
    })
})

describe('province', () => {
    let asia: Province;

    beforeEach(() => {
        const data = {
            name: 'Asia',
            producers: [
                { name: 'Byzantium', cost: 10, production: 9 },
                { name: 'Attalia', cost: 12, production: 10 },
                { name: 'Sinope', cost: 10, production: 6 },
            ],
            demand: 30,
            price: 20,
        };
        asia = new Province(AdapteProvinceDoc(data));
    });
    it('setter/getter', () => {
        expect(asia.name).toBe('Asia')
        asia.price = 1000
        expect(asia.price).toBe(1000)
    });
    it('shortfall', () => {
        expect(asia.shortfall).toBe(5);
    });
    it('profit', () => {
        expect(asia.profit).toBe(230);
    });
    it('change production', () => {
        asia.producers[0].production = 20;
        expect(asia.shortfall).toBe(-24);
        expect(asia.profit).toBe(292);
    });
    it('zero demand', () => {
        asia.demand = 0;
        expect(asia.shortfall).toBe(-25);
        expect(asia.profit).toBe(0);
    });
    it('negative demand', () => {
        asia.demand = -1;
        expect(asia.shortfall).toBe(-26);
        expect(asia.profit).toBe(-10);
    });
});

describe('no producers', () => {
    let noProducers: Province;

    beforeEach(() => {
        const data = {
            name: 'No producers',
            producers: [],
            demand: 30,
            price: 20,
        };
        noProducers = new Province(AdapteProvinceDoc(data));
    });

    it('shortfall', () => {
        expect(noProducers.shortfall).toBe(30);
    });
    it('profit', () => {
        expect(noProducers.profit).toBe(0);
    });
});
