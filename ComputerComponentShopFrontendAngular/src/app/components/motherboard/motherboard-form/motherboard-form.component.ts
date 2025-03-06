// src/app/components/motherboards/motherboard-form/motherboard-form.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MotherboardService } from '../../../services/motherboard.service';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-motherboard-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatInputModule, MatButtonModule, MatCardModule],
  templateUrl: './motherboard-form.component.html',
  styleUrls: ['./motherboard-form.component.css'],
})
export class MotherboardFormComponent implements OnInit {
  motherboardForm: FormGroup;
  isEditMode = false;
  motherboardId: number | null = null; 

  constructor(
    private fb: FormBuilder,
    private motherboardService: MotherboardService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.motherboardForm = this.fb.group({
      memorySlots: ['', [Validators.required, Validators.min(1)]],
      chipset: ['', Validators.required],
      formFactor: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.motherboardId = +this.route.snapshot.paramMap.get('id')!;

    if (this.motherboardId) {
      this.isEditMode = true;
      this.loadMotherboardForEdit();
    }
  }

  loadMotherboardForEdit(): void {
    this.motherboardService.getMotherboardById(this.motherboardId!).subscribe((motherboard) => {
      this.motherboardForm.patchValue(motherboard); 
    });
  }

  onSubmit(): void {
    if (this.motherboardForm.valid) {
      if (this.isEditMode) {
        this.motherboardService
          .updateMotherboard(this.motherboardId!, this.motherboardForm.value)
          .subscribe(() => {
            this.router.navigate(['/motherboards']);
          });
      } else {
        this.motherboardService.createMotherboard(this.motherboardForm.value).subscribe(() => {
          this.router.navigate(['/motherboards']);
        });
      }
    }
  }
}