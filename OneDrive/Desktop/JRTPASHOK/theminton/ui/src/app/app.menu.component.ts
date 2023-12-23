import { Component, OnInit } from '@angular/core';
import { AppComponent } from './app.component';

@Component({
    selector: 'app-menu',
    template: `
        <ul class="layout-menu">
            <li app-menuitem *ngFor="let item of model; let i = index;" [item]="item" [index]="i" [root]="true"></li>
        </ul>
    `
})
export class AppMenuComponent implements OnInit {

    model: any[];

    constructor(public app: AppComponent) { }

    ngOnInit() {
        this.model = [
            {

                items: [
                    { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/dashboard'] },
                    { label: 'Engagements', icon: 'pi pi-check-circle', routerLink: ['/dashboard/engagement'] },
                    { label: 'Contact List', icon: 'pi pi-book', routerLink: ['/dashboard/contacts'] },
                    { label: 'Campaigns', icon: 'pi pi-flag-fill', routerLink: ['/dashboard/campaigns'] },
                    { label: 'Insights', icon: 'pi pi-slack', routerLink: ['/dashboard/insights'] },
                    { label: 'Settings', icon: 'pi pi-cog', routerLink: ['/dashboard/setting'] },
                ]
            },

        ];
    }
}
