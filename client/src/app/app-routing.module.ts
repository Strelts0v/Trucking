import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthComponent } from './users/auth';
import { MainComponent } from './main';
import { AuthGuard } from './users';
import { UserListComponent } from './users/user-list';
import { InvoiceListComponent } from './invoices/invoice-list/invoice-list.component';
import { WaybillListComponent } from './waybills/waybill-list/waybill-list.component';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {path: '', /*component: MainComponent, canActivate: [AuthGuard]*/redirectTo: 'lists/users', pathMatch: 'full'},
  {path: 'lists', component: MainComponent, children: [
      {path: 'users', component: UserListComponent, data: [{subtitle: 'Users'}]},
      {path: 'invoices', component: InvoiceListComponent, data: [{subtitle: 'Invoices'}]},
      {path: 'waybills', component: WaybillListComponent, data: [{subtitle: 'Waybills'}]},
    ]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
