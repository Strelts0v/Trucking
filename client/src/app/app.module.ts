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
  MatCheckboxModule,
  MatToolbarModule,
  MatSidenavModule,
  MatListModule,
  MatSnackBarModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatProgressBarModule,
  MAT_DATE_LOCALE,
  MatRadioModule
} from '@angular/material';

import { JwtModule } from '@auth0/angular-jwt';
import { ChartModule, GMapModule } from 'primeng/primeng';

import { AppComponent } from './app.component';
import { MainComponent, SearchBarComponent, SearchService } from './main';
import {
  AuthGuard,
  RoleGuard,
  AuthService,
  UserService,
  AuthComponent,
  UserDetailComponent,
  UserListComponent,
  UserFullNamePipe
} from './users';
import { AppRoutingModule } from './app-routing.module';
import { DocHolderComponent, DocHolderPlaceholderComponent } from './doc-holder/doc-holder.component';
import { InvoiceDetailComponent, InvoiceListComponent, InvoiceService, InvoiceSearchComponent, LossActDetailComponent } from './invoices';
import { WaybillDetailComponent, WaybillListComponent, WaybillService, WaybillSearchComponent } from './waybills';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { ClientListComponent, ClientDetailComponent, ClientService } from './clients';
import { WelcomeComponent } from './welcome/welcome.component';
import { WarehouseFullAddressPipe } from './warehouses/warehouse-full-address.pipe';
import { CarService } from './cars/car.service';
import { CarListComponent } from './cars/car-list/car-list.component';
import { BithdayCongratulationComponent } from './bithday-congratulation/bithday-congratulation.component';
import { ItemService } from './items/item.service';
import { ProgressDialogComponent } from './progress-dialog/progress-dialog.component';
import { WarehouseListComponent, WarehouseDetailComponent, WarehouseService, WarehouseFullAddressPipe } from './warehouses';
import { ProfitLossStatementComponent, ReportService } from './report';

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
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatProgressBarModule,
    MatRadioModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('token');
        },
        whitelistedDomains: ['localhost:8080']
      }
    }),
    AppRoutingModule,
    GMapModule,
    ChartModule
  ],
  declarations: [
    AppComponent,
    AuthComponent,
    MainComponent,
    UserListComponent,
    DocHolderComponent,
    DocHolderPlaceholderComponent,
    InvoiceDetailComponent,
    InvoiceListComponent,
    WaybillDetailComponent,
    LossActDetailComponent,
    ConfirmDialogComponent,
    WaybillListComponent,
    UserFullNamePipe,
    WelcomeComponent,
    WarehouseFullAddressPipe,
    CarListComponent,
    BithdayCongratulationComponent,
    UserFullNamePipe,
    ClientListComponent,
    UserDetailComponent,
    ClientDetailComponent,
    ProgressDialogComponent,
    SearchBarComponent,
    InvoiceSearchComponent,
    WaybillSearchComponent,
    WarehouseListComponent,
    WarehouseDetailComponent,
    ProfitLossStatementComponent,
    WarehouseSearchComponent
  ],
  entryComponents: [
    DocHolderComponent,
    ConfirmDialogComponent,
    BithdayCongratulationComponent,
    UserDetailComponent,
    ClientDetailComponent,
    ProgressDialogComponent,
    WarehouseDetailComponent
  ],
  providers: [
    AuthGuard,
    RoleGuard,
    AuthService,
    UserService,
    CarService,
    InvoiceService,
    WaybillService,
    ClientService,
    ItemService,
    SearchService,
    WarehouseService,
    ReportService,
    {provide: MAT_DATE_LOCALE, useValue: 'ru-RU'}
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
