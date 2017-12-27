import { Item } from '../items/item';

export class Consignment {
  item: Item;
  amount: number;
  status: ConsignmentStatus;
}

export enum ConsignmentStatus {
  REGISTERED = 'REGISTERED',
  CHECKED = 'CHECKED',
  DELIVERED = 'DELIVERED',
  LOST = 'LOST'
}
