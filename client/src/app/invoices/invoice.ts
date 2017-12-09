import { Consignment } from './consignment';
import { User } from '../users';

export class Invoice {
  id: number;
  issueDate: string;
  consignments: Consignment[] = [];
  status: InvoiceStatus;
  checkDate: string;
  creator: User;
  inspector: User;
  client: string;
}

export enum InvoiceStatus {
  ISSUED = 'Issued',
  CHECKED = 'Checked',
  DELIVERED = 'Delivered'
}
