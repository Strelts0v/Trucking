import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';

import { MainComponent, LoginComponent } from './components/index';

import { AuthService, UserService } from './services/index';

import { AuthGuard } from './guards/index';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    RouterModule,
  ],
  declarations: [
    MainComponent,
    LoginComponent,
  ],
  providers: [
    AuthService,
    UserService,
    AuthGuard,
  ],
  bootstrap: [MainComponent]
})

export class AppModule {}
