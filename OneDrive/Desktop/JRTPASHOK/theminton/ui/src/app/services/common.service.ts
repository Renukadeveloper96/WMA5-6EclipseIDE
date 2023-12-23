import { Injectable } from '@angular/core';
import { ConfirmationService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private confirmationService: ConfirmationService) { }

  confirmation(message: string) {
    return this.confirmationService.confirm({
      message: message,
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
    });
  }

  saveNotes
}
