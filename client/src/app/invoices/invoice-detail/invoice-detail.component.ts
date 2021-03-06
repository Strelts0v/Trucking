import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { animate, style, transition, trigger } from '@angular/animations';

import { Item } from '../../items/item';
import { Consignment, ConsignmentStatus } from '../consignment';
import { Invoice, InvoiceStatus } from '../invoice';
import { InvoiceService } from '../invoice.service';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';
import { RoleGuard, User } from '../../users';
import { Client, ClientService } from '../../clients';
import { ItemService } from '../../items/item.service';

const BUTTON_SAVE = 'Save';
const BUTTON_CHECK = 'Check';
const BUTTON_COMPLETE = 'Complete';

@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html',
  styleUrls: ['./invoice-detail.component.sass'],
  animations: [
    trigger('shrinkIn', [
      transition('void => *', [
        style({
          height: '0',
          opacity: 0
        }),
        animate('0.35s ease-out')
      ]),
      transition('* => void', [
        animate('0.25s ease-in', style({
          height: '0',
          opacity: 0
        }))
      ])
    ])
  ]
})
export class InvoiceDetailComponent implements OnInit {

  iForm: FormGroup;

  @Input() invoice: Invoice;
  user: User;

  edit: boolean;
  editAvailability: boolean;

  items: Item[];
  clients: Client[];

  saveBtnName: string;

  consignmentStatus = ConsignmentStatus;

  constructor(private fb: FormBuilder,
              private invoiceService: InvoiceService,
              private iconRegistry: MatIconRegistry,
              private sanitizer: DomSanitizer,
              private parentDialogRef: MatDialogRef<DocHolderComponent>,
              private clientService: ClientService,
              private itemService: ItemService,
              public roleGuard: RoleGuard,
              private dialog: MatDialog) {

    iconRegistry.addSvgIcon(
      'package_variant',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icon/package-variant.svg'));
  }

  toggleEdit(): void {
    if (!this.edit) {
      this.edit = true;
      this.toggleForm();
    }
  }

  toggleForm(): void {
    this.consignments.controls.forEach(control => {
      if (this.edit && (this.roleGuard.isOwner() || this.roleGuard.isDispatcher() || this.roleGuard.isManager())) {
        control.enable();
      }
    });

    if (this.edit && (this.roleGuard.isOwner() || this.roleGuard.isDispatcher())) {
      this.client.enable();
    }
  }

  submit(): void {
    if (!this.invoice.status) {
      this.invoice.client = this.iForm.value.client;
      this.invoice.consignments = this.iForm.value.consignments;
      this.invoiceService.registerInvoice(this.invoice)
        .subscribe(invoice => this.parentDialogRef.close(invoice));
    } else if (this.invoice.status === InvoiceStatus.ISSUED) {
      this.invoice.consignments = this.iForm.value.consignments;
      this.invoiceService.checkInvoice(this.invoice)
        .subscribe(invoice => this.parentDialogRef.close(invoice));
    } else if (this.invoice.status === InvoiceStatus.CHECKED) {
      const isValid = this.invoice.waybill.waybillCheckpoints.every(waybillCheckpoint => {
        return waybillCheckpoint.checked === true;
      });

      if (isValid) {
        this.invoiceService.completeInvoice(this.invoice)
          .subscribe(invoice => this.parentDialogRef.close(invoice));
      } else {
        const dialogRef = this.dialog.open(ConfirmDialogComponent, {
          data: {
            text: 'You must check all checkpoints before completing the transportation',
            trueAction: 'Ok',
            isAlert: true
          }
        });
      }
    }
  }

  cancel(): void {
    console.log('Invoice canceled');
  }

  compareClient(client1: Client, client2: Client): boolean {
    return client1 && client2 && client1.id === client2.id;
  }

  compareItem(item1: Item, item2: Item): boolean {
    return item1 && item2 && item1.id === item2.id;
  }

  shouldShowAddBtn(index: number): boolean {
    return index === (this.consignments.controls.length - 1)
      && this.consignments.controls[index].get('item').value
      && this.edit
      && !this.invoice.status;
  }

  shouldShowRemoveBtn(): boolean {
    return this.consignments.controls.length > 1
      && this.edit
      && !this.invoice.status;
  }

  // TODO: Change call to get all clients.
  getClients(): void {
    this.clientService.getClients(1, 100)
      .subscribe(clients => this.clients = clients);
  }

  getItems(): void {
    this.itemService.getItems()
      .subscribe(items => this.items = items);
  }

  getStatus(index: number): string {
    const consignments = this.invoice.consignments;
    if (consignments.length > index) {
      return consignments[index].status;
    } else {
      return '';
    }
  }

  setUpSaveBtn(): void {
    if (!this.invoice.status) {
      this.saveBtnName = BUTTON_SAVE;
    } else if (this.invoice.status === InvoiceStatus.ISSUED) {
      this.saveBtnName = BUTTON_CHECK;
    } else if (this.invoice.status === InvoiceStatus.CHECKED) {
      this.saveBtnName = BUTTON_COMPLETE;
    } else {
      this.saveBtnName = BUTTON_SAVE;
    }
  }

  get client(): FormControl {
    return this.iForm.controls.client as FormControl;
  }

  get consignments(): FormArray {
    return this.iForm.controls.consignments as FormArray;
  }

  addItem(consignment?: Consignment): void {
    this.consignments.push(
      this.fb.group({
        item: [{value: consignment && consignment.item, disabled: !this.edit}, Validators.required],
        amount: [{value: consignment && consignment.amount, disabled: !this.edit}, [Validators.required, Validators.min(1)]],
        status: [{value: consignment && consignment.status, disabled: !this.edit}]
      })
    );
  }

  deleteItem(index: number) {
    this.consignments.removeAt(index);
  }

  initForm(): void {
    this.iForm = this.fb.group({
      client: [{value: '', disabled: true}, Validators.required],
      consignments: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.initForm();

    this.getClients();
    this.getItems();

    if (this.invoice.id) {
      this.invoice.consignments.forEach(consignment => this.addItem(consignment));
      this.client.setValue(this.invoice.client);
    } else {
      this.addItem();
      this.toggleEdit();
    }

    this.setUpSaveBtn();
    this.editAvailability = this.invoice.status !== InvoiceStatus.DELIVERED && (this.roleGuard.isOwner()
      || (this.roleGuard.isManager() && this.invoice.status === InvoiceStatus.ISSUED)
      || (this.roleGuard.isDispatcher() && !this.invoice.status)
      || this.roleGuard.isDriver());
  }

}
