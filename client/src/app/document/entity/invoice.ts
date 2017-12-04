import { Consignment } from './consignment';

export class Invoice {
  id: number;
  issueDate: string;
  consignments: Consignment[] = [];
  status: InvoiceStatus;
  checkDate: string;
  inspector: string;
  client: string;
}

export enum InvoiceStatus {
  ISSUED = 'Issued',
  CHECKED = 'Checked',
  DELIVERED = 'Delivered'
}
