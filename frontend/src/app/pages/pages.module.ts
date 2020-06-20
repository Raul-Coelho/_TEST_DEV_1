import { DashboardComponent } from './dashboard/dashboard.component';
import { PagesRoutingModule } from './pages-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PagesComponent } from './pages.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { ChartsModule } from 'ng2-charts';
import { MatListModule } from '@angular/material/list';



@NgModule({
  declarations: [
    PagesComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    PagesRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatGridListModule,
    ChartsModule,
    MatListModule
  ]
})
export class PagesModule { }
