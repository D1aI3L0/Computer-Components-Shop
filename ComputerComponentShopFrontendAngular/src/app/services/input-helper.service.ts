
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class InputHelperService {
  restrictDecimalInput(event: Event, maxDecimals: number = 2): void {
    const input = event.target as HTMLInputElement;
    const value = input.value;
    const regex = new RegExp(`^-?\\d*(\\.\\d{0,${maxDecimals}})?$`);

    if (!regex.test(value)) {
      input.value = value.slice(0, -1);
    }
  }
}