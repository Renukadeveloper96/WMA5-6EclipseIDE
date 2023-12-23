import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  apiUrl: string = environment.apiURL
  constructor(private http: HttpClient) { }


  get<T>(url): Observable<T> {
    return this.http.get<T>(this.apiUrl + url);
  }

  post<T>(url, obj): Observable<T> {
    return this.http.post<T>(this.apiUrl + url, obj);
  }

  put<T>(url, obj): Observable<T> {
    return this.http.put<T>(this.apiUrl + url, obj);
  }
  delete<T>(url): Observable<T> {
    return this.http.delete<T>(this.apiUrl + url);
  }
}
