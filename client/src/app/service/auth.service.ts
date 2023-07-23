import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {API} from "../utils/config";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  authenticate(
    request: any
  ): Observable<any> {
    return this.http.post(`${API}/auth`, request)
  }

  refreshToken(): Observable<any> {
    return this.http.post(`${API}/auth`, {})
  }
}
