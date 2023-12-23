import { Component, OnInit } from '@angular/core';
import { ToastService } from '../services/toast.service';
import { EmailValidator, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AppConstant } from '../models/app-constant';
import { EmailValidatorService } from '../services/email-validator.service';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService, SocialUser } from '@abacritt/angularx-social-login';
import { AuthenticationService } from '../services/authentication.service';
import { error } from 'console';

@Component({
  selector: 'app-login',
  templateUrl: './app.login.component.html',
})
export class AppLoginComponent implements OnInit {

  email: string = '';
  passcode: string = '';
  message: string = 'Hello world';
  signUpForm: FormGroup;
  socialUser!: SocialUser;
  isLoggedin?: boolean;
  login: boolean = true;
  forgetPassword: boolean = false;
  signUp: boolean = false;


  constructor(
    private toastService: ToastService,
    private form: FormBuilder,
    private userService: UserService,
    private router: Router,
    private emailValidator: EmailValidatorService,
    private socialAuthService: SocialAuthService,
    private authenticationService: AuthenticationService) {
  }
  ngOnInit(): void {
    this.showPanel('login');

    this.signUpForm = this.form.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required]],
      password: ['', [Validators.required]],
      rePassword: ['', [Validators.required]]
    });

    this.socialAuthService.authState.subscribe((user) => {
      this.socialUser = user;
      this.isLoggedin = user != null;
      console.log(this.socialUser);

      sessionStorage.setItem(AppConstant.FirstName, this.socialUser.firstName);
      sessionStorage.setItem(AppConstant.LastName, this.socialUser.lastName);
      sessionStorage.setItem(AppConstant.Email, this.socialUser.email);
      sessionStorage.setItem(AppConstant.UserId, this.socialUser.id);
      sessionStorage.setItem(AppConstant.Provider, this.socialUser.provider);
      this.userService.authorizeUserGoogleAuthenticator(
        {
          firstName: this.socialUser.firstName,
          lastName: this.socialUser.lastName,
          email: this.socialUser.email,
          provider: this.socialUser.provider
        }
      );
    });
  }
  
  authenticateUser() {
    if (this.email == null || this.email == '') {
      this.toastService.showError('Please enter username');
    } else if (this.passcode == null || this.passcode == '') {
      this.toastService.showError('Please enter password');
    }
    else {
      this.userService.authenticateUser(this.email, this.passcode).subscribe((p: any) => {
        sessionStorage.setItem(AppConstant.Token, p.accessToken)
        this.userService.getUserDetails().subscribe((p: any) => {
          sessionStorage.setItem(AppConstant.FirstName, p.firstName);
          sessionStorage.setItem(AppConstant.LastName, p.lastName);
          sessionStorage.setItem(AppConstant.Email, p.email);
          sessionStorage.setItem(AppConstant.Phonenumber, p.phoneNumber);
          sessionStorage.setItem(AppConstant.UserId, p.id);

          this.toastService.showSuccess('Login Success');
          this.router.navigate(['/dashboard']);
        });
      });
    }
  }

  sendPasswordLink() {
    if (!this.emailValidator.isEmailValid(this.email)) {
      this.toastService.showError('Please enter proper email address');
      return;
    }
    else {
      this.userService.sendPasswordLink(this.email).subscribe((p: any) => {
        if (p.success) {
          this.toastService.showInfo(p.message)
          this.router.navigate(['/']);
        }
      });
    }
  }

  showPanel(panelName: string) {
    switch (panelName) {
      case 'login':
        this.login = true;
        this.forgetPassword = false;
        this.signUp = false;
        break;
      case 'forgetPassword':
        this.login = false;
        this.forgetPassword = true;
        this.signUp = false;
        break;
      case 'signUp':
        this.login = false;
        this.forgetPassword = false;
        this.signUp = true;
        break;

    }
  }

  loginWithGoogle(): void {
    this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }
  logOut(): void {
    this.socialAuthService.signOut();
  }

  signUpDetails() {
    if (this.signUpForm.valid) {
      if (!this.emailValidator.isEmailValid(this.signUpForm.value.email)) {
        this.toastService.showError('Email address is not valid');
        return;
      }
      else if (this.signUpForm.value.password != this.signUpForm.value.rePassword) {
        this.toastService.showError('Password doesn\'t matches with confirm password');
        return;
      }
      else {

        let obj = {
          name: '',
          firstName: this.signUpForm.value.firstName,
          lastName: this.signUpForm.value.lastName,
          phoneNumber: this.signUpForm.value.phoneNumber,
          email: this.signUpForm.value.email,
          password: this.signUpForm.value.password
        }
        this.userService.sigunUp(obj).subscribe(p => {
          if (p) {
            this.toastService.showSuccess('User account has been registered');
            this.signUpForm.reset();
          }
        });
      }

    } else {
      this.toastService.showError('Please enter valid input in form');
    }
  }
  authenticateGmail() {
    window.location.href = environment.apiURL + '/oauth2/authorize/google?redirect_uri=' + environment.redirectURL;
  }
  authenticateFacebook() {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }
}
