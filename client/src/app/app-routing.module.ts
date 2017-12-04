import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthComponent } from './users/auth';
import { MainComponent } from './main';
import { AuthGuard } from './users';
import { InvoicesComponent } from './document/invoices/invoices.component';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {path: 'invoices', component: InvoicesComponent},
  {path: '', component: MainComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
