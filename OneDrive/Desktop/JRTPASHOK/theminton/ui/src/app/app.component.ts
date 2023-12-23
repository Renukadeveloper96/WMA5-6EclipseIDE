import { Component, OnInit } from '@angular/core';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { LoadingService } from './services/loading.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

    topbarTheme = 'light';

    menuTheme = 'dim';

    layoutMode = 'light';

    menuMode = 'static';

    isRTL = false;

    inputStyle = 'outlined';

    ripple: boolean;

    constructor(private primengConfig: PrimeNGConfig, public loadService: LoadingService) { }

    ngOnInit() {
        this.primengConfig.ripple = true;
    }
}
