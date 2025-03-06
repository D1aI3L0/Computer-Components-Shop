import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Motherboard } from '../models/motherboard';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MotherboardService {
  private apiUrl = 'http://localhost:8080/motherboards';

  constructor(private http: HttpClient) {}

  getMotherboards(): Observable<Motherboard[]> {
    return this.http.get<Motherboard[]>(this.apiUrl);
  }

  getMotherboardById(id: number): Observable<Motherboard> {
    return this.http.get<Motherboard>(`${this.apiUrl}/${id}`);
  }

  createMotherboard(motherboard: Motherboard): Observable<Motherboard> {
    return this.http.post<Motherboard>(this.apiUrl, motherboard);
  }

  updateMotherboard(id: number, motherboard: Motherboard): Observable<Motherboard> {
    return this.http.put<Motherboard>(`${this.apiUrl}/${id}`, motherboard);
  }

  deleteMotherboard(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}