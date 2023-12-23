import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-campaign',
  templateUrl: './add-campaign.component.html',
  styleUrls: ['./add-campaign.component.scss']
})
export class AddCampaignComponent {

  constructor(private router: Router) { }

  moveBack() {
    this.router.navigate(['/dashboard/campaigns']);
  }

}
