import { Component } from '@angular/core';
import { BreadcrumbComponent } from '../breadcrumb/breadcrumb.component';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../security/auth.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [BreadcrumbComponent, RouterModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  breadcrumbPaths: string[] = ['Home', 'User'];
  
  constructor(private authService: AuthService){}

  logout() {
    this.authService.logout();
  }

}
