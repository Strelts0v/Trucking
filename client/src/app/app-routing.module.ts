import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthComponent } from './users/auth';
import { MainComponent } from './main';
import { AuthGuard } from './users';
import { InvoiceListComponent } from './invoices/invoice-list/invoice-list.component';
import { WaybillListComponent } from './waybills/waybill-list/waybill-list.component';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {path: 'invoices', component: InvoiceListComponent},
  {path: 'waybills', component: WaybillListComponent},
  {path: '', component: MainComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
