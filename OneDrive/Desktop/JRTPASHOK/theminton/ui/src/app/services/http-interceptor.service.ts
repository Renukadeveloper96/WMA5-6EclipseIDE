import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AppConstant } from '../models/app-constant';
import { ToastService } from './toast.service';
import { LoadingService } from './loading.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private route: Router, private toastService: ToastService, private loadingService: LoadingService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    this.loadingService.showloading = true;

    return next.handle(this.addAuthToken(request)).pipe(
      tap({
        next: (event) => {
          if (event instanceof HttpResponse) {
            if (event.status == 401) {
              this.toastService.showError('Unauthorized access!');
              setTimeout(window.location.href = environment.appUrl, 1000);
            }
          }
          return event;
        },
        error: (error) => {
          if (error.status === 400) {
            this.toastService.showError(error.error.errorDescription);
          }
          if (error.status === 401) {
            this.toastService.showError('Unauthorized access!');
            setTimeout(window.location.href = environment.appUrl, 1000);
          }
          else if (error.status === 404) {
            this.toastService.showError('Not Found!')
          }
          else if (error.status === 500) {
            this.toastService.showError('Opps! Somthing went wrong');
            console.error(error);
          }
        }, finalize: () => this.loadingService.showloading = false
      }));

  }
  addAuthToken(request: HttpRequest<any>) { 
    const token = sessionStorage.getItem(AppConstant.Token);
    if (token != null && token != '') {
      return request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token
        }
      });
    }
    else {
      return request.clone();
    }
  }



}
