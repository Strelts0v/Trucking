export class Cars {
  id: number;
  name: string;
  number: string;
  type: CarsType;
  consumption: number;
}

enum CarsType {
  CARCASE = 'CARCASE',
  FRIDGE = 'FRIDGE',
  TANK = 'TANK'
}

