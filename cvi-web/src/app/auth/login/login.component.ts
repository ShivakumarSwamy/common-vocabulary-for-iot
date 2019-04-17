import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {ResponseMessage} from "../../response/response-message";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {UserLoginDto} from "../dto/user-login-dto";
import {throwError} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  usernameFormControl = this.fmb.control('', Validators.required);
  passwordFormControl = this.fmb.control('', Validators.required);

  loginForm = this.fmb.group({
    username: this.usernameFormControl,
    password: this.passwordFormControl
  }, {updateOn: 'blur'});

  successSubmit = false;
  username = '';
  password = '';

  errorResponseMessage: ResponseMessage;

  constructor(private fmb: FormBuilder,
              private authService: AuthService,
              private router: Router) {
  }


  ngOnInit() {
    this.subscribeUsernameFormControl();
    this.subscribePasswordFormControl();
  }

  subscribeUsernameFormControl() {
    this.usernameFormControl.valueChanges
      .subscribe(
        value => this.username = value
      );
  }

  subscribePasswordFormControl() {
    this.passwordFormControl.valueChanges
      .subscribe(
        value => this.password = value
      );
  }

  getUserLoginDto(): UserLoginDto {
    return {
      username: this.username,
      password: this.password
    }
  }

  OnLogin() {


    this.authService.login(this.getUserLoginDto())
      .subscribe(
        (response) => {
          this.authService.storeToken(response.headers);
          if (this.authService.isAuthenticated) {
            this.successSubmit = true;
            this.router.navigate([this.getRedirectUrl()]);
          } else {
            // should never go this condition
            throwError({message: 'Username or password error'});
          }
        },
        (error: ResponseMessage) => this.errorResponseMessage = error
      );
  }

  navigateToCreateAccount() {
    this.router.navigate(['/users/create']);
  }

  getRedirectUrl() {
    const redirectUrl = this.authService.redirectUrl;

    if (redirectUrl === '/') {
      if (this.authService.isConsumer) return '/consumer';

      if (this.authService.isManager) return '/manager';

      if (this.authService.isAdmin) return '/admin';
    }
    if (redirectUrl.startsWith('/consumer')) return redirectUrl;

    if (redirectUrl.startsWith('/manager')) return redirectUrl;

    if (redirectUrl.startsWith('/admin')) return redirectUrl;

    return '/error' // should invoke page not found component
  }

}
