import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { MatInputModule, MatButtonModule, MatCardModule, MatProgressSpinnerModule } from '@angular/material';
import { JwtModule } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';
import { AuthComponent } from './users/auth/index';
import { MainComponent } from './main/index';
import { AuthGuard, AuthService, UserService } from './users/index';
import { AppRoutingModule } from './/app-routing.module';
import { CarsComponent } from './cars/cars.component';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatProgressSpinnerModule,
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
    MainComponent,
    CarsComponent
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
