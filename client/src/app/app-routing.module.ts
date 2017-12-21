import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainComponent } from './main';
import { AuthGuard } from './users';
import { RoleGuard } from './users';
import { AuthComponent, UserListComponent } from './users';
import { InvoiceListComponent } from './invoices';
import { WaybillListComponent } from './waybills';
import { ClientListComponent } from './clients';
import { WelcomeComponent } from './welcome/welcome.component';
import { WarehouseListComponent } from './warehouses';
import { ProfitLossStatementComponent } from './report';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {
    path: '', component: MainComponent, data: {title: 'Trucking'}, canActivate: [AuthGuard],
    children: [
      {path: '', component: WelcomeComponent, data: {title: 'Welcome'}},
      {path: 'users', component: UserListComponent, data: {title: 'Users'}, canActivate: [RoleGuard]},
      {path: 'invoices', component: InvoiceListComponent, data: {title: 'Consignment notes'}, canActivate: [RoleGuard]},
      {path: 'waybills', component: WaybillListComponent, data: {title: 'Waybills'}, canActivate: [RoleGuard]},
      {path: 'clients', component: ClientListComponent, data: {title: 'Clients'}, canActivate: [RoleGuard]},
      {path: 'warehouses', component: WarehouseListComponent, data: {title: 'Warehouses'}, canActivate: [RoleGuard]},
      {path: 'report', component: ProfitLossStatementComponent, data: {title: 'P&L'}, canActivate: [RoleGuard]}
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
