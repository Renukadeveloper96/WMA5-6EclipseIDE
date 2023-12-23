import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { ToastService } from './toast.service';
import { AppConstant } from '../models/app-constant';
import { Engagement, Promotion, Question } from '../demo/domain/contact';

@Injectable({
  providedIn: 'root'
})
export class EngagementService {

  constructor(private httpService: HttpService, private toastService: ToastService) { }

  getEngagementTypes() {
    let url = "/enagementTypes"
    return this.httpService.get(url);
  }

  getTemplateTypes() {
    let url = "/templateTypes"
    return this.httpService.get(url);
  }

  saveEngagement(obj: any) {
    let url = "/engagements";
    return this.httpService.post(url, obj);
  }

  updateEngagement(id: unknown, obj: any) {
    let url = "/engagements/" + id;
    return this.httpService.put(url, obj);
  }

  getEngagements() {
    let url = "/engagements";
    return this.httpService.get<Engagement[]>(url);
  }

  deleteEngagements(obj: any) {
    let url = "/engagements/delete";
    return this.httpService.post(url, obj)
  }

  getEnagementById(id) {
    let url = "/engagements/" + id;
    return this.httpService.get(url);
  }

  saveQuestions(engagementId: unknown, obj: any) {
    let url = "/engagement/" + engagementId + "/questions"
    return this.httpService.post(url, obj);
  }

  getQuestions(engagementId: unknown) {
    let url = "/engagement/" + engagementId + "/questions"
    return this.httpService.get<Question[]>(url);
  }

  deleteQuestions(engagementId: number, obj: any) {
    let url = "/engagement/" + engagementId + "/questions/delete";
    return this.httpService.post(url, obj);
  } 

  updateQuestion(engagementId: number, questionId: number, obj: any) {
    let url = "/engagement/" + engagementId + "/questions/" + questionId;
    return this.httpService.put(url, obj);
  }

  getPromotions(engagementId: unknown) {
    let url = "/engagement/" + engagementId + "/promotions"
    return this.httpService.get<Promotion[]>(url);
  }

  savePromotions(engagementId: number, obj: any) {
    let url = "/engagement/" + engagementId + "/promotions"
    return this.httpService.post(url, obj);
  }

  updatePromotion(engagementId: number, promotionId: number, obj: any) {
    let url = "/engagement/" + engagementId + "/promotions/" + promotionId;
    return this.httpService.put(url, obj);
  }

  deletePromotions(engagementId: number, obj: any) {
    let url = "/engagement/" + engagementId + "/promotions/delete";
    return this.httpService.post(url, obj);
  }
}
