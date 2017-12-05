import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
<<<<<<< HEAD
import { MatInputModule, MatButtonModule, MatCardModule, MatProgressSpinnerModule } from '@angular/material';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { JwtModule } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';
import { AuthComponent } from './users/auth/index';
import { UserListComponent } from './users/user-list/index';
import { MainComponent } from './main/index';
import { AuthGuard, AuthService, UserService } from './users/index';
import { AppRoutingModule } from './app-routing.module';
=======
import {
  MatInputModule,
  MatButtonModule,
  MatCardModule,
  MatProgressSpinnerModule,
  MatTabsModule,
  MatIconModule,
  MatTableModule,
  MatSelectModule,
  MatAutocompleteModule,
  MatPaginatorModule,
  MatDialogModule
} from '@angular/material';

import { JwtModule } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';
import { AuthComponent } from './users/auth';
import { MainComponent } from './main';

import { AuthGuard, AuthService, UserService } from './users';
import { AppRoutingModule } from './app-routing.module';
import { DocumentComponent } from './document/document.component';
import { InvoiceDetailComponent } from './document/invoice-detail/invoice-detail.component';
import { InvoicesComponent } from './document/invoices/invoices.component';
import { WaybillComponent } from './document/waybill/waybill.component';
import { LossActComponent } from './document/lossact/lossact.component';
import { InvoiceService } from './document/invoice.service';
import { NewButtonComponent } from './new-button/new-button.component';
>>>>>>> 2779a581ca7999a816d6c9605d20e4eab9a989f1

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
<<<<<<< HEAD
    MatTableModule,
    MatPaginatorModule,
    AppRoutingModule,
=======
    MatTabsModule,
    MatIconModule,
    MatTableModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatPaginatorModule,
    MatDialogModule,
>>>>>>> 2779a581ca7999a816d6c9605d20e4eab9a989f1
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('authenticationToken');
        }
      }
    }),
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    AuthComponent,
    MainComponent,
<<<<<<< HEAD
    UserListComponent,
=======
    DocumentComponent,
    InvoiceDetailComponent,
    InvoicesComponent,
    WaybillComponent,
    LossActComponent,
    NewButtonComponent
  ],
  entryComponents: [
    DocumentComponent
>>>>>>> 2779a581ca7999a816d6c9605d20e4eab9a989f1
  ],
  providers: [
    AuthGuard,
    AuthService,
    UserService,
    InvoiceService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
