import { Processor } from './processor';
import { Motherboard } from './motherboard';
import { GraphicCard } from './graphic-card';

export interface Product {
  id: number;
  price: number;
  name: string;
  manufacturer: string;
  type: string;
  processor?: Processor;
  motherboard?: Motherboard;
  graphicCard?: GraphicCard;
}