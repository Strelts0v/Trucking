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
  MatDialogModule,
  MatTooltipModule,
  MatMenuModule,
  MatCheckboxModule
} from '@angular/material';

import { JwtModule } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';
import { AuthComponent } from './users/auth';
import { MainComponent } from './main';
import { UserListComponent } from './users/user-list';

import { AuthGuard, AuthService, UserService } from './users';
import { AppRoutingModule } from './app-routing.module';
import { DocHolderComponent } from './doc-holder/doc-holder.component';
import { InvoiceDetailComponent } from './invoices/invoice-detail/invoice-detail.component';
import { InvoiceListComponent } from './invoices/invoice-list/invoice-list.component';
import { InvoiceService } from './invoices/invoice.service';
import { WaybillDetailComponent } from './waybills/waybill-detail/waybill-detail.component';
import { LossActDetailComponent } from './invoices/lossact-detail/lossact-detail.component';
import { WaybillListComponent } from './waybills/waybill-list/waybill-list.component';
import { WaybillService } from './waybills/waybill.service';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { UserFullNamePipe } from './users/user-full-name.pipe';
import {CarsService} from './cars/cars.service';
import {CarsComponent} from './cars/cars.component';

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
    MatTooltipModule,
    MatMenuModule,
    MatCheckboxModule,
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
    UserListComponent,
    DocHolderComponent,
    InvoiceDetailComponent,
    InvoiceListComponent,
    WaybillDetailComponent,
    LossActDetailComponent,
    ConfirmDialogComponent,
    WaybillListComponent,
    UserFullNamePipe
  ],
  entryComponents: [
    DocHolderComponent,
    ConfirmDialogComponent
  ],
  providers: [
    AuthGuard,
    AuthService,
    UserService,
    InvoiceService,
    WaybillService,
    InvoiceService,
    CarsService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
