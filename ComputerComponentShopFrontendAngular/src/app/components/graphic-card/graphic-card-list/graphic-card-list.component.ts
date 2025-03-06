import { Component, OnInit } from '@angular/core';
import { GraphicCardService } from '../../../services/graphic-card.service';
import { GraphicCard } from '../../../models/graphic-card';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-graphic-card-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './graphic-card-list.component.html',
  styleUrls: ['./graphic-card-list.component.css'],
})
export class GraphicCardListComponent implements OnInit {
  graphicCards: GraphicCard[] = [];
  displayedColumns: string[] = ['id', 'gpu_count', 'gpu_frequency', 'memory_count', 'memory_frequency', 'actions'];

  constructor(private graphicCardService: GraphicCardService, private router: Router) {}

  ngOnInit(): void {
    this.loadGraphicCards();
  }

  loadGraphicCards(): void {
    this.graphicCardService.getGraphicCards().subscribe((cards) => {
      this.graphicCards = cards;
    });
  }

  editGraphicCard(id: number): void {
    this.router.navigate(['/graphic-cards/edit', id]);
  }

  deleteGraphicCard(id: number): void {
    this.graphicCardService.deleteGraphicCard(id).subscribe(() => {
      this.loadGraphicCards();
    });
  }

  addNewGraphicCard() : void {
    this.router.navigate(['/graphic-cards/add']);
  }
  
  goToHome() : void {
    this.router.navigate(['/']);
  }
}