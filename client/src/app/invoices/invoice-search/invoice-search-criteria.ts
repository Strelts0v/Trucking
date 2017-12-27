import { InvoiceStatus } from '../invoice';

export class InvoiceSearchCriteria {
  issueDate: string | Date;
  checkDate: string | Date;
  status: InvoiceStatus;
  inspector: string;
}
