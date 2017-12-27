import { Pipe, PipeTransform } from '@angular/core';

import { Warehouse } from './warehouse';

@Pipe({
  name: 'warehouseFullAddress'
})
export class WarehouseFullAddressPipe implements PipeTransform {

  transform(warehouse: Warehouse): any {
    return warehouse && `${warehouse.country}, ${warehouse.city}, ${warehouse.street}, ${warehouse.house}`;
  }

}
