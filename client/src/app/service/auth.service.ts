import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {interval, Observable} from "rxjs";
import {API} from "../utils/config";
import {SessionService} from "./session.service";
import {TokenResponse} from "../utils/TokenResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) {
  }

  authenticate(
    request: any
  ): Observable<any> {
    return this.http.post(`${API}/auth`, request)
  }

  refreshToken() {
    return this.http.post<any>(`${API}/auth/refresh-token`, {})
      .subscribe((response: TokenResponse) => {
          this.sessionService.updateTokens(response)
        }
      )
  }

  startRefreshing() {
    const intervalTime = 10 * 60 * 1000
    this.refreshToken()
    interval(intervalTime)
      .subscribe(() => {
          this.refreshToken()
        }
      )
  }
}
