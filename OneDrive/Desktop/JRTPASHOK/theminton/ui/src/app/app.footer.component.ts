import { Component } from '@angular/core';
import { AppComponent } from './app.component';

@Component({
    selector: 'app-footer',
    template: `
        <div class="layout-footer">
            <a id="footerlogolink">
               @2023
            </a>
            <div class="social-icons">
                
            </div>
        </div>
    `
})
export class AppFooterComponent {

    constructor(public app: AppComponent) { }


}
