import { Component, OnInit } from '@angular/core';
import { AppComponent } from './app.component';
import { AppMainComponent } from './app.main.component';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AppConstant } from './models/app-constant';
import { Session } from 'inspector';

@Component({
    selector: 'app-topbar',
    template: `
        <div class="layout-topbar">
            <div class="layout-topbar-wrapper">
                <div class="layout-topbar-left">
                    <div class="layout-topbar-logo" id="logolink" style="cursor: pointer; outline: none;" routerLink="/">
                        <img id="app-logo"
                             [src]="'assets/layout/images/logo-' + (app.topbarTheme === 'light' ? 'light' : 'dark') + '.png'"
                             alt="poseidon-layout">
                    </div>
                </div>

                <div class="layout-topbar-right">
                    <a class="menu-button" href="#" (click)="appMain.onMenuButtonClick($event)">
                        <i class="pi pi-bars"></i>
                    </a>

                    <ul class="layout-topbar-actions">   
                         
                        <li #profile class="topbar-item user-profile"
                            [ngClass]="{'active-topmenuitem':appMain.activeTopbarItem === profile}">
                            <a href="#" (click)="appMain.onTopbarItemClick($event,profile)">
                                <img class="profile-image" src="assets/layout/images/avatar-profile.png" alt="demo">
                                <div class="profile-info">
                                    <h6>{{username}}</h6>
                                    <span></span>
                                </div>
                            </a>

                            <ul class="fadeInDown">
                                <li class="layout-submenu-header">
                                    <img class="profile-image" src="assets/layout/images/avatar-profile.png" alt="demo">
                                    <div class="profile-info">
                                        <h6>{{username}}</h6>
                                        <span></span>
                                    </div>
                                </li> 
                                <li role="menuitem">
                                    <a href="#" [routerLink]="['/dashboard/profile']" (click)="appMain.onTopbarSubItemClick($event)">
                                        <i class="pi pi-user"></i>
                                        <h6>Profile</h6>
                                    </a>
                                </li>
                                <li role="menuitem">
                                    <a href="#" [routerLink]="['/dashboard/password']" (click)="appMain.onTopbarSubItemClick($event)">
                                        <i class="pi pi-lock"></i>
                                        <h6>Change Password</h6>
                                    </a>
                                </li>
                                <li role="menuitem">
                                    <a href="#" (click)="signOut()">
                                        <i class="pi pi-power-off"></i>
                                        <h6>Logout</h6>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul> 
                     
                </div>
            </div>
        </div>
    `
})
export class AppTopBarComponent implements OnInit {
    username: string;
    constructor(public appMain: AppMainComponent, public app: AppComponent, private router: Router) {
    }
    ngOnInit(): void {
        this.username = sessionStorage.getItem(AppConstant.FirstName) + " " + sessionStorage.getItem(AppConstant.LastName);
    }
    signOut() {
        sessionStorage.clear();
        window.location.href = environment.appUrl;
    }

}
