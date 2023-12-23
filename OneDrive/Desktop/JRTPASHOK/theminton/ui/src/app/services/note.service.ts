import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { ToastService } from './toast.service';
import { AppConstant } from '../models/app-constant';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Contact } from '../demo/domain/contact';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private httpService: HttpService, private router: Router, private toastService: ToastService) { }

  addNotes(obj: any){
    let url = '/note';
    return this.httpService.post(url,obj);
  } 

  getNotes(){
    let url = '/note';
    return this.httpService.get(url);
  }  



}
