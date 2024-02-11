import { HttpHeaders, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { TokenStorageService } from './token-storage.service';
import { filter, interval, of, switchMap, take } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const tokenStorageService = inject(TokenStorageService)

  if (req.url.startsWith("http://localhost:8080/api/admin") || req.url.startsWith("http://localhost:8080/api/user") || req.url.startsWith("http://localhost:8080/api/auth/logout")) 
  {
    return interval(0).pipe(
      switchMap(() => {
        const authentication = tokenStorageService.retrieveAccessToken();
        return authentication ? of(authentication) : of(null);
      }),
      filter(authentication => authentication !== null),
      take(1),
      switchMap(authentication => {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${authentication}`);
        const authReq = req.clone({ headers });
        return next(authReq);
      })
    )
  }

  if (req.url.startsWith("http://localhost:8080/api/auth/relogin")) 
  {
    const authentication = tokenStorageService.retrieveRefreshToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${authentication}`);
    const authReq = req.clone({ headers });
    return next(authReq);
  }


  return next(req);
};
