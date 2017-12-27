import { User } from '../users';
import { WaybillCheckpoint } from './waybill-checkpoint';
import { Car } from '../cars/car';
import { Warehouse } from '../warehouses/warehouse';

export class Waybill {
  id: number;
  invoiceId: number;
  invoiceNumber: string;
  departureDate: string;
  driver: User;
  car: Car;
  from: Warehouse;
  to: Warehouse;
  waybillCheckpoints: WaybillCheckpoint[] = [];
  status: WaybillStatus;
  issueDate: string;
  distance: number;
}

export enum WaybillStatus {
  STARTED = 'STARTED',
  COMPLETED = 'COMPLETED'
}
