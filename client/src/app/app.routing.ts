import { Routes, RouterModule } from '@angular/router';

import { MainComponent } from './main/index';
import { AuthComponent } from './users/auth/index';
import { AuthGuard } from './users/index';

const appRoutes: Routes = [
    { path: 'auth', component: AuthComponent },
    { path: '', component: MainComponent, canActivate: [AuthGuard] },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);