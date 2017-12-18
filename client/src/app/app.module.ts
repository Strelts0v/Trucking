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
} from '@angular/material';

import { JwtModule } from '@auth0/angular-jwt';
import { GMapModule } from 'primeng/primeng';

import { AppComponent } from './app.component';
import { MainComponent } from './main';
import {
  AuthGuard,
  RoleGuard,
  AuthService,
  UserService,
  AuthComponent,
  UserDetailComponent,
  UserListComponent
} from './users';
import { AppRoutingModule } from './app-routing.module';
import { DocHolderComponent, DocHolderPlaceholderComponent } from './doc-holder/doc-holder.component';
import { InvoiceDetailComponent } from './invoices/invoice-detail/invoice-detail.component';
import { InvoiceListComponent } from './invoices/invoice-list/invoice-list.component';
import { InvoiceService } from './invoices/invoice.service';
import { WaybillDetailComponent } from './waybills/waybill-detail/waybill-detail.component';
import { LossActDetailComponent } from './invoices/lossact-detail/lossact-detail.component';
import { WaybillListComponent } from './waybills/waybill-list/waybill-list.component';
import { WaybillService } from './waybills/waybill.service';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { UserFullNamePipe } from './users/user-full-name.pipe';
import {
  ClientListComponent,
  ClientDetailComponent,
  ClientService
} from './clients';
import { WelcomeComponent } from './welcome/welcome.component';
import { WarehouseFullAddressPipe } from './warehouses/warehouse-full-address.pipe';
import { CarService } from './cars/car.service';
import { CarListComponent } from './cars/car-list/car-list.component';

import { ItemService } from './items/item.service';
import { ProgressDialogComponent } from './waybills/waybill-detail/progress-dialog/progress-dialog.component';
import { WarehouseListComponent, WarehouseDetailComponent, WarehouseService } from './warehouses/index';
import { SearchBarComponent } from './main/search-bar/search-bar.component';
import { SearchService } from './main/search-bar/search.service';
import { InvoiceSearchComponent } from './invoices/invoice-search/invoice-search.component';
import { WaybillSearchComponent } from './waybills/waybill-search/waybill-search.component';
import { WarehouseListComponent } from './warehouses/warehouse-list/warehouse-list.component';
import { WarehouseService} from './warehouses/warehouse.service';
import { BirthdayCongratulationComponent } from './birthday-congratulation/birthday-congratulation.component';
import {LetterService} from './birthday-congratulation/letter.service';


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
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('token');
        },
        whitelistedDomains: ['localhost:8080']
      }
    }),
    AppRoutingModule,
    GMapModule
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
    BirthdayCongratulationComponent,
    UserFullNamePipe,
    ClientListComponent,
    UserDetailComponent,
    ClientDetailComponent,
    ProgressDialogComponent,
    SearchBarComponent,
    InvoiceSearchComponent,
    WaybillSearchComponent,
    WarehouseListComponent,
    WarehouseListComponent,
    WarehouseDetailComponent,
  ],
  entryComponents: [
    DocHolderComponent,
    ConfirmDialogComponent,
    BirthdayCongratulationComponent,
    UserDetailComponent,
    ClientDetailComponent,
    ProgressDialogComponent,
    WarehouseDetailComponent,
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
    LetterService,
    ItemService,
    SearchService,
    WarehouseService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
