import { Component, ViewChild } from '@angular/core';
import { ProfileResponse } from '../../response/profile-response';
import { UserProfileService } from '../../service/user-profile.service';
import { CommonModule } from '@angular/common';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { Router, RouterModule } from '@angular/router';
import { MessageResponse } from '../../response/message-response';
import { AlertService } from '../../service/alert.service';
import { TokenStorageService } from '../../security/token-storage.service';
import { ConfirmModalComponent } from '../confirm-modal/confirm-modal.component';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [CommonModule, BreadcrumbComponent, RouterModule, ConfirmModalComponent], // add this to your imports array
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {
  breadcrumbPaths: string[] = ['Home', 'User', 'Manage Profile'];

  profile?: ProfileResponse;

  imageUrl?: string

  @ViewChild(ConfirmModalComponent) confirmModal!: ConfirmModalComponent; // ViewChild for ConfirmModalComponent


  constructor(private userProfileService: UserProfileService, private alertService: AlertService, private router: Router, private tokenStorageService: TokenStorageService) {
  }

  ngOnInit() {
    this.showProfile();
    this.showProfileImage();
  }

  showProfile() {
    this.userProfileService.showProfile().subscribe(profile => {
      this.profile = profile as ProfileResponse;
    });
  }

  deleteProfile() {
    this.alertService.clearSuccessMessage()
    this.confirmModal.show()
    this.confirmModal.confirmed.subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.userProfileService.deleteProfile().subscribe(data => {
          const response = data as MessageResponse
          this.alertService.setSuccessMessage(response.message)
          this.tokenStorageService.clearRefreshToken()
          this.tokenStorageService.clearAccessToken()
          this.router.navigate([''])
        });
      }
    });
  }

  onFileSelected(event: any) {
    this.alertService.clearSuccessMessage()
    this.alertService.clearErrorMessage()
    const selectedFile = event.target.files[0];
    this.userProfileService.changePhoto(selectedFile).subscribe({
      next: value => {
        const response = value as MessageResponse
        this.showProfileImage()
        this.alertService.setSuccessMessage(response.message)
      },
      error: err => {
        console.log(err)
        this.alertService.setErrorMessage(err.error.validation.image)
      },
      complete: () => {
      }
    });

  }



  showProfileImage() {
    this.userProfileService.getImage().subscribe(imageData => {
      this.imageUrl = URL.createObjectURL(imageData);
    });
  }


}
