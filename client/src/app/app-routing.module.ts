import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthComponent } from './users/auth/auth.component';
import { MainComponent } from './main/main.component';
import { AuthGuard } from './users/auth.guard';
import { UserListComponent } from './users/user-list/index';
import { InvoicesComponent } from './document/invoices/invoices.component';

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {path: '', component: MainComponent, canActivate: [AuthGuard]},
  {path: 'invoices', component: InvoicesComponent},
  {path: 'users', component: UserListComponent },

  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
