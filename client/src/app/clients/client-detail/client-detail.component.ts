import {Component, Inject} from '@angular/core';
import {MatDatepickerModule, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {Client} from './../client';
import {ClientService} from './../client.service';

@Component({
  selector: 'app-client-detail',
  templateUrl: 'client-detail.component.html',
  styleUrls: ['./client-detail.component.sass'],
})

export class ClientDetailComponent {

  client: Client;

  constructor(
    public dialogRef: MatDialogRef<ClientDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private clientService: ClientService) {
      this.client = <Client> data.client;
    }

  cancelClientDetail(): void {
    this.dialogRef.close();
  }

  deleteClient() {
    this.log(`Deleting client ${JSON.stringify(this.client)}`);
    this.clientService.deleteClient(this.client);
  }

  private log(message: string) {
    console.log('ClientDetailComponent: ' + message);
  }
}
