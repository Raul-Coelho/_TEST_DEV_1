import { Stock } from './Stock';

export class Company {
    constructor(
        public id: number,
        public name: string,
        public segment: string,
        public stocks: Stock[],

    ) {
        this.stocks = [];
    }
}