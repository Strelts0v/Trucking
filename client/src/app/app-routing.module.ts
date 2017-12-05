import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

<<<<<<< HEAD
import { AuthComponent } from './users/auth/auth.component';
import { MainComponent } from './main/main.component';
import { AuthGuard } from './users/auth.guard';
import { UserListComponent } from './users/user-list/index';
=======
import { AuthComponent } from './users/auth';
import { MainComponent } from './main';
import { AuthGuard } from './users';
import { InvoicesComponent } from './document/invoices/invoices.component';
>>>>>>> 2779a581ca7999a816d6c9605d20e4eab9a989f1

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {path: 'invoices', component: InvoicesComponent},
  {path: '', component: MainComponent, canActivate: [AuthGuard]},
<<<<<<< HEAD
  {path: 'users', component: UserListComponent},

  // otherwise redirect to home
=======
>>>>>>> 2779a581ca7999a816d6c9605d20e4eab9a989f1
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
