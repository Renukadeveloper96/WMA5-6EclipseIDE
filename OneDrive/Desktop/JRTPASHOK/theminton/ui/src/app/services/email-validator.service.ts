import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailValidatorService {

  constructor() { }

  isEmailValid(value: string) {
    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    return value.match(validRegex);
  }
}
