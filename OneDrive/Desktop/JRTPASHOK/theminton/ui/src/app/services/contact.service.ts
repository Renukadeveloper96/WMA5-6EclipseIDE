import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Router } from '@angular/router';
import { Contact } from '../demo/domain/contact';

@Injectable({
  providedIn: 'root'
})
export class ConatctService {

  constructor(private httpService: HttpService, private router: Router) { }

  addContact(obj: any) {
    let url = '/contacts';
    return this.httpService.post(url, obj);
  }

  editContact(obj: any, contactId: number) {
    let url = '/contacts/' + contactId;
    return this.httpService.put(url, obj);
  }

  getContacts() {
    let url = '/contacts';
    return this.httpService.get<Contact[]>(url);
  }

  deleteContacts(obj: any) {
    let url = '/contacts/delete';
    return this.httpService.post(url, obj);
  }

  getAllGroups() {
    let url = '/group/list';
    return this.httpService.get(url);
  }

  addGroup(obj: any) {
    let url = '/group/add';
    return this.httpService.post(url, obj);
  }
}
