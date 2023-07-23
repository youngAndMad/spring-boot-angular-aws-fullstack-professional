import {Injectable} from '@angular/core';
import {TokenResponse} from "../utils/TokenResponse";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  get(
    value: any
  ) {
    return sessionStorage.getItem(value)
  }

  updateTokens(
    tokens: TokenResponse
  ) {
    sessionStorage.setItem('refresh_token' , tokens.refresh_token)
    sessionStorage.setItem('access_token' , tokens.access_token)
  }

  delete(
    value:any
  ){
    sessionStorage.removeItem(`${value}`)
  }

  logout(){
    sessionStorage.clear();
  }

}

