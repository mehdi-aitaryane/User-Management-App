import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { CommonModule } from '@angular/common';
import { ChangePasswordValidation } from '../../validation/change-password-validation';
import { AdminChangePasswordService } from '../../service/admin-change-password.service';
import { AlertService } from '../../service/alert.service';
import { ChangePasswordRequest } from '../../request/change-password-request';
import { MessageResponse } from '../../response/message-response';
import { TokenStorageService } from '../../security/token-storage.service';

@Component({
  selector: 'app-admin-change-password',
  standalone: true,
  imports: [RouterModule, BreadcrumbComponent, CommonModule],
  templateUrl: './admin-change-password.component.html',
  styleUrl: './admin-change-password.component.css'
})
export class AdminChangePasswordComponent {
  breadcrumbPaths: string[] = ['Home', 'Admin', 'Manage Profile', 'Change Password'];
  validationMessages!: ChangePasswordValidation;

  constructor(private adminChangePasswordService: AdminChangePasswordService, private alertService: AlertService, private router: Router, private tokenStorageService : TokenStorageService) {
  }

  changePassword(oldPassword: string, newPassword: string, confirmPassword: string) {

    const changePasswordRequest = new ChangePasswordRequest(oldPassword, newPassword, confirmPassword);
    this.alertService.clearErrorMessage()
    this.alertService.clearSuccessMessage()

    this.adminChangePasswordService.changePassword(changePasswordRequest).subscribe({
      next: value => {
        let messageResponse = value as MessageResponse;
        this.alertService.setSuccessMessage(messageResponse.message)
        this.tokenStorageService.clearRefreshToken()
        this.tokenStorageService.clearAccessToken()
        this.router.navigate([''])
    },
      error: err => {
        let messageResponse = err.error as MessageResponse;
        this.alertService.setErrorMessage(messageResponse.message)
        this.validationMessages = err.error.validation as ChangePasswordValidation;
      },
      complete: () => {
      }
    });
  }
}