import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthComponent } from './users/auth';
import { MainComponent } from './main';
import { AuthGuard } from './users';
import { RoleGuard } from './users';
import { UserListComponent } from './users/user-list';
import { InvoiceListComponent } from './invoices/invoice-list/invoice-list.component';
import { WaybillListComponent } from './waybills/waybill-list/waybill-list.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { CarListComponent } from './cars/car-list/car-list.component';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {
    path: '', component: MainComponent, canActivate: [AuthGuard], data: {title: 'Trucking'}, children: [
      {path: '', component: WelcomeComponent, data: {title: 'Welcome'}},
      {path: 'users', component: UserListComponent, canActivate: [RoleGuard], data: {title: 'Users'}},
      {path: 'invoices', component: InvoiceListComponent, canActivate: [RoleGuard], data: {title: 'Consignment notes'}},
      {path: 'waybills', component: WaybillListComponent, canActivate: [RoleGuard], data: {title: 'Waybills'}},
      {path: 'cars', component: CarListComponent, data: {title: 'Cars'}}
    ]
  },
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
