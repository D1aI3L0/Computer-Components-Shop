import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProcessorService } from '../../../services/processor.service';
import { Processor } from '../../../models/processor';
import { Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DecimalPipe } from "../../../pipes/decimap.pipe";

@Component({
  selector: 'app-processor-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule, DecimalPipe],
  templateUrl: './processor-list.component.html',
  styleUrls: ['./processor-list.component.css'],
})
export class ProcessorListComponent implements OnInit {
  processors: Processor[] = [];
  displayedColumns: string[] = ['id', 'threads_count', 'clock_frequency', 'max_frequency', 'cpu_count', 'actions'];

  constructor(private processorService: ProcessorService, private router: Router) {}

  ngOnInit(): void {
    this.loadProcessors();
  }

  loadProcessors(): void {
    this.processorService.getProcessors().subscribe((processors) => {
      this.processors = processors;
    });
  }

  editProcessor(id: number): void {
    this.router.navigate(['/processors/edit', id]);
  }

  deleteProcessor(id: number): void {
    this.processorService.deleteProcessor(id).subscribe(() => {
      this.loadProcessors();
    });
  }

  addNewProcessor(): void {
    this.router.navigate(['/processors/add']);
  }

  goToHome() : void {
    this.router.navigate(['/']);
  }
}