import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-contacts',
  templateUrl: './add-contacts.component.html',
  styleUrls: ['./add-contacts.component.scss']
})
export class AddContactsComponent implements OnInit {
  contactForm: FormGroup;

  constructor(private route: Router, private form: FormBuilder) {

  }
  ngOnInit(): void {
    this.contactForm = this.form.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      dob: ['', [Validators.required]],
      city: ['', [Validators.required]],
      state:['',Validators.required]
    });
  }

  moveBack() {
    this.route.navigate(['/dashboard/contacts']);
  }
}
