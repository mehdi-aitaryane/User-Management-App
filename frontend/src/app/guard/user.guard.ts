import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { AuthStatusService } from '../security/auth-status.service';
import { of } from 'rxjs';

export const userGuard: CanActivateFn = (route, state) => {
  const authStatusService = inject(AuthStatusService);
  return of(authStatusService.isUserAuthenticated())
};
