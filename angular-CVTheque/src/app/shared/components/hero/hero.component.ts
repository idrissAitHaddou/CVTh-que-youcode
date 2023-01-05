import { Component, OnInit } from '@angular/core';
import { CurrentUser } from 'src/app/core/interfaces/current-user';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit {
  currentUser: CurrentUser
  constructor(private authService: AuthService) { 
    this.currentUser = this.authService.getUserFromLocalCache()
  }

  ngOnInit(): void {

  }

}
