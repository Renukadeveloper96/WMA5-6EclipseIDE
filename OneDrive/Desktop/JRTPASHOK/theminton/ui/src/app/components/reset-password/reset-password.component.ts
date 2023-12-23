import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from 'src/app/services/toast.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {
  password: string;
  token: string;
  isformDisabled: boolean;

  constructor(
    private route: ActivatedRoute,
    private toastMessage: ToastService,
    private userService: UserService,
    private router: Router) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params.token == null || params.token == '') {
        this.toastMessage.showError('Unable to validate user');
        this.isformDisabled = true;
      }
      else {
        this.token = params.token;
        this.isformDisabled = false;
      }
    });
  }

  resetPassword() {
    if (this.password.length >= 1) {
      this.userService.resetPassword({
        token: this.token,
        password: this.password
      }).subscribe(p => {
        if (p) {
          this.toastMessage.showSuccess('Password has been reset');
          this.router.navigate(['/']);
        }
      })
    }
    else {
      this.toastMessage.showError('Please enter password');
    }
  }
}
