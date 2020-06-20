import { StockMockup } from '../model/StockMockup';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConsumeStocksService {

  private readonly API = `${environment.API}/stocks`;

  constructor(private http: HttpClient) { }

  getStocks(): Promise<StockMockup[]> {
    return this.http.get<StockMockup[]>(this.API).toPromise();
  }

  getStockId(id: number): Promise<StockMockup> {
    return this.http.get<StockMockup>(this.API + "/" + id).toPromise();
  }
}
