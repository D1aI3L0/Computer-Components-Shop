import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewService } from '../../../services/review.service';
import { ClientService } from '../../../services/client.service';
import { ProductService } from '../../../services/product.service';
import { Client } from '../../../models/client';
import { Product } from '../../../models/product';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { debug } from 'console';

@Component({
  selector: 'app-review-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
  ],
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css'],
})
export class ReviewFormComponent implements OnInit {
  reviewForm: FormGroup;
  isEditMode = false;
  reviewId: number | null = null;
  clients: Client[] = [];
  products: Product[] = [];

  constructor(
    private fb: FormBuilder,
    private reviewService: ReviewService,
    private clientService: ClientService,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.reviewForm = this.fb.group({
      client: ['', Validators.required],
      product: ['', Validators.required],
      review: ['', [Validators.required, Validators.maxLength(500)]],
    });
  }

  ngOnInit(): void {
    this.reviewId = +this.route.snapshot.paramMap.get('id')!;

    this.clientService.getClients().subscribe((clients) => (this.clients = clients));
    this.productService.getProducts().subscribe((products) => (this.products = products));

    if (this.reviewId) {
      this.isEditMode = true;
      this.loadReviewForEdit();
    }
  }

  loadReviewForEdit(): void {
    this.reviewService.getReviewById(this.reviewId!).subscribe((review) => {
      this.reviewForm.patchValue({
        client: review.client,
        product: review.product,
        review: review.review,
      });
    });
  }

  onSubmit(): void {
    if (this.reviewForm.valid) {
      const reviewData = this.reviewForm.value;

      if (this.isEditMode) {
        this.reviewService.updateReview(this.reviewId!, reviewData).subscribe(() => {
          this.router.navigate(['/reviews']);
        });
      } else {
        this.reviewService.createReview(reviewData).subscribe(() => {
          this.router.navigate(['/reviews']);
        });
      }
    }
  }
}