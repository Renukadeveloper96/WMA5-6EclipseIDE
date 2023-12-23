import { Component } from '@angular/core';
import { SelectItem } from 'primeng/api/selectitem';
import { SettingService } from 'src/app/services/setting.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {
  billingPlans: any;
  selectedPlan: SelectItem;
  valCheckWhatsapp: boolean = false;
  valCheckTelegram: boolean = false;
  noOfUsersLimit: number;

  constructor(
    private settingService: SettingService,
    private toastService: ToastService) {

  }

  ngOnInit(): void {
    this.billingPlans = [{ name: 'Free Plan', code: 'Free Plan' }];
    this.settingService.getSetting().subscribe(settings => {
      this.selectedPlan = settings[0].billingPlan;
      this.noOfUsersLimit = settings[0].noOfUsers;
      this.valCheckTelegram = settings[0].isTelegram;
      this.valCheckWhatsapp = settings[0].isWhatsapp;
    });
  }

  addSetting() {
    let obj = {
      noOfUsers: this.noOfUsersLimit,
      billingPlan: this.selectedPlan['name'],
      isWhatsapp: this.valCheckWhatsapp,
      isTelegram: this.valCheckTelegram,
    }; 
    this.settingService.addSetting(obj).subscribe(result => {
      if (result['isSuccess']) {
        this.toastService.showSuccess(`Settings updated successfully`);
      }
      else if (!result['isSuccess']) {
        this.toastService.showError(result['message']);
      }
    });
  }
}
