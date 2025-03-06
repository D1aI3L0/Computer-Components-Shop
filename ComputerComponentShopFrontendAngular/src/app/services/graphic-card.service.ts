import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GraphicCard } from '../models/graphic-card';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GraphicCardService {
  private apiUrl = 'http://localhost:8080/graphic-cards';

  constructor(private http: HttpClient) {}

  getGraphicCards(): Observable<GraphicCard[]> {
    return this.http.get<GraphicCard[]>(this.apiUrl);
  }

  getGraphicCardById(id: number): Observable<GraphicCard> {
    return this.http.get<GraphicCard>(`${this.apiUrl}/${id}`);
  }

  createGraphicCard(graphicCard: GraphicCard): Observable<GraphicCard> {
    return this.http.post<GraphicCard>(this.apiUrl, graphicCard);
  }

  updateGraphicCard(id: number, graphicCard: GraphicCard): Observable<GraphicCard> {
    return this.http.put<GraphicCard>(`${this.apiUrl}/${id}`, graphicCard);
  }

  deleteGraphicCard(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}