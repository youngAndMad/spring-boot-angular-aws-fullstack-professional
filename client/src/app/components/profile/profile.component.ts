import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../model/User";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user:User

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user')!)
  }



}
