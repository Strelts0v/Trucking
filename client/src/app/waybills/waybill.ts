import { User } from '../users';
import { WaybillCheckpoint } from './waybill-checkpoint';

export class Waybill {
  id: number;
  invoiceId: number;
  departureDate: string;
  driver: User;
  car: any;
  from: any;
  to: any;
  waybillCheckpoints: WaybillCheckpoint[];
  status: WaybillStatus;
  issueDate: string;
}

export enum WaybillStatus {
  STARTED = 'Started',
  COMPLETED = 'Completed'
}
