import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { GraphicCardService } from '../../../services/graphic-card.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { InputHelperService } from '../../../services/input-helper.service';

@Component({
  selector: 'app-graphic-card-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatInputModule, MatButtonModule, MatCardModule],
  templateUrl: './graphic-card-form.component.html',
  styleUrls: ['./graphic-card-form.component.css'],
})
export class GraphicCardFormComponent {
  graphicCardForm: FormGroup;
  graphicCardId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private graphicCardService: GraphicCardService,
    private router: Router,
    private route: ActivatedRoute,
    private inputHelper: InputHelperService
  ) {
    this.graphicCardForm = this.fb.group({
      gpuCount: ['', [Validators.required, Validators.min(1)]],
      gpuFrequency: ['', [Validators.required, Validators.min(0)]],
      memoryCount: ['', [Validators.required, Validators.min(1)]],
      memoryFrequency: ['', [Validators.required, Validators.min(0)]],
    });
  }
  
  ngOnInit(): void {
    this.graphicCardId = +this.route.snapshot.paramMap.get('id')!;

    if (this.graphicCardId) {
      this.graphicCardService.getGraphicCardById(this.graphicCardId).subscribe((card) => {
        this.graphicCardForm.patchValue(card);
      });
    }
  }

  onInput(event: Event): void {
    this.inputHelper.restrictDecimalInput(event);
  }

  onSubmit(): void {
    if (this.graphicCardForm.valid) {
      if (this.graphicCardId) {
        this.graphicCardService
          .updateGraphicCard(this.graphicCardId, this.graphicCardForm.value)
          .subscribe(() => {
            this.router.navigate(['/graphic-cards']);
          });
      } else {
        this.graphicCardService.createGraphicCard(this.graphicCardForm.value).subscribe(() => {
          this.router.navigate(['/graphic-cards']);
        });
      }
    }
  }
}