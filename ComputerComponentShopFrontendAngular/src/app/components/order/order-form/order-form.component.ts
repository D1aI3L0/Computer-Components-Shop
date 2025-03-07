import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '../../../services/order.service';
import { ClientService } from '../../../services/client.service';
import { ProductService } from '../../../services/product.service';
import { Client } from '../../../models/client';
import { Product } from '../../../models/product';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { DecimalPipe } from "../../../pipes/decimap.pipe";

@Component({
  selector: 'app-order-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    DecimalPipe
],
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css'],
})
export class OrderFormComponent implements OnInit {
  orderForm: FormGroup;
  isEditMode = false;
  orderId: number | null = null;
  clients: Client[] = [];
  products: Product[] = [];

  constructor(
    private fb: FormBuilder,
    private orderService: OrderService,
    private clientService: ClientService,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.orderForm = this.fb.group({
      totalPrice: [''],
      orderDate: ['', Validators.required],
      status: ['', Validators.required],
      paymentMethod: ['', Validators.required],
      client: ['', Validators.required],
      products: [[], Validators.required],
    });
  }

  ngOnInit(): void {
    this.orderId = +this.route.snapshot.paramMap.get('id')!;

    this.clientService.getClients().subscribe((clients) => (this.clients = clients));
    this.productService.getProducts().subscribe((products) => {
      this.products = products;
    });

    this.orderForm.get('products')?.valueChanges.subscribe((selectedProducts) => {
      this.calculateTotalPrice(selectedProducts);
    });

    if (this.orderId) {
      this.isEditMode = true;
      this.loadOrderForEdit();
    }
  }

  loadOrderForEdit(): void {
    this.orderService.getOrderById(this.orderId!).subscribe((order) => {
      this.orderForm.patchValue({
        totalPrice: order.totalPrice,
        orderDate: order.orderDate,
        status: order.status,
        paymentMethod: order.paymentMethod,
        client: order.client,
        products: order.products,
      });
    });
  }

  calculateTotalPrice(selectedProduct: Product[]): void {
    const selectedProducts = this.products.filter((product) =>
      selectedProduct.includes(product)
    );
    this.orderForm.patchValue({ totalPrice: selectedProducts.reduce((sum, product) => sum + product.price, 0)}); 
  }

  onSubmit(): void {
    if (this.orderForm.valid) {
      const orderData = {
        ...this.orderForm.value,
        orderDate: new Date(this.orderForm.value.orderDate).toISOString(), 
      };

      if (this.isEditMode) {
        this.orderService.updateOrder(this.orderId!, orderData).subscribe(() => {
          this.router.navigate(['/orders']);
        });
      } else {
        this.orderService.createOrder(orderData).subscribe(() => {
          this.router.navigate(['/orders']);
        });
      }
    }
  }
}