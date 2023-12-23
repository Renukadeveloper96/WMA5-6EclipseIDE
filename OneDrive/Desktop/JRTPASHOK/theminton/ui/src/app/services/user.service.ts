import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { ToastService } from './toast.service';
import { AppConstant } from '../models/app-constant';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpService: HttpService, private router: Router, private toastService: ToastService) { }

  sigunUp(obj: any) {
    let url = '/auth/signup';
    return this.httpService.post(url, obj);
  }

  authenticateUser(user: string, passcode: string) {
    let url = '/auth/login';
    return this.httpService.post(url, {
      email: user,
      password: passcode
    });
  }

  getUserDetails() {
    let url = '/user/me';
    return this.httpService.get(url);
  }

  changePassword(email: string, password: string) {
    let url = '/user/resetpassword';
    return this.httpService.post(url, { email: email, newPassword: password });
  }

  updateUserDetails(obj: any) {
    let url = '/user/update/' + sessionStorage.getItem(AppConstant.UserId);
    return this.httpService.put(url, obj);
  }
  sendPasswordLink(email: string) {
    let url = '/auth/forgetpassword/sendlink/' + email;
    return this.httpService.get(url);
  }
  resetPassword(obj: any) {
    let url = '/auth/resetpassword';
    return this.httpService.post(url, obj);
  }

  authorizeUserGoogleAuthenticator(obj: any) {
    let url = '/auth/google/login';
    this.httpService.post(url, obj).subscribe((p: any) => {
      sessionStorage.setItem(AppConstant.Token, p.accessToken);
      this.router.navigate(['/dashboard']);
      this.toastService.showSuccess('Login Success'); 
    });

  }

}
