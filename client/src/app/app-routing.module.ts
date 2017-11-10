import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainComponent } from './main/main.component';

const routes: Routes = [
    { path: '', redirectTo: '/main', pathMatch: 'full' },
    { path: 'main', component: MainComponent },
];

@NgModule({
    // Initialize RouterModule with specified routes and start
    // listening changes in browser
    imports: [ RouterModule.forRoot(routes) ],  
    exports: [ RouterModule ]
})

export class AppRoutingModule {}