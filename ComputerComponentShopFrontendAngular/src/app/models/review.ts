import { Client } from "./client";
import { Product } from "./product";

export interface Review {
  id: number;
  client: Client;
  product: Product;
  review: string;
}