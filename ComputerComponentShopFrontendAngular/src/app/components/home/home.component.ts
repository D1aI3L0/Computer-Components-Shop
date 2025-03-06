import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
   constructor(
      private router: Router,
      private route: ActivatedRoute
   ){}

   goToProducts() : void
   {
    this.router.navigate(["/products"]);
   }

   goToClients() : void
   {
    this.router.navigate(["/clients"]);
   }

   goToProcessors() : void
   {
    this.router.navigate(["/processors"]);
   }

   goToMotherboards() : void
   {
    this.router.navigate(["/motherboards"]);
   }

   goToGraphiCards() : void
   {
    this.router.navigate(["/graphic-cards"]);
   }

   goToReviews() : void
   {
    this.router.navigate(["/reviews"]);
   }

   goToOrders() : void
   {
    this.router.navigate(["/orders"]);
   }
}
