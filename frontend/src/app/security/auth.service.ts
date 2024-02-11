import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenResponse } from '../response/token-response';
import { LoginRequest } from '../request/login-request';
import { MessageResponse } from '../response/message-response';
import { Router } from '@angular/router';
import { TokenStorageService } from './token-storage.service';
import { AuthStatusService } from './auth-status.service';
import { AlertService } from '../service/alert.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private tokenStorageService: TokenStorageService,
    private authStatusService: AuthStatusService,
    private router: Router,
    private alertService: AlertService
  ) { }

  login(loginRequest: LoginRequest) {
    return this.http.post<TokenResponse>("http://localhost:8080/api/auth/login", loginRequest);
  }

  relogin() {
    // Get the current user's authentication details
    let authentication = this.tokenStorageService.retrieveRefreshToken();
    // Make a POST request to the /relogin endpoint
    return this.http.post("http://localhost:8080/api/auth/relogin", { token: authentication })
      .subscribe({
        next: value => {
          let tokenResponse = value as TokenResponse;
          this.tokenStorageService.storeAccessToken(tokenResponse.access_token)
          this.tokenStorageService.storeRefreshToken(tokenResponse.refresh_token)
        },
        error: err => {
          this.tokenStorageService.clearAccessToken()
          this.tokenStorageService.clearRefreshToken()
        },
        complete: () => {
        }
      });
  }

  logout() {
    this.alertService.clearSuccessMessage()
    // Get the current user's authentication details
    let authentication = this.tokenStorageService.retrieveAccessToken();

    // Make a POST request to the /relogin endpoint
    return this.http.post("http://localhost:8080/api/auth/logout", { token: authentication })
      .subscribe({
        next: value => {
          let messageResponse = value as MessageResponse;
          this.tokenStorageService.clearAccessToken()
          this.tokenStorageService.clearRefreshToken()
          this.router.navigate([''])
          this.alertService.setSuccessMessage(messageResponse.message)
        },
        error: err => {
        },
        complete: () => {
         }
      });
  }

}
