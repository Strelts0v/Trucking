import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';

import { MatInputModule } from '@angular/material/input';

import { AppComponent }  from './app.component';
import { routing }       from './app.routing';
import { AuthComponent } from './users/auth/index';
import { MainComponent } from './main/index';
import { AuthGuard, AuthService, UserService} from './users/index';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing,
        MatInputModule,
    ],
    declarations: [
        AppComponent,
        AuthComponent,
        MainComponent
    ],
    providers: [
        AuthGuard,
        AuthService,
        UserService,
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }
