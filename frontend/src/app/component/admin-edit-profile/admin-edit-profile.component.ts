import { Component } from '@angular/core';
import { ProfileValidation } from '../../validation/profile-validation';
import { Router, RouterModule } from '@angular/router';
import { AlertService } from '../../service/alert.service';
import { AdminProfileService } from '../../service/admin-profile.service';
import { ProfileResponse } from '../../response/profile-response';
import { MessageResponse } from '../../response/message-response';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { CommonModule } from '@angular/common';
import { TokenStorageService } from '../../security/token-storage.service';

@Component({
  selector: 'app-admin-edit-profile',
  standalone: true,
  imports: [RouterModule, BreadcrumbComponent, CommonModule],
  templateUrl: './admin-edit-profile.component.html',
  styleUrl: './admin-edit-profile.component.css'
})
export class AdminEditProfileComponent {
  profile?:ProfileResponse;
  breadcrumbPaths: string[] = ['Home', 'Admin', 'Manage Profile', 'Edit Profile'];
  validationMessages!: ProfileValidation;
  
  constructor(private adminProfileService:AdminProfileService, private alertService: AlertService, private router: Router, private tokenStorageService : TokenStorageService) 
  {
  }

  ngOnInit() {
    this.showProfile();
  }

  showProfile() {
    this.adminProfileService.showProfile().subscribe(profile => {
      this.profile = profile as ProfileResponse;
    });
  }

  editProfile(firstname: string, lastname: string, username: string) {
    const profileRequest = new ProfileResponse(firstname, lastname, username, "ADMIN");
    this.alertService.clearErrorMessage()
    this.alertService.clearSuccessMessage()
    
    this.adminProfileService.editProfile(profileRequest).subscribe({
      next: value => {
        let messageResponse = value as MessageResponse;
        this.alertService.setSuccessMessage(messageResponse.message)
        if(this.profile?.username == username)
        {
          this.router.navigate(['admin/profile'])
        } 
        else 
        {
          this.tokenStorageService.clearRefreshToken()
          this.tokenStorageService.clearAccessToken()
          this.router.navigate([''])
        }
      },
      error: err => {
        let messageResponse = err.error as MessageResponse;
        this.alertService.setErrorMessage(messageResponse.message)
        this.validationMessages = err.error.validation as ProfileValidation;
        },
      complete: () => {
      }
    });

     
  }

}
