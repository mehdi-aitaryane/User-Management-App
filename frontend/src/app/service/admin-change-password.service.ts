import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ChangePasswordRequest } from '../request/change-password-request';

@Injectable({
  providedIn: 'root'
})
export class AdminChangePasswordService {

  constructor(private http: HttpClient) { }


  changePassword(changePasswordRequest:ChangePasswordRequest)
  {
    return this.http.put("http://localhost:8080/api/admin/password", changePasswordRequest);
  }

}
