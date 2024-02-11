import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  error_message?:string;
  success_message?:string;

  constructor() { }

  getErrorMessage()
  {
    return this.error_message
  }

  getSuccessMessage()
  {
    return this.success_message
  }

  setErrorMessage(error_message: string)
  {
    this.error_message = error_message
  }

  setSuccessMessage(success_message: string)
  {
    this.success_message = success_message
  }

  clearErrorMessage()
  {
    this.error_message = ""
  }

  clearSuccessMessage()
  {
    this.success_message = ""
  }

}
