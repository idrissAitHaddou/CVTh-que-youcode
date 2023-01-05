import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, of, switchMap, } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CurrentUser } from '../interfaces/current-user';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _authenticated: boolean = false;
  private token: string = "";
  public currentUser: CurrentUser = new CurrentUser();;
  constructor(private _http:HttpClient, private router: Router) {
    this.currentUser = this.getUserFromLocalCache()
   }

   public getUserFromLocalCache(): CurrentUser {
     const user = localStorage.getItem("currentUser")
     return user != null ? JSON.parse(user) : null;
   }

    public addUserToLocalCache(user:CurrentUser): void {
      localStorage.setItem('currentUser', JSON.stringify(user))
    }

    public loadToken(): string {
      const currentToken = localStorage.getItem("accessToken")
        this.token = currentToken != null ? currentToken : ""
        return this.token
    }

      /**
     * Setter & getter for access token
     */
      set accessToken(token: string) {
        localStorage.setItem('accessToken', token);
    }

    get accessToken(): string {
        return localStorage.getItem('accessToken') ?? '';
    }

  signIn(credentials:User): Observable<any> {
    return  this._http.post<any>(environment.baseApi + "authentication/authenticate", credentials).pipe(
        catchError((error) => {
          return of(null)
        }),
        switchMap((response: any) => {
          if(response != null) {
            this.accessToken = response.accessToken
            this.token = this.accessToken
            this._authenticated = true;
            this.addUserToLocalCache(response.user)
          }
          return of(response)
        })
      )
  }

  checkAuth():Observable<boolean> {
    if(this.accessToken) {
      return  this._http.post(environment.baseApi + "authentication/check-token", this.accessToken).pipe(
          catchError(error => {
            return of(false);
          }),
          switchMap((response: any) => { 
                  return of(response)
          }))
    }else return of(false)
  }

  logout(): void {
    localStorage.clear()
    this.router.navigate(['/sign-in']);
  }

}
