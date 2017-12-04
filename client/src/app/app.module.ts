import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
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
import { CarsComponent } from './cars/cars.component';
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
    MatTabsModule,
    MatIconModule,
    MatTableModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatPaginatorModule,
    MatDialogModule,
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
    CarsComponent,
    MainComponent,
    DocumentComponent,
    InvoiceDetailComponent,
    InvoicesComponent,
    WaybillComponent,
    LossActComponent,
    NewButtonComponent
  ],
  entryComponents: [
    DocumentComponent
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
