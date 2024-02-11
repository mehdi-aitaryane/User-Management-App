import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  findUsers(keyword : string, size: number, page: number) {
    return this.http.get("http://localhost:8080/api/admin/users?keyword=" + keyword + "&page=" + page + "&size=" + size);
  }

  findUser(username : string) {
    return this.http.get("http://localhost:8080/api/admin/users/" + username);
  }

  getUserImage(username : string) {
    return this.http.get("http://localhost:8080/api/admin/users/images/" + username, { responseType: 'blob' });
  }

  deleteUser(username : string) {
    return this.http.delete("http://localhost:8080/api/admin/users/" + username);
  }

}
