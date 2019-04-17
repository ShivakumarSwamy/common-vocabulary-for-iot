import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {

  constructor(private authService: AuthService,
              private router: Router) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const url = state.url;
    return this.checkLogin(url);
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.canActivate(childRoute, state);
  }

  private checkLogin(url: string) {
    console.log('URL to check: ' + url);
    console.log('Auth Service logged in: ' + this.authService.isAuthenticated);
    if (this.authService.isAuthenticated) {
      return true;
    }

    console.log('Redirect URL: ' + url);
    this.authService.redirectUrl = url;
    this.router.navigate([ '/login' ]);

    return false;
  }
}
