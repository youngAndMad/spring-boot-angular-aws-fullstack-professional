import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SessionService} from "../service/session.service";

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

    if (!request.url.includes('verify') ||
      (request.url.includes('api/v1/user') && request.method === 'POST'
      )) {

      const modified = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.sessionService.get('access_token')}`
        }
      })
      return next.handle(modified)

    }

    return next.handle(request);
  }
}
