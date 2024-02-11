import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../request/register-request';

@Injectable({
  providedIn: 'root'
})
export class RegisterationService {

  constructor(private http: HttpClient) { }

  register(registerRequest?: RegisterRequest) {
    return this.http.post("http://localhost:8080/api/register", registerRequest);
  }

}
