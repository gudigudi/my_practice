import { Play, Performance } from '@/statement';
import { createPerformanceCalculator, ComedyCalculator } from '@/performanceCalculator';

describe('performanceCalculator', () => {
    describe('func createPerformanceCalculator', () => {
        it('throw error: play.type', () => {
            const wrongPlayType = 'errorType'
            const play: Play = { name: 'Hamlet', type: wrongPlayType };
            const perf: Performance = { playID: 'hamlet', audience: 55 };

            expect(() => { createPerformanceCalculator(perf, play) }).toThrow(`unknown type: ${wrongPlayType}`)
        })
        it('success', () => {
            const play: Play = { name: 'Hamlet', type: 'comedy' };
            const perf: Performance = { playID: 'hamlet', audience: 55 };

            expect(createPerformanceCalculator(perf, play)).toBeInstanceOf(ComedyCalculator)
        })
    })
    describe('class TragedyCalculator', () => {
        it('method amount: when performance.audience > 30', () => {
            const play: Play = { name: 'Hamlet', type: 'tragedy' };
            const perf: Performance = { playID: 'hamlet', audience: 55 };
            const cal = createPerformanceCalculator(perf, play);

            expect(cal.amount).toBe(65000)
        })
        it('method amount: when performance.audience <= 30', () => {
            const play: Play = { name: 'Hamlet', type: 'tragedy' };
            const perf: Performance = { playID: 'hamlet', audience: 15 };
            const cal = createPerformanceCalculator(perf, play);

            expect(cal.amount).toBe(40000)
        })
    })
    describe('class ComedyCalculator', () => {
        it('method amount: when performance.audience > 20', () => {
            const play: Play = { name: 'Hamlet', type: 'comedy' };
            const perf: Performance = { playID: 'hamlet', audience: 55 };
            const cal = createPerformanceCalculator(perf, play);

            expect(cal.amount).toBe(74000)
        })
        it('method amount: when performance.audience <= 20', () => {
            const play: Play = { name: 'Hamlet', type: 'comedy' };
            const perf: Performance = { playID: 'hamlet', audience: 15 };
            const cal = createPerformanceCalculator(perf, play);

            expect(cal.amount).toBe(34500)
        })
    })
})