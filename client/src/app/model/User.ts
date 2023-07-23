import {Gender} from "../utils/Gender";
import {AmazonFile} from "./AmazonFile";

export class User {
  id?:number
  name:string
  surname:string
  age:number
  email:number
  gender:Gender
  files: Array<AmazonFile>
}
