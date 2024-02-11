import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../security/token-storage.service';
import { Observable, filter, interval, of, switchMap, take } from 'rxjs';
import { ProfileResponse } from '../response/profile-response';

@Injectable({
  providedIn: 'root'
})
export class AdminProfileService {

  constructor( private http: HttpClient) { }

    showProfile() 
    {
      return this.http.get("http://localhost:8080/api/admin/profile");
    }

    editProfile(profile:ProfileResponse)
    {
      return this.http.put("http://localhost:8080/api/admin/profile", profile);
    }

    changePhoto(image:Blob)
    {
      return this.http.put("http://localhost:8080/api/admin/profile/image", image);
    }

    getImage()
    {
      return this.http.get("http://localhost:8080/api/admin/profile/image", { responseType: 'blob' });
    }
        
}
