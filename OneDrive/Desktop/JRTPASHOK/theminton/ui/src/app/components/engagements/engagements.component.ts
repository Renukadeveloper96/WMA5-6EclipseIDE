import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';
import { AppConstant } from 'src/app/models/app-constant';
import { EngagementService } from 'src/app/services/engagement.service';
import { NoteService } from 'src/app/services/note.service';
import { ToastService } from 'src/app/services/toast.service';
import { FileSaver } from 'file-saver';
import { Engagement, Promotion, Question } from 'src/app/demo/domain/contact';
import { Observable, ReplaySubject } from 'rxjs';

@Component({
  selector: 'app-engagements',
  templateUrl: './engagements.component.html',
  styleUrls: ['./engagements.component.scss']
})
export class EngagementsComponent implements OnInit {
  showMaster: boolean = true;
  showKnowledge: boolean = false;
  showPromotion: boolean = false;
  selectedEngagementId: number = 0;

  engagements: Engagement[];
  cols: any[] = [];
  loading: boolean = true;
  selectedEngagements: Engagement[] = [];
  header: string = 'Add engagement';
  engagementName: string;
  engagementType: any;
  templateType: any;
  submitted: boolean = false;
  engagementDialog: boolean = false;
  deleteEngagementDialog: boolean = false;
  engagementTypes: any = [];
  templateTypes: any = [];
  exportColumns: any[];
  exportColumns1: any[];
  @ViewChild('dt') table: Table;
  @ViewChild('filter') filter: ElementRef;
  rowsPerPageOptions = [5, 10, 20];

  questionName: string;
  choice1: string;
  isSelectedChoice1: boolean;
  choice2: string;
  isSelectedChoice2: boolean;
  choice3: string;
  isSelectedChoice3: boolean;
  tidbitLink: string;
  tidbitText: string;
  tags: string;
  questions: Question[];
  cols1: any[] = [];
  selectedQuestions: Question[] = [];
  header1: string = 'Add question';
  questionDialog: boolean = false;
  showFileUpload: boolean = false;
  deleteQuestionDialog: boolean = false;
  @ViewChild('dt1') table1: Table;
  @ViewChild('filter1') filter1: ElementRef;

  promotions: Promotion[];
  cols2: any[] = [];
  selectedPromotions: Promotion[] = [];
  header2: string = 'Add promotion';
  promotionDialog: boolean = false;
  deletePromotionDialog: boolean = false;
  uploadedFiles: any[] = [];
  promotionName: string;
  promotionText: string;
  url: string;
  tags1: string;

  @ViewChild('dt1') table2: Table;
  @ViewChild('filter1') filter2: ElementRef;

  // engagementName: string;
  // templateType: any;
  // engagementType: any;
  // isEnagementSubmitted: boolean = false;
  // isUpdateEngagement: boolean = false;

  // selectedEngagmentType: any;
  // selectedEngagment: any;

  // correctAnswer: string = '';
  // questionForm: FormGroup;
  // isQuestionFormSubmitted: boolean = false;

  // questions: any = [];
  // isQuestionEdit: boolean = false;
  // selectedQuestion: number;

  // note: string = '';
  // notes: any = [];



  constructor(
    private engagementService: EngagementService,
    private toastService: ToastService,
    private confirmationService: ConfirmationService,
    //private formBuilder: FormBuilder,
    //private noteService: NoteService
  ) { }

  ngOnInit(): void {
    this.getTemplateTypes();
    this.getEngagementTypes();
    this.getEngagements();
    // this.initQuestionForm();
    // this.getNotes();

    this.cols = [
      { field: "name", header: "Engagement Name" },
      { field: "engagementType.type", header: "Engagement Type" },
      { field: "templateType.type", header: "Template" }
    ];

    this.cols1 = [
      { field: "engagement.name", header: "Engagement Name" },
      { field: "question", header: "Question" },
      { field: "choice1", header: "Choice1" },
      { field: "choice2", header: "Choice2" },
      { field: "choice3", header: "Choice3" },
      { field: "correctChoice", header: "Correct Choice" },
      { field: "tidbitLink", header: "TidBit Link" },
      { field: "tidbitText", header: "TidBit Text" },
    ];

    this.cols2 = [
      { field: "promotionName", header: "Promotion Name" },
      { field: "image", header: "Image" },
      { field: "promotionText", header: "Promotion Text" },
      { field: "url", header: "Image Url" },
      { field: "tags", header: "Tags" },
    ]

    this.exportColumns = this.cols.map(col => ({
      title: col.header,
      dataKey: col.field
    }));

    this.exportColumns1 = this.cols1.map(col => ({
      title: col.header,
      dataKey: col.field
    }));

    this.exportColumns1 = this.cols2.map(col => ({
      title: col.header,
      dataKey: col.field
    }));
  }

  handleSelect(event) {
    //this.contactRightPane = event.data;
  }

  showUpload() {
    this.showFileUpload = true;
  }

  onUpload(event) {
    if (event.originalEvent.body.isSuccess) {
      this.toastService.showSuccess('File uploaded successfully');
      this.showFileUpload = false;
      window.location.reload();
    }
  }

  exportFormat() {
    let link = document.createElement('a');
    link.setAttribute('type', 'hidden');
    link.href = 'assets/minton_questions_format.csv';
    link.download = 'minton_questions_format.csv';
    document.body.appendChild(link);
    link.click();
    link.remove();
    this.toastService.showSuccess('File exported successfully');
  }

  getEngagements() {
    this.engagements = [];
    this.engagementService.getEngagements().subscribe(
      (engagements: any) => {
        this.engagements = engagements;
        this.loading = false;
      }
    )
  }

  exportExcel() {
    import("xlsx").then(xlsx => {
      const worksheet = xlsx.utils.json_to_sheet(this.engagements);
      const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
      const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
      this.saveAsExcelFile(excelBuffer, "engagements");
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

  openNew(isEdit: boolean) {
    isEdit ? this.header = 'Edit Engagement' : 'Add Engagement';
    if (isEdit) {
      this.engagementName = this.selectedEngagements[0].name;
      this.engagementType = (({ type, id }) => ({ type, id }))(this.selectedEngagements[0].engagementType);
      this.templateType = (({ type, id }) => ({ type, id }))(this.selectedEngagements[0].templateType);

    }
    //this.contact = {};
    this.submitted = false;
    this.engagementDialog = true;
  }

  openNew1(isEdit: boolean) {
    isEdit ? this.header1 = 'Edit Question' : 'Add Question';
    if (isEdit) {
      this.questionName = this.selectedQuestions[0].question;
      this.choice1 = this.selectedQuestions[0].choice1;
      this.choice2 = this.selectedQuestions[0].choice2;
      this.choice3 = this.selectedQuestions[0].choice3;
      if (this.selectedQuestions[0].correctChoice === 9) {
        this.isSelectedChoice1 = true;
        this.isSelectedChoice2 = true;
        this.isSelectedChoice3 = true;
      }
      else if (this.selectedQuestions[0].correctChoice === 1)
        this.isSelectedChoice1 = true;
      else if (this.selectedQuestions[0].correctChoice === 2)
        this.isSelectedChoice2 = true;
      else if (this.selectedQuestions[0].correctChoice === 3)
        this.isSelectedChoice3 = true;
      this.tidbitLink = this.selectedQuestions[0].tidbitLink;
      this.tidbitText = this.selectedQuestions[0].tidbitText;
      this.tags = this.selectedQuestions[0].tags;
    }
    //this.contact = {};
    this.submitted = false;
    this.questionDialog = true;
  }

  openNew2(isEdit: boolean) { 
    isEdit ? this.header2 = 'Edit Promotion' : 'Add Promotion';
    if (isEdit) {
      this.promotionName = this.selectedPromotions[0].promotionName;
      this.promotionText = this.selectedPromotions[0].promotionText;
      this.uploadedFiles = [];
      this.url = this.selectedPromotions[0].url;
      this.tags1 = this.selectedPromotions[0].tags;
    }
    //this.contact = {};
    this.submitted = false;
    this.promotionDialog = true;
  }

  getEngagementTypes() {
    this.engagementTypes = [];
    this.engagementService.getEngagementTypes()
      .subscribe((p: any) => {
        p.forEach(element => {
          this.engagementTypes.push({ type: element.type, id: element.id });
        });
      });
  }

  getTemplateTypes() {
    this.templateTypes = [];
    this.engagementService.getTemplateTypes().subscribe((p: any) => {
      p.forEach(element => {
        this.templateTypes.push({ type: element.type, id: element.id });
      });
    })
  }

  hideDialog() {
    this.engagementName = '';
    this.engagementType = undefined;
    this.templateType = undefined;
    this.engagementDialog = false;
    this.submitted = false;
  }

  hideDialog1() {
    this.questionName = '';
    this.choice1 = null;
    this.isSelectedChoice1 = false;
    this.choice2 = null;
    this.isSelectedChoice2 = false;
    this.choice3 = null;
    this.isSelectedChoice3 = false;
    this.tidbitLink = '';
    this.tidbitText = '';
    this.tags = '';
    this.questionDialog = false;
    this.submitted = false;
  }

  hideDialog2() {
    this.promotionName = '';
    this.promotionText = '';
    this.uploadedFiles = [];
    this.url = '';
    this.tags1 = '';
    this.promotionDialog = false;
    this.submitted = false;
  }

  saveEngagement() {
    this.submitted = true;

    if (this.engagementName == null || this.engagementName == '' || this.engagementName == undefined) {
      this.toastService.showError('Please enter Engagement Name');
      this.submitted = false;
    }
    if (this.engagementType == undefined) {
      this.toastService.showError('Please select engagement type.');
      this.submitted = false;
    }
    if (this.templateType == undefined) {
      this.toastService.showError('Please select template type.');
      this.submitted = false;
    }


    if (this.submitted) {
      let obj = {
        name: this.engagementName,
        engagementTypeId: this.engagementType.id,
        templateTypeId: this.templateType.id
      }
      if (this.header == 'Edit Engagement') {
        this.engagementService.updateEngagement(this.selectedEngagements[0].id, obj).subscribe((result: any) => {
          if (result['isSuccess']) {
            this.toastService.showSuccess(`Engagement has been updated successfully`);
            this.hideDialog();
            this.header = 'Add Engagement';
            window.location.reload();
          }
          else if (!result['isSuccess']) {
            this.toastService.showError(result['message']);
          }
        });
      }
      else {
        this.engagementService.saveEngagement(obj).subscribe(result => {
          if (result['isSuccess']) {
            this.toastService.showSuccess(`Engagement has been added successfully`);
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

  saveQuestion() {
    this.submitted = true;

    if (this.questionName == null || this.questionName == '' || this.questionName == undefined) {
      this.toastService.showError('Please enter Question');
      this.submitted = false;
    }
    if (this.choice1 == null || this.choice1 == undefined) {
      this.toastService.showError('Please enter Choice1');
      this.submitted = false;
    }
    if (this.choice2 == null || this.choice1 == undefined) {
      this.toastService.showError('Please enter Choice2');
      this.submitted = false;
    }
    if (this.choice3 == null || this.choice1 == undefined) {
      this.toastService.showError('Please enter Choice3');
      this.submitted = false;
    }
    let choice = this.isSelectedChoice1 && this.isSelectedChoice2 && this.isSelectedChoice3;
    if (this.isSelectedChoice1 == undefined && this.isSelectedChoice2 == undefined && this.isSelectedChoice3 == undefined) {
      this.toastService.showError('Please select Choice');
      this.submitted = false;
    }
    if ([this.isSelectedChoice1, this.isSelectedChoice2, this.isSelectedChoice3].filter(Boolean).length === 2) {
      this.toastService.showError('Please select only one Choice or all 3 choice');
      this.submitted = false;
    }
    let currentChoice = null;
    if (choice) {
      currentChoice = 9;
    }
    else {
      if (this.isSelectedChoice1) {
        currentChoice = 1;
      }
      else if (this.isSelectedChoice2) {
        currentChoice = 2;
      }
      else if (this.isSelectedChoice3) {
        currentChoice = 3
      }
    }


    if (this.submitted) {
      let obj = {
        question: this.questionName,
        choice1: this.choice1,
        choice2: this.choice2,
        choice3: this.choice3,
        correctChoice: currentChoice,
        tidbitLink: this.tidbitLink,
        tidbitText: this.tidbitText,
        tags: this.tags
      }
      if (this.header == 'Edit Question') {
        this.engagementService.updateQuestion(this.selectedEngagementId, this.selectedQuestions[0].id, obj).subscribe((result: any) => {
          if (result['isSuccess']) {
            this.toastService.showSuccess(`Question has been updated successfully`);
            this.hideDialog();
            this.header = 'Add Question';
            window.location.reload();
          }
          else if (!result['isSuccess']) {
            this.toastService.showError(result['message']);
          }
        });
      }
      else {
        this.engagementService.saveQuestions(this.selectedEngagementId, obj).subscribe(result => {
          if (result['isSuccess']) {
            this.toastService.showSuccess(`Question has been added successfully`);
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

  savePromotion() {
    this.submitted = true;

    if (this.promotionName == null || this.promotionName == '' || this.promotionName == undefined) {
      this.toastService.showError('Please enter Promotion Name');
      this.submitted = false;
    }
    if (this.promotionText == null || this.promotionText == '' || this.promotionText == undefined) {
      this.toastService.showError('Please enter Promotion Text');
      this.submitted = false;
    }


    if (this.submitted) {
      let obj = {
        promotionName: this.promotionName,
        promotionText: this.promotionText,
        image: this.uploadedFiles.length == 1 ? this.uploadedFiles[0].name : '',
        imageBase64: this.uploadedFiles.length == 1 ? this.uploadedFiles[0].base64 : '',
        url: this.url,
        tags: this.tags1
      }
      if (this.header == 'Edit Promotion') {
        this.engagementService.updatePromotion(this.selectedEngagementId, this.selectedPromotions[0].id, obj).subscribe((result: any) => {
          if (result['isSuccess']) {
            this.toastService.showSuccess(`Promotion has been updated successfully`);
            this.hideDialog();
            this.header = 'Add Promotion';
            window.location.reload();
          }
          else if (!result['isSuccess']) {
            this.toastService.showError(result['message']);
          }
        });
      }
      else {
        this.engagementService.savePromotions(this.selectedEngagementId, obj).subscribe(result => {
          if (result['isSuccess']) {
            this.toastService.showSuccess(`Promotion has been added successfully`);
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

  deleteselectedEngagements() {
    this.deleteEngagementDialog = true;
  }

  deleteselectedQuestions() {
    this.deleteQuestionDialog = true;
  }

  deleteselectedPromotions() {
    this.deletePromotionDialog = true;
  }

  confirmDeleteSelected() {
    this.deleteEngagementDialog = false;
    let ids = this.selectedEngagements.map(a => a.id);
    let obj = {
      ids: ids
    }
    this.engagementService.deleteEngagements(obj).subscribe(result => {
      if (result['isSuccess']) {
        this.selectedEngagements = [];
        this.toastService.showSuccess('Selected Engagements have been deleted successfully');
        window.location.reload();
      }
      else if (!result['isSuccess']) {
        this.toastService.showError(result['message']);
      }
    });
    // this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'contacts Deleted', life: 3000 });

  }

  confirmDeleteSelectedQuestions() {
    this.deleteEngagementDialog = false;
    let ids = this.selectedQuestions.map(a => a.id);
    let obj = {
      ids: ids
    }
    this.engagementService.deleteQuestions(this.selectedEngagementId, obj).subscribe(result => {
      if (result['isSuccess']) {
        this.selectedQuestions = [];
        this.toastService.showSuccess('Selected Questions have been deleted successfully');
        window.location.reload();
      }
      else if (!result['isSuccess']) {
        this.toastService.showError(result['message']);
      }
    });
    // this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'contacts Deleted', life: 3000 });

  }

  confirmDeleteSelectedPromotions() {
    this.deletePromotionDialog = false;
    let ids = this.selectedPromotions.map(a => a.id);
    let obj = {
      ids: ids
    }
    this.engagementService.deletePromotions(this.selectedEngagementId, obj).subscribe(result => {
      if (result['isSuccess']) {
        this.selectedQuestions = [];
        this.toastService.showSuccess('Selected Promotions have been deleted successfully');
        window.location.reload();
      }
      else if (!result['isSuccess']) {
        this.toastService.showError(result['message']);
      }
    });
    // this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'contacts Deleted', life: 3000 });

  }

  onClick(engagement: any) {
    if (engagement.engagementType.type == "Knowledge Sharing") {
      this.showKnowledge = true;
      this.showMaster = false;
      this.selectedEngagementId = engagement.id;
      this.engagementService.getQuestions(engagement.id).subscribe(questions => {
        this.questions = questions;
      });
    }
    else {
      this.showPromotion = true;
      this.showMaster = false;
      this.selectedEngagementId = engagement.id;
      this.engagementService.getPromotions(engagement.id).subscribe(promotions => {
        this.promotions = promotions;
      });
    }
  }

  goToMain() {
    this.showMaster = true;
    this.showKnowledge = false;
    this.showPromotion = false;
    this.selectedEngagementId = 0;
  }

  convertFile(file: File): Observable<string> {
    const result = new ReplaySubject<string>(1);
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = (event) => result.next(btoa(event.target.result.toString()));
    return result;
  }

  onUpload1(event) {
    if (this.uploadedFiles.length >= 1) {
      event.files = [];
      this.toastService.showError("You can upload only one file");
    }
    else {
      for (const file of event.files) {
        this.uploadedFiles.push(file);
        this.convertFile(this.uploadedFiles[0]).subscribe(base64 => {
          debugger
          this.uploadedFiles[0].base64 = base64;
        });
      }
    }
  }

  onClear(event) {
    this.uploadedFiles = [];
  }

  clear(table: Table) {
    table.clear();
    this.filter.nativeElement.value = '';
    this.filter1.nativeElement.value = '';
    this.filter2.nativeElement.value = '';
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }
}
