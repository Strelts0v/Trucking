import { Item } from '../item';

export class Consignment {
  item: Item;
  amount: number;
  status: ConsignmentStatus;
}

export enum ConsignmentStatus {
  REGISTERED = 'Registered',
  CHECKED = 'Checked',
  DELIVERED = 'Delivered',
  LOST = 'Lost'
}
