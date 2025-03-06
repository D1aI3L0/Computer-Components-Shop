import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <h1>Computer componets shop</h1>
    <router-outlet></router-outlet>
  `,
})
export class AppComponent {}