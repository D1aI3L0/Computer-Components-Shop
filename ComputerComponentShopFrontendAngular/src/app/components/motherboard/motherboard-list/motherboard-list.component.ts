import { Component, OnInit } from '@angular/core';
import { MotherboardService } from '../../../services/motherboard.service';
import { Motherboard } from '../../../models/motherboard';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-motherboard-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './motherboard-list.component.html',
  styleUrls: ['./motherboard-list.component.css'],
})
export class MotherboardListComponent implements OnInit {
  motherboards: Motherboard[] = [];
  displayedColumns: string[] = ['id', 'memory_slots', 'chipset', 'form_factor', 'actions'];

  constructor(private motherboardService: MotherboardService, private router: Router) {}

  ngOnInit(): void {
    this.loadMotherboards();
  }

  loadMotherboards(): void {
    this.motherboardService.getMotherboards().subscribe((motherboards) => {
      this.motherboards = motherboards;
    });
  }

  editMotherboard(id: number): void {
    this.router.navigate(['/motherboards/edit', id]);
  }

  deleteMotherboard(id: number): void {
    this.motherboardService.deleteMotherboard(id).subscribe(() => {
      this.loadMotherboards();
    });
  }

  addNewMotherboard() : void {
    this.router.navigate(['/motherboards/add']);
  }
}