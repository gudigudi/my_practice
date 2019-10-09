import {createStatementData} from './createStatementData';


export interface Performance {
    playID: string;
    audience: number;
}

export interface StatementDataPerformance {
    playID: string;
    audience: number;
    volumeCredits: number;
    amount: number;
    play: Play;
}

export interface Invoice {
    customer: string;
    performances: Performance[];
}

export interface Play {
    name: string;
    type: string;
}

export interface PlayList { [index: string]: Play; }

export interface StatementData {
    totalVolumeCredits: number;
    customer: string;
    performances: StatementDataPerformance[];
    totalAmount: number;
}



export function statement(invoice: Invoice, plays: PlayList) {
    return renderPlainText(createStatementData(invoice, plays));
}

export function htmlStatement(invoice: Invoice, plays: PlayList) {
    return renderHTML(createStatementData(invoice, plays));
}

function renderPlainText(data: StatementData) {
    let result = `Statement for ${data.customer}\n`;

    for (const perf of data.performances) {
        result += `  ${perf.play.name}: ${format(perf.amount)} (${perf.audience} seats)\n`;
    }

    result += `Amount owed is ${format(data.totalAmount)}\nYou earned ${data.totalVolumeCredits} credits\n`;
    return result;
}

function renderHTML(data: StatementData) {
    let result = `<h1>Statement for ${data.customer}</h1>\n`;
    result += '<table>\n';
    result += '<tr><th>play</th><th>seats</th><th>cost</th></tr>';

    for (const perf of data.performances) {
        result += `<tr><td>${perf.play.name}</td><td>${perf.audience}</td><td>${format(perf.amount)}</td></tr>\n`;
    }

    result += '</table>\n';
    result += `<p>Amount owed is <em>${format(data.totalAmount)}</em></p>\n`;
    result += `<p>You earned <em>${data.totalVolumeCredits}</em> credits</p>\n`;

    return result;
}

function format(num: number): string {
    return new Intl.NumberFormat('en-US', {
        style: 'currency', currency: 'USD', minimumFractionDigits: 2,
    }).format(num / 100);
}
