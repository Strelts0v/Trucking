export class Cars {
  id: number;
  name: string;
  number: string;
  type: Type;
  consumption: number;
}

enum Type {
  CARCASE = 'CARCASE',
  FRIDGE = 'FRIDGE',
  TANK = 'TANK'
}

