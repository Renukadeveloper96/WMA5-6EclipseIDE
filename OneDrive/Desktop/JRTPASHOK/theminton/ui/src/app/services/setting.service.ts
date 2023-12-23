import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Router } from '@angular/router';
import { Contact } from '../demo/domain/contact';

@Injectable({
  providedIn: 'root'
})
export class SettingService {

  constructor(private httpService: HttpService, private router: Router) { }

  addSetting(obj: any){
    let url = '/setting';
    return this.httpService.post(url,obj);
  }

  getSetting(){
    let url = '/setting';
    return this.httpService.get(url);
  }
 
}
