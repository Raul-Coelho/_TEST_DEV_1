import { Stock } from '../../model/Stock';
import { ConsumeCompaniesService } from './../../service/consume-companies.service';
import { ConsumeStocksService } from './../../service/consume-stocks.service';
import { Company } from './../../model/Company';
import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import { Label, Color } from 'ng2-charts';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  public companies: Company[];
  public company: Company;
  public stocksRecived: Stock[];
  public stock: Stock;
  public headers: Headers;
  public id: number;
  private dateFormated: Date;

  public barChartOptions: ChartOptions = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: { xAxes: [{}], yAxes: [{}] },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };

  public barChartLabels: Label[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  public barChartType: ChartType = 'bar';
  public lineChartColors: Color[] = [
    { // grey
      backgroundColor: '#7b1fa2',
    }
  ];
  public barChartLegend = true;
  public barChartPlugins = [pluginDataLabels];

  public barChartData: ChartDataSets[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
  ];

  constructor(
    private consumeCompanies: ConsumeCompaniesService,
    private consumeStocks: ConsumeStocksService

  ) {
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
  }

  async getStock(id: number) {
    let stock = await this.consumeStocks.getStockId(id);
    this.dateFormated = new Date(stock.datePrice.year, stock.datePrice.monthValue, stock.datePrice.dayOfMonth);
    const newStock = new Stock(stock.id, stock.price, this.dateFormated);

    return newStock;
  }

  async getCompanies() {
    let updatedCompanies = [];
    const companies = await this.consumeCompanies.getCompanies();
    for (const iteratorC of companies) {
      const stockUpdated = [];
      for (const iteratorS of iteratorC.stocks) {
        let newStock = await this.getStock(iteratorS.id);
        stockUpdated.push(newStock);
      }
      const newCompany: Company = { ...iteratorC, stocks: stockUpdated };
      updatedCompanies.push(newCompany);
    }
    this.companies = updatedCompanies;
    console.log(this.companies);
  }

  getChartLabel(labels: Stock[]) {
    let values = [];
    for (const iterator of labels.slice(labels.length - 10, labels.length + 1)) {
      values.push(iterator.datePrice.toDateString());
    }
    return values;
  }

  getChartDataset(name: string, stocks: Stock[]) {
    let values = [];
    for (const iterator of stocks.slice(stocks.length - 10, stocks.length + 1)) {
      values.push(iterator.price);
    }
    const dataSet: ChartDataSets[] = [
      { data: values, label: name }
    ];
    return dataSet;
  }

  getStandardDesviation(stocks: Stock[]) {

    let media = 0;
    let lista = [];

    for (const iterator of stocks) {
      lista.push(iterator.price);
    }

    for (let i = 0; i < lista.length; i++) {
      media += lista[i];
    }
    media = media / lista.length;
    let varianca = 0;
    for (let i = 0; i < lista.length; i++) {
      varianca += (media - lista[i]) * (media - lista[i]);
    }
    varianca = varianca / lista.length;
    return Math.sqrt(varianca);
  }


  ngOnInit() {

    this.companies = [];

  }

  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }


}
