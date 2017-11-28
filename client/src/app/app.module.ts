import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { JwtModule } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';
import { AuthComponent } from './users/auth/index';
import { MainComponent } from './main/index';
import { AuthGuard, AuthService, UserService } from './users/index';
import { AppRoutingModule } from './/app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatInputModule,
    AppRoutingModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('authenticationToken');
        }
      }
    })
  ],
  declarations: [
    AppComponent,
    AuthComponent,
    MainComponent
  ],
  providers: [
    AuthGuard,
    AuthService,
    UserService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
