import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private access_token:string|null;

  constructor() { this.access_token = null; }

  storeAccessToken(access_token : string)
  {
    this.access_token = access_token
  }

  storeRefreshToken(refresh_token : string)
  {
    localStorage.setItem("refresh_token", refresh_token)
  }

  retrieveAccessToken() : string | null
  {
    return this.access_token;  
  }

  retrieveRefreshToken() : string | null
  {
    let refresh_token = localStorage.getItem("refresh_token")
    return refresh_token;  
  }

  clearAccessToken()
  {
    this.access_token = null
  }

  clearRefreshToken()
  {
    localStorage.removeItem("refresh_token")
  }

}
