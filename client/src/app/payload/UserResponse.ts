import {TokenResponse} from "../utils/TokenResponse";
import {User} from "../model/User";

export class UserResponse {
  user: User
  tokens: TokenResponse
}
