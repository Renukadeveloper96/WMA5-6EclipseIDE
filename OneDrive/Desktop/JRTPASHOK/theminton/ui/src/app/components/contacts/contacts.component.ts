import { formatDate } from '@angular/common';
import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { Contact } from 'src/app/demo/domain/contact';
import { ConatctService } from 'src/app/services/contact.service';
import { ToastService } from 'src/app/services/toast.service';
import { FileSaver } from 'file-saver';
import { NoteService } from 'src/app/services/note.service';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  providers: [MessageService, ConfirmationService],
  styleUrls: ['../../../assets/demo/badges.scss'],
  styles: [`
        :host ::ng-deep  .p-frozen-column {
            font-weight: bold;
        }

        :host ::ng-deep .p-datatable-frozen-tbody {
            font-weight: bold;
        }

        :host ::ng-deep .p-progressbar {
            height:.5rem;
        }
        
    `]
})
export class ContactsComponent implements OnInit {

  showFileUpload: boolean = false;
  showAddNotes: boolean = false;
  showSendMessage: boolean = false;
  showAddToGroup: boolean = false;
  groups: any;
  selectedGroup: SelectItem;
  notes: string;
  allNotes: any;
  contacts: Contact[];
  firstName: string;
  lastName: string;
  dob: Date;
  city: string;
  state: string;
  country: string;
  courseName: string;
  courseDate: Date;
  fieldOfInterest: string;
  primaryNumber: number;
  secondaryNumber: number;
  primaryEmail: string;
  secondaryEmail: string;
  currentCompany: string;
  currentPosition: string;
  linkedInId: string;
  instagramId: string;
  faceBookId: string;
  contactRightPane: any;
  groupName: string;
  exportColumns: any[];
  loading: boolean = true;

  @ViewChild('dt') table: Table;

  @ViewChild('filter') filter: ElementRef;

  contactDialog: boolean = false;

  deletecontactsDialog: boolean = false;

  selectedContacts: Contact[] = [];

  submitted: boolean = false;

  header: string = 'Add contact';

  cols: any[] = [];

  rowsPerPageOptions = [5, 10, 20];

  constructor(
    private route: Router,
    private contactService: ConatctService,
    private messageService: MessageService,
    private confirmService: ConfirmationService,
    private toastService: ToastService,
    private noteService: NoteService,
    private cd: ChangeDetectorRef) {

  }
  ngOnInit(): void {
    this.contactService.getAllGroups().subscribe(groups => {
      if (groups) {
        this.groups = groups;
        this.groups = this.groups.map(a => ({ name: a.groupName, code: a.id }));
      }
    });
    this.contactService.getContacts().subscribe(contacts => {
      this.contacts = contacts['list'];
      this.loading = false;
    });
    this.noteService.getNotes().subscribe(notes => {
      this.allNotes = notes;
    });
    this.cols = [
      { field: "firstName", header: "First Name" },
      { field: "lastName", header: "Last Name" },
      { field: "dob", header: "Date Of Birth" },
      { field: "primaryNumber", header: "Primary Number" },
      { field: "secondaryNumber", header: "Secondary Number" },
      { field: "primaryEmail", header: "Primary Email" },
      { field: "secondaryEmail", header: "Secondary Email" },
      { field: "fieldOfInterest", header: "Field Of Interest" },
      { field: "courseName", header: "Course Name" },
      { field: "city", header: "City" },
      { field: "state", header: "State" },
      { field: "country", header: "Country" },
      { field: "currentCompany", header: "Current Company" },
      { field: "currentPosition", header: "Current Position" },
      { field: "linkedInId", header: "LinkedInId" },
      { field: "instagramId", header: "InstagramId" },
      { field: "faceBookId", header: "FaceBook Id" },

    ];

    this.exportColumns = this.cols.map(col => ({
      title: col.header,
      dataKey: col.field
    }));
  }

  exportExcel() {
    import("xlsx").then(xlsx => {
      const worksheet = xlsx.utils.json_to_sheet(this.contacts);
      const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
      const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
      this.saveAsExcelFile(excelBuffer, "contacts");
    });
  }

  saveAsExcelFile(buffer: any, fileName: string): void {
    let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
    let EXCEL_EXTENSION = '.xlsx';
    const data: Blob = new Blob([buffer], {
      type: EXCEL_TYPE
    });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
  }

  addToGroupApi(type) {
    let contactids = this.selectedContacts.map(a => a.id);
    if (type == 'new') {
      if (this.groupName == null || this.groupName == '') {
        this.toastService.showError('Please enter group name');
      }
      else {
        let obj = {
          value: 0,
          groupName: this.groupName,
          contactIds: contactids
        }
        this.contactService.addGroup(obj).subscribe(result => {
          if (result['isSuccess']) {
            this.selectedContacts = [];
            this.toastService.showSuccess(`Contact added to ` + this.groupName + ` successfully`);
            this.showAddToGroup = false;
            window.location.reload();
          }
          else if (!result['isSuccess']) {
            this.toastService.showError(result['message']);
          }
        });
      }
    }
    else {
      let obj = {
        value: this.selectedGroup['code'],
        groupName: this.selectedGroup['name'],
        contactIds: contactids
      }
      this.contactService.addGroup(obj).subscribe(result => {
        if (result['isSuccess']) {
          this.selectedContacts = [];
          this.toastService.showSuccess(`Contact added to ` + this.selectedGroup['name'] + ` successfully`);
          this.showAddToGroup = false;
          window.location.reload();
        }
        else if (!result['isSuccess']) {
          this.toastService.showError(result['message']);
        }
      });
    }
  }

  handleSelect(event) {
    this.contactRightPane = event.data;
  }

  openNew(isEdit: boolean) {
    isEdit ? this.header = 'Edit Contact' : 'Add Contact';
    if (isEdit) {
      this.firstName = this.selectedContacts[0].firstName;
      this.lastName = this.selectedContacts[0].lastName;
      this.dob = this.selectedContacts[0].dob == null ? null : new Date(this.selectedContacts[0].dob);
      this.city = this.selectedContacts[0].city;
      this.state = this.selectedContacts[0].state;
      this.country = this.selectedContacts[0].country;
      this.courseName = this.selectedContacts[0].courseName;
      this.courseDate = new Date(this.selectedContacts[0].courseDate);
      this.fieldOfInterest = this.selectedContacts[0].fieldOfInterest;
      this.primaryNumber = this.selectedContacts[0].primaryNumber;
      this.secondaryNumber = this.selectedContacts[0].secondaryNumber;
      this.primaryEmail = this.selectedContacts[0].primaryEmail;
      this.secondaryEmail = this.selectedContacts[0].secondaryEmail;
      this.currentCompany = this.selectedContacts[0].currentCompany;
      this.currentPosition = this.selectedContacts[0].currentPosition;
      this.linkedInId = this.selectedContacts[0].linkedInId;
      this.instagramId = this.selectedContacts[0].instagramId;
      this.faceBookId = this.selectedContacts[0].faceBookId;
    }
    //this.contact = {};
    this.submitted = false;
    this.contactDialog = true;
  }

  deleteselectedContacts() {
    this.deletecontactsDialog = true;
  }

  confirmDeleteSelected() {
    this.deletecontactsDialog = false;
    let contactids = this.selectedContacts.map(a => a.id);
    let obj = {
      contactIds: contactids
    }
    this.contactService.deleteContacts(obj).subscribe(result => {
      if (result['isSuccess']) {
        this.selectedContacts = [];
        this.toastService.showSuccess('Selected Contacts have been deleted successfully');
        window.location.reload();
      }
      else if (!result['isSuccess']) {
        this.toastService.showError(result['message']);
      }
    });
    // this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'contacts Deleted', life: 3000 });

  }

  hideDialog() {
    this.firstName = '';
    this.lastName = '';
    this.dob = null;
    this.city = '';
    this.state = '';
    this.country = '';
    this.courseName = '';
    this.courseDate = null;
    this.fieldOfInterest = '';
    this.primaryNumber = null;
    this.secondaryNumber = null;
    this.primaryEmail = '';
    this.secondaryEmail = '';
    this.currentCompany = '';
    this.currentPosition = '';
    this.linkedInId = '';
    this.instagramId = '';
    this.faceBookId = '';
    this.contactDialog = false;
    this.submitted = false;
  }

  saveContact() {
    this.submitted = true;

    if (this.firstName == null || this.firstName == '') {
      this.toastService.showError('Please enter First Name');
      this.submitted = false;
    }
    if (this.lastName == null || this.lastName == '') {
      this.toastService.showError('Please enter Last Name');
      this.submitted = false;
    }
    if (this.primaryNumber == null || this.primaryNumber == 0) {
      this.toastService.showError('Please enter Primary Number');
      this.submitted = false;
    }
    if (this.courseDate == null || !this.courseDate) {
      this.toastService.showError('Please enter Course date');
      this.submitted = false;
    }
    if (this.city == null || this.city == '') {
      this.toastService.showError('Please enter City');
      this.submitted = false;
    }
    if (this.state == null || this.state == '') {
      this.toastService.showError('Please enter State');
      this.submitted = false;
    }
    if (this.country == null || this.country == '') {
      this.toastService.showError('Please enter Country');
      this.submitted = false;
    }

    if (this.submitted) {
      let obj = {
        firstName: this.firstName,
        lastName: this.lastName,
        dob: this.dob != undefined ? formatDate(this.dob, 'yyyy-MM-dd', 'en-US') : this.dob,
        city: this.city,
        state: this.state,
        country: this.country,
        courseName: this.courseName,
        courseDate: formatDate(this.courseDate, 'yyyy-MM-dd', 'en-US'),
        fieldOfInterest: this.fieldOfInterest,
        primaryNumber: Number(this.primaryNumber),
        secondaryNumber: Number(this.secondaryNumber),
        primaryEmail: this.primaryEmail,
        secondaryEmail: this.secondaryEmail,
        currentCompany: this.currentCompany,
        currentPosition: this.currentPosition,
        linkedInId: this.linkedInId,
        instagramId: this.instagramId,
        faceBookId: this.faceBookId
      }
      if (this.header == 'Edit Contact') {
        this.contactService.editContact(obj, this.selectedContacts[0].id).subscribe(result => {
          if (result['isSuccess']) {
            this.toastService.showSuccess(`Contact has been updated successfully`);
            this.hideDialog();
            this.header = 'Add Contact';
            window.location.reload();
          }
          else if (!result['isSuccess']) {
            this.toastService.showError(result['message']);
          }
        });
      }
      else {
        this.contactService.addContact(obj).subscribe(result => {
          if (result['isSuccess']) {
            this.toastService.showSuccess(`Contact has been added successfully`);
            this.hideDialog();
            window.location.reload();
          }
          else if (!result['isSuccess']) {
            this.toastService.showError(result['message']);
          }
        });
      }
    }
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }

  addNotes() {
    let obj = {
      note: this.notes,
      type: 'contact',
    }
    this.noteService.addNotes(obj).subscribe(result => {
      if (result['isSuccess']) {
        this.toastService.showSuccess(`Notes has been updated successfully`);
        this.showAddNotes = false;
        this.notes = '';
        window.location.reload();
      }
      else if (!result['isSuccess']) {
        this.toastService.showError(result['message']);
      }
    });
  }

  exportFormat() {
    let link = document.createElement('a');
    link.setAttribute('type', 'hidden');
    link.href = 'assets/minton_contacts_format.csv';
    link.download = 'minton_contacts_format.csv';
    document.body.appendChild(link);
    link.click();
    link.remove();
    this.toastService.showSuccess('File exported successfully');
  }

  showUpload() {
    this.showFileUpload = true;
  }

  onUpload(event) {
    if(event.originalEvent.body.isSuccess){
      this.toastService.showSuccess('File uploaded successfully');
      this.showFileUpload = false;
      window.location.reload();
    }
  }  

  showNotes() {
    this.showAddNotes = true;
  }

  sendMessage() {
    this.showSendMessage = true;
  }

  addToGroup() {
    this.showAddToGroup = true;
  }

  clear(table: Table) {
    table.clear();
    this.filter.nativeElement.value = '';
  }

}
