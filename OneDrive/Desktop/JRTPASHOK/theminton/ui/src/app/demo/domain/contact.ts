export interface Contact {
    id: number,
    firstName: string,
    lastName: string,
    dob: Date,
    city: string,
    state: string,
    country: string,
    courseName: string,
    courseDate: Date,
    fieldOfInterest: string,
    primaryNumber: number,
    secondaryNumber: number,
    primaryEmail: string,
    secondaryEmail: string,
    currentCompany: string,
    currentPosition: string,
    linkedInId: string,
    instagramId: string,
    faceBookId: string
}

export interface Engagement {
    id?: number;
    name?: string;
    engagementType: EngagementType;
    templateType: TemplateType;
}

export interface EngagementType {
    type: string;
    id: number;
}

export interface TemplateType {
    type: string;
    id: number;
}

export interface Question {
    id: number;
    question: string;
    choice1: string;
    choice2: string;
    choice3: string;
    correctChoice: number;
    tidbitLink: string;
    tidbitText: string;
    engagment: Engagement;
    tags: string;
}

export interface Promotion {
    id: number;
    promotionName: string;
    promotionText: string;
    image: string;
    url: string;
    tags: string;
}