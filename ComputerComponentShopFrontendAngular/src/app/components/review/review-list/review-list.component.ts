// src/app/components/reviews/review-list/review-list.component.ts
import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../../../services/review.service';
import { Review } from '../../../models/review';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css'],
})
export class ReviewListComponent implements OnInit {
  reviews: Review[] = [];
  displayedColumns: string[] = ['client', 'product', 'review', 'actions'];

  constructor(private reviewService: ReviewService, private router: Router) {}

  ngOnInit(): void {
    this.loadReviews();
  }

  loadReviews(): void {
    this.reviewService.getReviews().subscribe((reviews) => {
      this.reviews = reviews;
    });
  }

  editReview(id: number): void {
    this.router.navigate(['/reviews/edit', id]);
  }

  deleteReview(id: number): void {
    this.reviewService.deleteReview(id).subscribe(() => {
      this.loadReviews();
    });
  }

  addNewReview() : void {
    this.router.navigate(['/reviews/add']);
  }

  goToHome() : void {
    this.router.navigate(['/']);
  }
}