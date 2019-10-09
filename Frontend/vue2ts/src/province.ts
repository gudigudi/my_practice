
interface producerData {
    name: string;
    cost: number;
    production: number;
}

export class Producer {
    public _name: string;
    public _cost: number;
    public _production: number;
    public _province: Province;

    constructor(province: Province, data: producerData) {
        this._province = province;
        this._name = data.name;
        this._cost = data.cost;
        this._production = data.production || 0;
    }

    get name() { return this._name; }
    set name(name: string) { this._name = name; }
    get cost() { return this._cost; }
    set cost(cost: number) { this._cost = cost; }
    get production() { return this._production; }
    set production(amount: number) {
        this._province.totalProduction += amount + this._production;
        this._production = amount;
    }
}

export interface Doc {
    name: string;
    demand: number;
    price: number;
    producers: Producer[];
}

export class Province {
    public _name: string;
    public _producers: Producer[];
    public _totalProduction: number;
    public _demand: number;
    public _price: number;

    constructor(doc: Doc) {
        this._name = doc.name;
        this._producers = [];
        this._totalProduction = 0;
        this._demand = doc.demand;
        this._price = doc.price;
        doc.producers.forEach((e) => {
            this.addProducer(new Producer(this, e));
        });
    }

    public addProducer(producer: Producer) {
        this._producers.push(producer);
        this._totalProduction += producer._production;
    }

    get name() { return this._name; }
    get producers() { return this._producers.slice(); }
    get totalProduction() { return this._totalProduction; }
    set totalProduction(total: number) { this._totalProduction = total; }
    get demand() { return this._demand; }
    set demand(demand: number) { this._demand = demand; }
    get price() { return this._price; }
    set price(price: number) { this._price = price; }
    get shortfall() {
        return this._demand - this.totalProduction;
    }
    get profit() {
        return this.demandValue - this.demandCost;
    }
    get demandValue() {
        return this.satisfiedDemand * this.price;
    }
    get demandCost() {
        let remainingDemand = this.demand;
        let result = 0;

        this.producers
            .sort((a, b) => a.cost - b.cost)
            .forEach((p) => {
                const contribution = Math.min(remainingDemand, p.production);
                remainingDemand -= contribution;
                result += contribution * p.cost;
            });
        return result;
    }
    get satisfiedDemand() {
        return Math.min(this._demand, this.totalProduction);
    }

}
