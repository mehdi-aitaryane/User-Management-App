import { Component } from '@angular/core';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../security/auth.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [BreadcrumbComponent, RouterModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
  breadcrumbPaths: string[] = ['Home', 'Admin'];

  constructor(private authService: AuthService){}

  logout() {
    this.authService.logout();
  }

}
