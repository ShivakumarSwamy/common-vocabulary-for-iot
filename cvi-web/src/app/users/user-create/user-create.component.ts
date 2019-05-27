import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";
import {UserCreateDto} from "../dto/user-create-dto";
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
  }, {updateOn: 'blur', validators: [this.validatePasswordAndConfirmPasswordMatch(),
      this.validateUsernamePasswordSame()]});

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

  validatePasswordAndConfirmPasswordMatch() {
    return (control: AbstractControl) => {

      const passwordControl = control.get('password');
      const confirmPasswordControl = control.get('confirmPassword');

      if (passwordControl && passwordControl.value && confirmPasswordControl && confirmPasswordControl.value) {
        if (passwordControl.value !== confirmPasswordControl.value)
          return {'mismatchPassword': 'Passwords do not match'}
      }

      return null;
    }
  }

  validateUsernamePasswordSame() {
    return (control: AbstractControl) => {

      const usernameControl = control.get('username');
      const passwordControl = control.get('password');

      if (passwordControl && passwordControl.value && usernameControl && usernameControl.value) {
        if (passwordControl.value === usernameControl.value)
          return {'usernamePasswordSame': 'Username and password same'}
      }

      return null;
    }
  }

  isPasswordFormControlDirty() {
    return this.passwordFormControl.dirty;
  }

  isConfirmPasswordFormControlDirty() {
    return this.confirmPasswordFormControl.dirty;
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
      role: this.roleFormControl.value
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
