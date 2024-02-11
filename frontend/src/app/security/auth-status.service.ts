import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthStatusService {

  constructor(private tokenStorageService: TokenStorageService) { }

  isAuthenticated() {
    if(!this.isRefreshTokenAvailable())
    {
      return false;
    }
    const token = this.tokenStorageService.retrieveRefreshToken()
    const jwt = jwtDecode(token as string) as any;
    return Date.now() / 1000 < jwt.exp;
  }

  isNotAuthenticated() {
    return !this.isAuthenticated();
  }

  isAdminAuthenticated() {
    if(!this.isRefreshTokenAvailable())
    {
      return false;
    }
    const token = this.tokenStorageService.retrieveRefreshToken()
    const jwt = jwtDecode(token as string) as any;
    return (Date.now() / 1000 < jwt.exp) && jwt.roles == "ROLE_ADMIN";
  }

  isUserAuthenticated() {
    if(!this.isRefreshTokenAvailable())
    {
      return false;
    }
    const token = this.tokenStorageService.retrieveRefreshToken()
    const jwt = jwtDecode(token as string) as any;
    return (Date.now() / 1000 < jwt.exp) && jwt.roles == "ROLE_USER";
  }

  isAccessTokenExpired() {
    if(!this.isAccessTokenAvailable())
    {
      return true;
    }
    const token = this.tokenStorageService.retrieveAccessToken()
    const jwt = jwtDecode(token as string) as any;
    return (Date.now() / 1000 >= jwt.exp);
  }

  isAccessTokenAvailable() {
    const token = this.tokenStorageService.retrieveAccessToken()
    if (token == null) {
      return false;
    }
    else {
      return true;
    }
  }

  isRefreshTokenAvailable() {
    const token = this.tokenStorageService.retrieveRefreshToken()
    if (token == null) {
      return false;
    }
    else {
      return true;
    }
  }

}
