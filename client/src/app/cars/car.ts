export class Car {
  id: number;
  name: string;
  number: string;
  type: CarType;
  consumption: number;
}

export enum CarType {
  CARCASE = 'CARCASE',
  FRIDGE = 'FRIDGE',
  TANK = 'TANK'
}
