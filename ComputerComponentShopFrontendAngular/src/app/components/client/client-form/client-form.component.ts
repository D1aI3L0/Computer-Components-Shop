import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ClientService } from '../../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatInputModule, MatButtonModule, MatCardModule],
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css'],
})
export class ClientFormComponent {
  clientForm: FormGroup;
  clientId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.clientForm = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      patronymic: [''],
      emailAddress: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required],
    });
  }
  
  ngOnInit(): void {
    this.clientId = +this.route.snapshot.paramMap.get('id')!;

    if (this.clientId) {
      this.clientService.getClientById(this.clientId).subscribe((client) => {
        this.clientForm.patchValue(client);
      });
    }
  }

  onSubmit(): void {
    if (this.clientForm.valid) {
      if (this.clientId) {
        this.clientService
          .updateClient(this.clientId, this.clientForm.value)
          .subscribe(() => {
            this.router.navigate(['/clients']);
          });
      } else {
        this.clientService.createClient(this.clientForm.value).subscribe(() => {
          this.router.navigate(['/clients']);
        });
      }
    }
  }
}