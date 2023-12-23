import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-campaings',
  templateUrl: './campaings.component.html',
  styleUrls: ['./campaings.component.scss']
})
export class CampaingsComponent implements OnInit {
  campaigns: any = [];
  showAddNotes: boolean = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.getCampaigns();
  }
  getCampaigns() {
    this.campaigns = [
      { name: 'Campaigns 1', active: 'True', startOn: new Date(), endOn: new Date(), createdBy: 'Prabhat Kumar', groups: 'Group 1', engage: 'Engage 1', comMethod: 'WhatsApp' }
    ];
  }

  moveToDetails() {
    this.router.navigate(['/dashboard/add-campaign']);
  }

  showNotes() {
    this.showAddNotes = true;
  }
}
