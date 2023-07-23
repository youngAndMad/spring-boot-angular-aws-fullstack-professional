import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  loginForm: FormGroup
  registrationForm: FormGroup

  constructor(
    private userService: UserService,
    private fb: FormBuilder
  ) {
    this.loginForm = fb.group({
      email: [''],
      password: ['']
    })

    this.registrationForm = fb.group({
      email: ['', [Validators.email, Validators.required]],
      password: ['', [Validators.minLength(8)]],
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      age: ['', [Validators.required]],
      gender: ['', [Validators.required]]
    })
  }

  find(
    formName: string,
    attribute: string
  ) {
    return formName === 'login' ? this.loginForm.get(attribute) : this.registrationForm.get(attribute)
  }

  login() {
    console.log(this.loginForm)
  }

  register() {
    this.userService.register(this.registrationForm.value).subscribe(response => {
      if (response === true) {
      }
    })
    document.getElementById('popup')!.style.display = 'block';
  }

  ngOnInit(): void {
    document.getElementById('closePopup')!.addEventListener('click',  () => {
      document.getElementById('popup')!.style.display = 'none';
    });
  }


}
