import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/core/interfaces/user';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  user: User = new User();
  errorMessage: string = "";
  loading: boolean = false;
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if(this.authService.accessToken) this.router.navigate(["/"])
  }

  login() {
    localStorage.clear()
    this.loading = true;
    this.authService.signIn(this.user).subscribe(
      (responce: any) => {
        this.loading = false;
        if(!responce) this.errorMessage = "Login failed"
        else this.router.navigate(['/']);
    })
  }

}
