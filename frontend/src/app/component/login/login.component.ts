import { Component } from '@angular/core';
import { LoginRequest } from '../../request/login-request';
import { AuthService } from '../../security/auth.service';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthStatusService } from '../../security/auth-status.service';
import { TokenResponse } from '../../response/token-response';
import { TokenStorageService } from '../../security/token-storage.service';
import { MessageResponse } from '../../response/message-response';
import { AlertService } from '../../service/alert.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [BreadcrumbComponent, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  breadcrumbPaths: string[] = ['Home', 'Login'];
  constructor(private authService: AuthService, private router: Router, private authStatusService: AuthStatusService, private tokenStorageService: TokenStorageService, private alertService: AlertService) {}

  login(username: string, password: string) {
    this.alertService.clearErrorMessage()
    this.alertService.clearSuccessMessage()
    let loginRequest = new LoginRequest(username, password);
    this.authService.login(loginRequest)
    .subscribe({
      next: value => {
        let tokenResponse = value as TokenResponse;
        this.tokenStorageService.storeAccessToken(tokenResponse.access_token)
        this.tokenStorageService.storeRefreshToken(tokenResponse.refresh_token)
        this.alertService.setSuccessMessage(tokenResponse.message)
      },
      error: err => {
        let messageResponse = err.error as MessageResponse;
        this.alertService.setErrorMessage(messageResponse.message)
      },
      complete: () => {
        if(this.authStatusService.isAdminAuthenticated())
        {
          this.router.navigate(['/admin'])
        }
        if(this.authStatusService.isUserAuthenticated())
        {
          this.router.navigate(['/user'])
        } 
      }
    });
  }
}
