import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './components/authentication/sign-in/sign-in.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthGuard } from './core/gaurds/auth.guard';

const routes: Routes = [
  {
    path:'',  
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path:'sign-in',  
    component: SignInComponent
  },
  {
    path:'account',  
    component: ProfileComponent,
    canActivate: [AuthGuard] 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
