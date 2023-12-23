import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppConstant } from 'src/app/models/app-constant';
import { ToastService } from 'src/app/services/toast.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  profileForm: FormGroup;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;

  constructor(private fb: FormBuilder, private userService: UserService, private toastService: ToastService) { }

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      lastName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      email: [''],
      phoneNumber: ['', [Validators.required]],
      userType: ['Premium'],
      role: ['USER']
    });


    this.firstName = sessionStorage.getItem(AppConstant.FirstName);
    this.lastName = sessionStorage.getItem(AppConstant.LastName);
    this.email = sessionStorage.getItem(AppConstant.Email);
    this.phoneNumber = sessionStorage.getItem(AppConstant.Phonenumber);
  }

  updateDetails() {
    if (this.profileForm.valid) {
      this.userService.updateUserDetails(this.profileForm.value).subscribe(p => {
        if (p) {
          this.toastService.showSuccess('Profile has been updated');
        }

      });
    }
  }

}
