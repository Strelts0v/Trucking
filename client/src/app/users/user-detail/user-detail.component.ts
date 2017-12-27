import {Component, Inject} from '@angular/core';
import {MatDatepickerModule, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {User} from './../user';
import {UserService} from './../user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: 'user-detail.component.html',
  styleUrls: ['./user-detail.component.sass'],
})

export class UserDetailComponent {

  user: User;
  roles = new FormControl();

  deletedUserId: number;

  roleList = ['SYSADMIN', 'ADMIN', 'MANAGER', 'DISPATCHER', 'DRIVER', 'OWNER'];

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new CustomErrorStateMatcher();

  constructor(
    public dialogRef: MatDialogRef<UserDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService) {
      this.user = <User> data.user;
    }

  cancelUserDetail(): void {
    this.dialogRef.close();
  }

  deleteUser() {
    this.log(`Deleting user ${JSON.stringify(this.user)}`);
    this.deletedUserId = this.user.id;
    //this.userService.deleteUser(this.user);
    this.dialogRef.close();
      // .subscribe(_ => {
      //   this.dialogRef.close();
      // });
  }

  private log(message: string) {
    console.log('UserDetailComponent: ' + message);
  }
}

/** Error when invalid control is dirty, touched, or submitted. */
export class CustomErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
