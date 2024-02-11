import { Component } from '@angular/core';
import { AdminProfileService } from '../../service/admin-profile.service';
import { ProfileResponse } from '../../response/profile-response';
import { CommonModule } from '@angular/common';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { RouterModule } from '@angular/router';
import { AlertService } from '../../service/alert.service';
import { MessageResponse } from '../../response/message-response';


@Component({
  selector: 'app-admin-profile',
  standalone: true,
  imports: [BreadcrumbComponent, CommonModule, RouterModule], // add this to your imports array
  templateUrl: './admin-profile.component.html',
  styleUrl: './admin-profile.component.css'
})
export class AdminProfileComponent {
  breadcrumbPaths: string[] = ['Home', 'Admin', 'Manage Profile'];

  profile?: ProfileResponse;
  imageUrl?:string

  constructor(private adminProfileService: AdminProfileService, private alertService: AlertService)
  {
  }

  ngOnInit() {
    this.showProfile();
    this.showProfileImage();
  }

  onFileSelected(event: any) {
    this.alertService.clearSuccessMessage()
    this.alertService.clearErrorMessage()
    const selectedFile = event.target.files[0];
    this.adminProfileService.changePhoto(selectedFile).subscribe({
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


  showProfile() {
    this.adminProfileService.showProfile().subscribe(profile => {
      this.profile = profile as ProfileResponse;
    });
  }

  showProfileImage() {
    this.adminProfileService.getImage().subscribe(imageData  => {
      this.imageUrl = URL.createObjectURL(imageData);
    });
  }

}
