import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthComponent } from './users/auth';
import { MainComponent } from './main';
import { AuthGuard } from './users';
import {UserListComponent} from './users/user-list/user-list.component';
import {InvoiceListComponent} from './invoices/invoice-list/invoice-list.component';
import {WaybillListComponent} from './waybills/waybill-list/waybill-list.component';
import {CarListComponent} from './cars/car-list/car-list.component';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {path: 'users', component: UserListComponent},
  {path: 'invoices', component: InvoiceListComponent},
  {path: 'waybills', component: WaybillListComponent},
  {path: 'cars', component: CarListComponent},
  {path: '', component: MainComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
