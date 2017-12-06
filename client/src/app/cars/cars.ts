export class Cars {
  id: number;
  name: string;
  number: string;
  type: CarsType;
  consumption: number;
}

export enum CarsType {
  CARCASE = 'CARCASE',
  FRIDGE = 'FRIDGE',
  TANK = 'TANK'
}

