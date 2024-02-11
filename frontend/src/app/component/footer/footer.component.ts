import { CommonModule, Location } from '@angular/common';
import { Component } from '@angular/core';
import { NavigationStart, Router, RouterModule } from '@angular/router';
import { Subscription, filter } from 'rxjs';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  year = new Date().getFullYear()
  constructor(private router: Router, private location: Location){
  }

}
