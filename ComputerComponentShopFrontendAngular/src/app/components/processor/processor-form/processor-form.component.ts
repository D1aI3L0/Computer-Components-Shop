import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ProcessorService } from '../../../services/processor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { InputHelperService } from '../../../services/input-helper.service';

@Component({
  selector: 'app-processor-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatInputModule, MatButtonModule, MatCardModule],
  templateUrl: './processor-form.component.html',
  styleUrls: ['./processor-form.component.css'],
})
export class ProcessorFormComponent {
  processorForm: FormGroup;
  processorId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private processorService: ProcessorService,
    private router: Router,
    private route: ActivatedRoute,
    private inputHelperService: InputHelperService
  ) {
    this.processorForm = this.fb.group({
      threadsCount: ['', [Validators.required, Validators.min(1)]],
      clockFrequency: ['', [Validators.required, Validators.min(0)]],
      maxFrequency: ['', [Validators.required, Validators.min(0)]],
      cpuCount: ['', [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {
    this.processorId = +this.route.snapshot.paramMap.get('id')!;

    if (this.processorId) {
      this.processorService.getProcessorById(this.processorId).subscribe((processor) => {
        this.processorForm.patchValue(processor);
      });
    }
  }

  onInput(event: Event): void {
    this.inputHelperService.restrictDecimalInput(event);
  }

  onSubmit(): void {
    if (this.processorForm.valid) {
      if (this.processorId) {
        this.processorService
          .updateProcessor(this.processorId, this.processorForm.value)
          .subscribe(() => {
            this.router.navigate(['/processors']);
          });
      } else {
        this.processorService.createProcessor(this.processorForm.value).subscribe(() => {
          this.router.navigate(['/processors']);
        });
      }
    }
  }
}