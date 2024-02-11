import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthStatusService } from '../security/auth-status.service';

export const adminGuard: CanActivateFn = async (route, state) => {
  const authStatusService = inject(AuthStatusService);
  return authStatusService.isAdminAuthenticated()
};

