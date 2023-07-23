import {Injectable} from '@angular/core';
import {TokenResponse} from "../utils/TokenResponse";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  get(
    value: any
  ) {
    return localStorage.getItem(value)
  }

  updateTokens(
    tokens: TokenResponse
  ) {
    localStorage.setItem('refresh_token' , tokens.refresh_token)
    localStorage.setItem('access_token' , tokens.access_token)
  }

  delete(
    value:any
  ){
    localStorage.removeItem(`${value}`)
  }

  logout(){
    localStorage.clear();
  }

}

