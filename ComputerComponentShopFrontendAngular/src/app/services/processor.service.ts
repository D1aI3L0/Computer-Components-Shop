import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Processor } from '../models/processor';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProcessorService {
  private apiUrl = 'http://localhost:8080/processors'; 

  constructor(private http: HttpClient) {}

  getProcessors(): Observable<Processor[]> {
    return this.http.get<Processor[]>(this.apiUrl);
  }

  getProcessorById(id: number): Observable<Processor> {
    return this.http.get<Processor>(`${this.apiUrl}/${id}`);
  }

  createProcessor(processor: Processor): Observable<Processor> {
    return this.http.post<Processor>(this.apiUrl, processor);
  }

  updateProcessor(id: number, processor: Processor): Observable<Processor> {
    return this.http.put<Processor>(`${this.apiUrl}/${id}`, processor);
  }

  deleteProcessor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}