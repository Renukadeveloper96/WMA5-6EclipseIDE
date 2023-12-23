import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppConstant } from 'src/app/models/app-constant';
import { ToastService } from 'src/app/services/toast.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  changepassword: FormGroup;
  isLoggedInformSocialMedia: boolean = false;

  constructor(private fb: FormBuilder, private toastService: ToastService, private userService: UserService) {
  }

  ngOnInit(): void {
    this.changepassword = this.fb.group({
      passcode: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
      repasscode: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(15)]]
    });

    if (sessionStorage.getItem(AppConstant.Provider))
      this.isLoggedInformSocialMedia = true
  }

  updatePassword() {
    if (this.changepassword.value.passcode.length < 3 || this.changepassword.value.passcode.length > 15) {
      this.toastService.showError('Password length should be between 3-15 characters')
      return;
    }
    else if (this.changepassword.value.repasscode.length < 3 || this.changepassword.value.repasscode.length > 15) {
      this.toastService.showError('Password length should be between 3-15 characters')
      return;
    }
    else if (this.changepassword.value.repasscode != this.changepassword.value.passcode) {
      this.toastService.showError('Password does not match with confirm password')
      return;
    }
    else if (this.changepassword.valid) {
      this.userService.changePassword(sessionStorage.getItem(AppConstant.Email), this.changepassword.value.passcode).subscribe((p: any) => {
        if (p.success && p.message) {
          this.toastService.showSuccess(p.message);
          this.changepassword.reset();
        }
      }
      );
    }

  }
}
