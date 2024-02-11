import { Component, ViewChild } from '@angular/core';
import { UserService } from '../../service/user.service';
import { CommonModule } from '@angular/common';
import { UserResponse } from '../../response/user-response';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { FormsModule } from '@angular/forms';
import { ConfirmModalComponent } from '../confirm-modal/confirm-modal.component';
import { AlertService } from '../../service/alert.service';
import { MessageResponse } from '../../response/message-response';
import { ShowUserModalComponent } from '../show-user-modal/show-user-modal.component';

@Component({
  selector: 'app-admin-users',
  standalone: true,
  imports: [CommonModule, BreadcrumbComponent, FormsModule, ConfirmModalComponent, ShowUserModalComponent],
  templateUrl: './admin-users.component.html',
  styleUrl: './admin-users.component.css'
})
export class AdminUsersComponent {
  breadcrumbPaths: string[] = ['Home', 'Admin', 'Manage Users'];
  users?: UserResponse[];
  pages?: number[];
  page: number = 1; // Initialize page to 1
  keyword: string = "";
  size: number = 5;
  @ViewChild(ConfirmModalComponent) confirmModal!: ConfirmModalComponent; // ViewChild for ConfirmModalComponent
  @ViewChild(ShowUserModalComponent) showUserModal!: ShowUserModalComponent; // ViewChild for ConfirmModalComponent

  constructor(private adminUserService: UserService, private alertService: AlertService) { }

  ngOnInit() {
    this.showUsers();
  }

  
  searchUsers() {
    this.page = 1;
    this.showUsers();
  }



  showUsers() {
    this.adminUserService.findUsers(this.keyword, this.size, this.page - 1).subscribe((data: any) => {
      this.users = data.content;
      this.pages = Array.from({ length: data.totalPages }, (_, i) => i + 1);
    });
  }

  deleteUser(username: string) {
    this.alertService.clearSuccessMessage()
    this.confirmModal.show()
    this.confirmModal.confirmed.subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.adminUserService.deleteUser(username).subscribe((data: any) => {
          const response = data as MessageResponse
          this.alertService.setSuccessMessage(response.message)
          this.showUsers();
        });
      }
    });
  }

  showUser(username : string)
  {
    this.showUserModal.username = username
    this.showUserModal.getUser()
    this.showUserModal.getUserImage()
    this.showUserModal.show()
  }


  changePage(page: number | string) {
    if (typeof page === 'number') {
      this.page = page;
    } else if (page === 'prev' && this.page > 1) {
      this.page--;
    } else if (page === 'next' && this.page < (this.pages ? this.pages.length : 1)) {
      this.page++;
    }

    this.showUsers();
  }
}
