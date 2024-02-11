import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MenuComponent } from "./component/menu/menu.component";
import { FooterComponent } from './component/footer/footer.component';
import { AlertComponent } from './component/alert/alert.component';
import { Subscription, interval } from 'rxjs';
import { AlertService } from './service/alert.service';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [CommonModule, RouterOutlet, MenuComponent, AlertComponent, FooterComponent]
})
export class AppComponent {
  title = 'User Management App';
  error_message?:string;
  success_message?:string;
  alertSubscription!: Subscription;

  constructor(private alertService: AlertService){}

  ngOnInit() {


    this.alertSubscription = interval(0)
    .subscribe(val => {
      this.success_message = this.alertService.getSuccessMessage()
      this.error_message = this.alertService.getErrorMessage()
    });

  }


  ngOnDestroy() {
    this.alertSubscription.unsubscribe();
  }

}
