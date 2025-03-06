import { Client } from './client';
import { Product } from './product';

export interface Order {
  id: number;
  totalPrice: number;
  orderDate: Date;
  status: string;
  paymentMethod: string;
  client: Client;
  products: Product[];
}
