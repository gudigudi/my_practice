import { Invoice, PlayList, StatementData, StatementDataPerformance, Performance, Play } from './statement';
import { createPerformanceCalculator } from '@/performanceCalculator';
export function createStatementData(invoice: Invoice, plays: PlayList): StatementData {
    const statementData: StatementData = {} as StatementData;
    statementData.customer = invoice.customer;
    statementData.performances = invoice.performances.map(enrichPerformance) as StatementDataPerformance[];
    statementData.totalAmount = totalAmount(statementData);
    statementData.totalVolumeCredits = totalVolumeCredits(statementData);
    return statementData;
    function enrichPerformance(perf: Performance) {
        const calculator = createPerformanceCalculator(perf, playFor(perf));
        const result = Object.assign({}, perf) as StatementDataPerformance;
        result.play = calculator.play;
        result.amount = calculator.amount;
        result.volumeCredits = calculator.volumeCredits;
        return result;
    }
    function playFor(perf: Performance): Play {
        return plays[perf.playID];
    }
    function totalAmount(data: StatementData): number {
        return data.performances.reduce((total, p) => total += p.amount, 0);
    }
    function totalVolumeCredits(data: StatementData): number {
        return data.performances.reduce((total, p) => total + p.volumeCredits, 0);
    }
}
