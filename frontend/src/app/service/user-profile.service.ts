import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../security/token-storage.service';
import { ProfileResponse } from '../response/profile-response';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  constructor(private http: HttpClient) { }

  showProfile() {
    return this.http.get("http://localhost:8080/api/user/profile");
  }

  editProfile(profile:ProfileResponse)
  {
    return this.http.put("http://localhost:8080/api/user/profile", profile);
  }

  deleteProfile()
  {
    return this.http.delete("http://localhost:8080/api/user/profile");
  }

  changePhoto(image:Blob)
  {
    return this.http.put("http://localhost:8080/api/user/profile/image", image);
  }

  getImage()
  {
    return this.http.get("http://localhost:8080/api/user/profile/image", { responseType: 'blob' });
  }

}
