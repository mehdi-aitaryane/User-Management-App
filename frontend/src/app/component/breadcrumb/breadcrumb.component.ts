import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-breadcrumb',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './breadcrumb.component.html',
  styleUrl: './breadcrumb.component.css'
})
export class BreadcrumbComponent {
  @Input() paths: string[] = [];

  constructor(private router: Router) {}

  getPathLink(index: number): string {
    const route = '/' + this.paths.slice(0, index + 1).join('/');
    return route
    .replace("/Home", "/")
    .replace("Manage Profile", "profile")
    .toLowerCase();
  }
}