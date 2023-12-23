import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject, EMPTY, Observable, concatMap, from, map, of } from 'rxjs';
import { environment } from 'src/environments/environment';

declare const FB: any;
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private accountSubject: BehaviorSubject<any>;
  public account: Observable<any>;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private http: HttpClient) {
    this.accountSubject = new BehaviorSubject<any>(null);
    this.account = this.accountSubject.asObservable();
  }


  public get accountValue(): any {
    return this.accountSubject.value;
  }

  login() {
    // login with facebook then authenticate with the API to get a JWT auth token
    this.facebookLogin()
      .pipe(concatMap(accessToken => this.apiAuthenticate(accessToken)))
      .subscribe((p: any) => {
        let accessToken = p.accessToken;
        // get return url from query parameters or default to home page
        const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
        this.router.navigateByUrl(returnUrl);
      });
  }

  facebookLogin() {
    // login with facebook and return observable with fb access token on success
    return from(new Promise<any>(resolve => FB.login(resolve)))
      .pipe(concatMap(({ authResponse }) => {
        if (!authResponse) return EMPTY;
        return of(authResponse);
      }));
  }

  apiAuthenticate(accessToken: string) {
    // authenticate with the api using a facebook access token,
    // on success the api returns an account object with a JWT auth token
    return this.http.post<any>(`http://localhost:4000/authenticate`, { accessToken })
      .pipe(map(account => {
        this.accountSubject.next(account);
        //this.startAuthenticateTimer();
        return account;
      }));
  }

  logout() {
    // revoke app permissions to logout completely because FB.logout() doesn't remove FB cookie
    FB.api('/me/permissions', 'delete', null, () => FB.logout());
    this.accountSubject.next(null);
    this.router.navigate(['/']);
  }

  


}