import { DatePrice } from './DatePrice';


export class StockMockup {
    constructor(
        public id: number,
        public price: number,
        public datePrice: DatePrice,
        public company: number
    ) { }
}