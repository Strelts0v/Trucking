import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthComponent } from './users/auth';
import { MainComponent } from './main';
import { AuthGuard } from './users';
import { UserListComponent } from './users/user-list';
import { InvoiceListComponent } from './invoices/invoice-list/invoice-list.component';
import { WaybillListComponent } from './waybills/waybill-list/waybill-list.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {
    path: '', component: MainComponent, data: {title: 'Trucking'}, children: [
      {path: '', component: WelcomeComponent, data: {title: 'Welcome'}},
      {path: 'users', component: UserListComponent, data: {title: 'Users'}},
      {path: 'invoices', component: InvoiceListComponent, data: {title: 'Consignment notes'}},
      {path: 'waybills', component: WaybillListComponent, data: {title: 'Waybills'}},
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
