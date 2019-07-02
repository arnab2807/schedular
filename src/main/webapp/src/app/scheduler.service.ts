import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SchedulerService {

  constructor(private httpClient: HttpClient) { }

  performGet(url: string, headers?: HttpHeaders): Observable<any> {
    return this.httpClient.get(url);
  }

  performPost(url: string, body?: any, headers?: HttpHeaders): Observable<any> {
    return this.httpClient.post(url, body);
  }
}
