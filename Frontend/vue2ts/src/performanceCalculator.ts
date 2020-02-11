import { Performance, Play } from '@/statement';

export abstract class PerformanceCalculator {
    public performance: Performance;
    public play: Play;

    constructor(perf: Performance, play: Play) {
        this.performance = perf;
        this.play = play;
    }

    abstract get amount(): number;

    get volumeCredits() {
        return Math.max(this.performance.audience - 30, 0);
    }
}

export class TragedyCalculator extends PerformanceCalculator {
    get amount(): number {
        let result = 40000;
        if (this.performance.audience > 30) {
            result += 1000 * (this.performance.audience - 30);
        }

        return result;
    }
}

export class ComedyCalculator extends PerformanceCalculator {
    get amount(): number {
        let result = 30000;
        if (this.performance.audience > 20) {
            result += 10000 + 500 * (this.performance.audience - 20);
        }
        result += 300 * this.performance.audience;

        return result;
    }

    get volumeCredits() {
        return super.volumeCredits + Math.floor(this.performance.audience / 5);
    }

}

export function createPerformanceCalculator(perf: Performance, play: Play): PerformanceCalculator {
    switch (play.type) {
        case 'tragedy':
            return new TragedyCalculator(perf, play);
        case 'comedy':
            return new ComedyCalculator(perf, play);
        default:
            throw new Error(`unknown type: ${play.type}`);
    }
}
