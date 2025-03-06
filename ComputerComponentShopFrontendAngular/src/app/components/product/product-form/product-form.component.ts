import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../../services/product.service';
import { ProcessorService } from '../../../services/processor.service';
import { MotherboardService } from '../../../services/motherboard.service';
import { GraphicCardService } from '../../../services/graphic-card.service';
import { Processor } from '../../../models/processor';
import { Motherboard } from '../../../models/motherboard';
import { GraphicCard } from '../../../models/graphic-card';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { DecimalPipe } from "../../../pipes/decimap.pipe";

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    DecimalPipe
],
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css'],
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  isEditMode = false;
  productId: number | null = null;
  processors: Processor[] = [];
  motherboards: Motherboard[] = [];
  graphicCards: GraphicCard[] = [];
  types: String[] = ["processor", "motherbaord", "graphic card"];

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private processorService: ProcessorService,
    private motherboardService: MotherboardService,
    private graphicCardService: GraphicCardService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      manufacturer: ['', Validators.required],
      type: ['', Validators.required],
      processor: [null],
      motherboard: [null],
      graphicCard: [null],
    });
  }

  ngOnInit(): void {
    this.productId = +this.route.snapshot.paramMap.get('id')!;

    this.processorService.getProcessors().subscribe((data) => (this.processors = data));
    this.motherboardService.getMotherboards().subscribe((data) => (this.motherboards = data));
    this.graphicCardService.getGraphicCards().subscribe((data) => (this.graphicCards = data));

    if (this.productId) {
      this.isEditMode = true;
      this.loadProductForEdit();
    }
  }

  loadProductForEdit(): void {
    this.productService.getProductById(this.productId!).subscribe((product) => {
      this.productForm.patchValue({
        ...product,
        processor: product.processor,
        motherboard: product.motherboard,
        graphicCard: product.graphicCard,
      });
    });
  }

  validateOptionalObjects(group: FormGroup): { [key: string]: any } | null {
    const processorId = group.get('processor')?.value;
    const motherboardId = group.get('motherboard')?.value;
    const graphicCardId = group.get('graphicCard')?.value;

    const selectedCount = [processorId, motherboardId, graphicCardId].filter(
      (id) => id !== null
    ).length;

    if (selectedCount > 1) {
      return { multipleOptionalObjectsSelected: true };
    }
    return null;
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      const productData = this.productForm.value;
      console.log(productData);

      if (this.isEditMode) {
        this.productService.updateProduct(this.productId!, productData).subscribe(() => {
          this.router.navigate(['/products']);
        });
      } else {
        this.productService.createProduct(productData).subscribe(() => {
          this.router.navigate(['/products']);
        });
      }
    }
  }
}