import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Car, CarType } from './car';

@Injectable()
export class CarService {


  constructor() {
  }

  getCar(id: number): Observable<Car> {
    if (id) {
      return of(CARS_DATA.find(car => car.id === id));
    } else {
      return of(new Car());
    }
  }

  getCars(pageNumber: number, pageSize: number): Observable<Car[]> {
    const offset = (pageNumber - 1) * pageSize;
    return of(CARS_DATA.slice(offset, offset + pageSize));
  }

  size(): Observable<number> {
    return of(CARS_DATA.length);
  }


}

const CARS_DATA: Car[] = [
  {
    id: 1,
    name: 'MaZ 31056',
    consumption: 15,
    type: CarType.TANK,
    number: '1234AE-5'

  },
  {
    id: 2,
    name: 'DAF 4404',
    consumption: 10,
    type: CarType.FRIDGE,
    number: '1334AE-5'

  },
  {
    id: 3,
    name: 'Scania AR262',
    consumption: 11,
    type: CarType.CARCASE,
    number: '1234AK-5'

  },
  {
    id: 4,
    name: 'KAMAZ 31056',
    consumption: 15,
    type: CarType.FRIDGE,
    number: '1234BE-5'

  },
  {
    id: 5,
    name: 'MaZ 31056',
    consumption: 15,
    type: CarType.TANK,
    number: '1236AE-5'

  },
  {
    id: 6,
    name: 'MaZ 31056',
    consumption: 15,
    type: CarType.TANK,
    number: '1224AE-5'

  },
  {
    id: 7,
    name: 'MaZ 31056',
    consumption: 15,
    type: CarType.TANK,
    number: '1294AE-5'

  },
  {
    id: 8,
    name: 'MaZ 31056',
    consumption: 15,
    type: CarType.TANK,
    number: '1235AE-5'

  }
];
