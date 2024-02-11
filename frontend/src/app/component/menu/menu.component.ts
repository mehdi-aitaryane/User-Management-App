import { Component } from '@angular/core';
import { AuthService } from '../../security/auth.service';
import { CommonModule } from '@angular/common';
import { NavigationStart, Router, RouterModule } from '@angular/router';
import { AuthStatusService } from '../../security/auth-status.service';
import { Subscription, filter, interval, merge } from 'rxjs';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, RouterModule], // add this to your imports array
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  isAdmin!: boolean;
  isUser!: boolean;
  isVisitor!: boolean;
  refreshReloginSubscription!: Subscription;
  autoReloginSubscription!: Subscription;
  changeStatusSubscription!: Subscription;

  constructor(private authService: AuthService, private authStatusService: AuthStatusService, private router: Router) 
  {
  }

  ngOnInit() {

    this.refreshReloginSubscription = this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe(val => {
      if (this.authStatusService.isAccessTokenExpired() && this.authStatusService.isAuthenticated() && !this.router.navigated) {
        this.authService.relogin()
      }
    });

    this.autoReloginSubscription = interval(1000)
    .pipe(      
      filter(() => this.authStatusService.isAccessTokenAvailable()),
    ).subscribe(val => {
      if (this.authStatusService.isAccessTokenExpired() && this.authStatusService.isAuthenticated() ) {
        this.authService.relogin()
      }
    });

    this.changeStatusSubscription = interval(0).subscribe(val => {
      this.isAdmin = this.authStatusService.isAdminAuthenticated()
      this.isUser = this.authStatusService.isUserAuthenticated()
      this.isVisitor = this.authStatusService.isNotAuthenticated()
    });

  }

  logout() {
    this.authService.logout();
  }

  ngOnDestroy() {
    this.refreshReloginSubscription.unsubscribe();
    this.autoReloginSubscription.unsubscribe();
    this.changeStatusSubscription.unsubscribe();
  }

}
