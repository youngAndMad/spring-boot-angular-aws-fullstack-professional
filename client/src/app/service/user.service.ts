import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {API} from "../utils/config";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) {
  }

  register(
    request: any
  ): Observable<any> {
    return this.http.post(`${API}/user`, request)
  }

  getCredentials(
    token: string,
    data: string
  ): Observable<any> {
    return this.http.get(`${API}/user/verify`,
      {
        params: {
          'verification_token': token,
          'data': data
        }
      }
    )
  }

  findAll(): Observable<any> {
    return this.http.get(`${API}/user`)
  }

  findById(
    id: number
  ): Observable<any> {
    return this.http.get(`${API}/user/find`,
      {
        params: {
          'id': id
        }
      }
    )
  }

  deleteById(
    id:number
  ):Observable<any>{
     return this.http.delete(`${API}/user/${id}`)
  }
}
