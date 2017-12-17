import { Consignment } from './consignment';
import { User } from '../users';
import { Waybill } from '../waybills/waybill';
import { LossAct } from './lossact';

export class Invoice {
  id: number;
  number: string;
  client: {id: number; name: string};
  issueDate: string;
  consignments: Consignment[] = [];
  status: InvoiceStatus;
  checkDate: string;
  creator: User;
  inspector: User;
  waybill: Waybill;
  lossActs: LossAct[] = [];
  lossActIssueDate: string;
}

export enum InvoiceStatus {
  ISSUED = 'ISSUED',
  CHECKED = 'CHECKED',
  DELIVERED = 'DELIVERED'
}
