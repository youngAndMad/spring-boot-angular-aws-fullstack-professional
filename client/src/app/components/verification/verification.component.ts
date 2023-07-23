import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../service/user.service";
import {SessionService} from "../../service/session.service";

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css']
})
export class VerificationComponent implements OnInit {

  token: string | null = null;
  data: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: UserService,
    private sessionService: SessionService
  ) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.token = params['token'] || null;
      this.data = params['data'] || null;
      if (this.token && this.data) {
        this.authService.getCredentials(this.token, this.data).subscribe(response => {
          this.sessionService.updateTokens(response.tokens)
          localStorage.setItem('user', JSON.stringify(response.user))
        })
        this.router.navigate(['/profile'])
      }
    });
  }

}
