import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Company } from '../model/Company';


@Injectable({
  providedIn: 'root'
})
export class ConsumeCompaniesService {

  private readonly API = `${environment.API}/companies`;

  constructor(private http: HttpClient) { }

  getCompanies(): Promise<Company[]> {
    return this.http.get<Company[]>(this.API).toPromise();
  }

  // getFon() {
  //   this.http.get(this.API).subscribe(response => {
  //     console.log(response);
  //   });
  // }
}
