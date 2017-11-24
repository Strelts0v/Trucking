import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainComponent } from './components/index';
import { LoginComponent } from './components/index';
import { AuthGuard } from './guards/index';

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: '', component: MainComponent, canActivate: [AuthGuard] },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

@NgModule({
    // Initialize RouterModule with specified routes and start
    // listening changes in browser
    imports: [ RouterModule.forRoot(routes) ],  
    exports: [ RouterModule ]
})

export class AppRoutingModule {}