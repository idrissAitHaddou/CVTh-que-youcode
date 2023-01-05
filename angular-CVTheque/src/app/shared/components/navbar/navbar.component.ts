import { Component, OnInit } from '@angular/core';
import { CurrentUser } from 'src/app/core/interfaces/current-user';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  currentUser: CurrentUser 
  constructor(private authService: AuthService) { 
    this.currentUser = this.authService.getUserFromLocalCache()
  }
  
  ngOnInit(): void {
  }

  signOut(): void {
      this.authService.logout()
  }

}
