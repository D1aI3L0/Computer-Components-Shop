import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/client';
import { Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css'],
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  displayedColumns: string[] = ['name', 'surname', 'patronymic', 'email_address', 'phone_number', 'actions'];
  
  constructor(private clientService: ClientService, private router: Router) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getClients().subscribe((clients) => {
      this.clients = clients;
    });
  }

  editClient(id: number): void {
    this.router.navigate(['/clients/edit', id]);
  }

  deleteClient(id: number): void {
    this.clientService.deleteClient(id).subscribe(() => {
      this.loadClients();
    });
  }

  addNewClient() : void {
    this.router.navigate(['/clients/add']);
  }
}