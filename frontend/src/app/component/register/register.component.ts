import { Component } from '@angular/core';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { RegisterationService } from '../../service/registeration.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RegisterRequest } from '../../request/register-request';
import { AlertService } from '../../service/alert.service';
import { MessageResponse } from '../../response/message-response';
import { Router } from '@angular/router';
import { RegisterValidation } from '../../validation/register-validation';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [BreadcrumbComponent, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  breadcrumbPaths: string[] = ['Home', 'Register'];
  validationMessages!: RegisterValidation;
  constructor(private formBuilder: FormBuilder, private registrationService:RegisterationService, private alertService: AlertService, private router: Router) 
  {
  }

  register(firstname: string, lastname: string, username: string, password: string, confirmPassword: string) {
    const registerRequest = new RegisterRequest(firstname, lastname, username, password, confirmPassword);
    this.alertService.clearErrorMessage()
    this.alertService.clearSuccessMessage()
    
    this.registrationService.register(registerRequest).subscribe({
      next: value => {
        let messageResponse = value as MessageResponse;
        this.alertService.setSuccessMessage(messageResponse.message)
        this.router.navigate(['login'])
      },
      error: err => {
        let messageResponse = err.error as MessageResponse;
        this.alertService.setErrorMessage(messageResponse.message)
        this.validationMessages = err.error.validation as RegisterValidation;
        },
      complete: () => {
      }
    });

     
  }
}
