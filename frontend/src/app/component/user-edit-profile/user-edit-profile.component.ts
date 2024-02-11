import { Component } from '@angular/core';
import { ProfileResponse } from '../../response/profile-response';
import { ProfileValidation } from '../../validation/profile-validation';
import { AlertService } from '../../service/alert.service';
import { Router, RouterModule } from '@angular/router';
import { TokenStorageService } from '../../security/token-storage.service';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { CommonModule } from '@angular/common';
import { UserProfileService } from '../../service/user-profile.service';
import { MessageResponse } from '../../response/message-response';

@Component({
  selector: 'app-user-edit-profile',
  standalone: true,
  imports: [RouterModule, BreadcrumbComponent, CommonModule],
  templateUrl: './user-edit-profile.component.html',
  styleUrl: './user-edit-profile.component.css'
})
export class UserEditProfileComponent {
  profile?: ProfileResponse;
  breadcrumbPaths: string[] = ['Home', 'User', 'Manage Profile', 'Edit Profile'];
  validationMessages!: ProfileValidation;

  constructor(private userProfileService: UserProfileService, private alertService: AlertService, private router: Router, private tokenStorageService: TokenStorageService) {
  }

  ngOnInit() {
    this.showProfile();
  }

  showProfile() {
    this.userProfileService.showProfile().subscribe(profile => {
      this.profile = profile as ProfileResponse;
    });
  }

  editProfile(firstname: string, lastname: string, username: string) {
    const profileRequest = new ProfileResponse(firstname, lastname, username, "USER");
    this.alertService.clearErrorMessage()
    this.alertService.clearSuccessMessage()

    this.userProfileService.editProfile(profileRequest).subscribe({
      next: value => {
        let messageResponse = value as MessageResponse;
        this.alertService.setSuccessMessage(messageResponse.message)
        if (this.profile?.username == username) {
          this.router.navigate(['user/profile'])
        }
        else {
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
