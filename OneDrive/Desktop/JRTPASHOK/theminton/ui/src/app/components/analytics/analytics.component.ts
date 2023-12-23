import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.scss']
})
export class AnalyticsComponent implements OnInit {

  reports: any = [];
  selectedReport: any;
  products: any = [];

  ngOnInit(): void {
    this.getReports();
    this.getProducts();
  }

  getReports() {
    this.reports = [
      { name: 'Consolidated View', code: 'CV' },
      { name: 'Finance', code: 'CV' },
      { name: 'Module 2', code: 'CV' },
      { name: 'Module 3', code: 'CV' },
      { name: 'Module 4', code: 'CV' }
    ]
  }

  getProducts() {
    this.products = [{ name: 'Campaing1', first: 80, second: 30, third: 10, fourth: 20, fifth: 15 }]
  }


}
