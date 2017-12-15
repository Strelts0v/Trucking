import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainComponent } from './main';
import { AuthGuard } from './users';
import { RoleGuard } from './users';
import { AuthComponent, UserListComponent } from './users/index';
import { InvoiceListComponent } from './invoices/invoice-list/invoice-list.component';
import { WaybillListComponent } from './waybills/waybill-list/waybill-list.component';
import { ClientListComponent } from './clients/index';
import { WelcomeComponent } from './welcome/welcome.component';
import { CarListComponent } from './cars/car-list/car-list.component';
import {BirthdayCongratulationComponent} from './birthday-congratulation/birthday-congratulation.component';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {
    path: '', component: MainComponent,  data: {title: 'Trucking'},
    children: [
      {path: '', component: WelcomeComponent, data: {title: 'Welcome'}},
      {path: 'users', component: UserListComponent,  data: {title: 'Users'}},
      {path: 'invoices', component: InvoiceListComponent,  data: {title: 'Consignment notes'}},
      {path: 'waybills', component: WaybillListComponent, data: {title: 'Waybills'}},
      {path: 'cars', component: CarListComponent, data: {title: 'Cars'}}
    ]
  },
  {path: '**', redirectTo: ''},
  {path: 'template' , component: BirthdayCongratulationComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
