import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {UserCreateDto} from "../../dto/user-create-dto";
import {UserService} from "../../service/user.service";
import {ResponseMessage} from "../../response/response-message";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-create',
  templateUrl: 'user-create.component.html'
})
export class UserCreateComponent implements OnInit {

  usernameFormControl = this.fb.control('', Validators.required);
  passwordFormControl = this.fb.control('', Validators.required);
  confirmPasswordFormControl = this.fb.control('', Validators.required);
  roleFormControl = this.fb.control('', Validators.required);

  userCreateForm = this.fb.group({
    username: this.usernameFormControl,
    password: this.passwordFormControl,
    confirmPassword: this.confirmPasswordFormControl,
    role: this.roleFormControl
  }, {updateOn: 'blur'});

  username = '';
  password = '';
  confirmPassword = '';
  role = '';

  successSubmit = false;
  errorResponseMessage: ResponseMessage = undefined;

  roleTypes = ["Consumer", "Manager"];


  constructor(private fb: FormBuilder,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    this.subscribeUsernameFormControl();
    this.subscribePasswordFormControl();
    this.subscribeConfirmPasswordFormControl();
    this.subscribeRoleFormControl();
    this.initialiseRoleValue();
  }

  initialiseRoleValue() {
    this.roleFormControl.setValue("Consumer")
  }


  isPasswordFormControlTouchedAndDirty() {
    return this.passwordFormControl.touched && this.passwordFormControl.dirty;
  }

  isConfirmPasswordFormControlTouchedAndDirty() {
    return this.confirmPasswordFormControl.touched && this.confirmPasswordFormControl.dirty;
  }

  subscribeUsernameFormControl() {
    this.usernameFormControl.valueChanges.subscribe(
      (value) => this.username = value
    );
  }

  subscribePasswordFormControl() {
    this.passwordFormControl.valueChanges.subscribe(
      (value) => this.password = value
    );
  }

  subscribeConfirmPasswordFormControl() {
    this.confirmPasswordFormControl.valueChanges.subscribe(
      (value) => this.confirmPassword = value
    );
  }

  subscribeRoleFormControl() {
    this.roleFormControl.valueChanges.subscribe(
      (value) => this.role = value
    );
  }

  getUserCreateDto(): UserCreateDto {
    return {
      username: this.username,
      password: this.password,
      role: this.role
    }
  }

  OnSubmit() {
    console.log(this.getUserCreateDto());

    this.clearErrorResponseMessageFromApi();

    this.userService.create(this.getUserCreateDto())
      .subscribe(
        () => {
          this.successSubmit = true;
          this.navigateToLoginOnSuccess();
        },

        (error) => this.errorResponseMessage = error
      );
  }

  navigateToLoginOnSuccess() {
    this.router.navigate(['/login'])
  }

  clearErrorResponseMessageFromApi() {
    this.errorResponseMessage = undefined;
  }
}
