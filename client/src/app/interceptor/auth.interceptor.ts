import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {BehaviorSubject, catchError, filter, finalize, Observable, switchMap, take, throwError} from 'rxjs';
import {SessionService} from "../service/session.service";
import {AuthService} from "../service/auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {


  constructor(
    private sessionService: SessionService
  ) {
  }

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    console.log(request)

    if (request.url.includes('refresh-token')) {
      let req = request.clone({
        setHeaders: {
          'refresh-token': this.sessionService.get('refresh_token')!
        }
      })
      return next.handle(req)
    } else if (!request.url.includes('verify') ||
      (request.url.includes('api/v1/user') && request.method === 'POST'
      )) {
      return next.handle(request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.sessionService.get('access_token')}`,
        },
      }))
    } else {
      return next.handle(request);
    }
  }


}
