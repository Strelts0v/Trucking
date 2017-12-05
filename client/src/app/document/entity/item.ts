export class Item {
  id: number;
  name: string;
  price: number;
  type: ItemUnit;
}

export enum ItemUnit {
  PIECE = 'pc',
  KILOGRAM = 'kg',
  LITER = 'l'
}
