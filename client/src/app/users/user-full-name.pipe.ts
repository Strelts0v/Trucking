import { Pipe, PipeTransform } from '@angular/core';

import { User } from './user';

@Pipe({
  name: 'userFullName'
})
export class UserFullNamePipe implements PipeTransform {

  transform(user: User): string {
    return user && `${user.firstName} ${user.lastName} ${user.middleName}`;
  }

}
