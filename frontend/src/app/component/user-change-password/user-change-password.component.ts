import { Component } from '@angular/core';
import { ChangePasswordValidation } from '../../validation/change-password-validation';
import { UserChangePasswordService } from '../../service/user-change-password.service';
import { AlertService } from '../../service/alert.service';
import { Router, RouterModule } from '@angular/router';
import { TokenStorageService } from '../../security/token-storage.service';
import { ChangePasswordRequest } from '../../request/change-password-request';
import { MessageResponse } from '../../response/message-response';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-change-password',
  standalone: true,
  imports: [RouterModule, BreadcrumbComponent, CommonModule],
  templateUrl: './user-change-password.component.html',
  styleUrl: './user-change-password.component.css'
})
export class UserChangePasswordComponent {
  breadcrumbPaths: string[] = ['Home', 'User', 'Manage Profile', 'Change Password'];
  validationMessages!: ChangePasswordValidation;

  constructor(private userChangePasswordService: UserChangePasswordService, private alertService: AlertService, private router: Router, private tokenStorageService : TokenStorageService) {
  }

  changePassword(oldPassword: string, newPassword: string, confirmPassword: string) {

    const changePasswordRequest = new ChangePasswordRequest(oldPassword, newPassword, confirmPassword);
    this.alertService.clearErrorMessage()
    this.alertService.clearSuccessMessage()

    this.userChangePasswordService.changePassword(changePasswordRequest).subscribe({
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
