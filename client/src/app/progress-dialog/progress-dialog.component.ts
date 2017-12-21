import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-progress-dialog',
  templateUrl: './progress-dialog.component.html',
  styleUrls: ['./progress-dialog.component.sass']
})
export class ProgressDialogComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
  }

}
