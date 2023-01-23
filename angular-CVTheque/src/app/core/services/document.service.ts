import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../interfaces/document';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  createComment(comment: FormData): Observable<any> {
    return this.http.post<any>(environment.baseApi + "document/comment/create", comment)
  }

  getAllComment(id: number): Observable<any> {
    return this.http.get<any>(environment.baseApi + `document/comment/get?id=${id}`)
  }

  addDocument(document: FormData):Observable<any> {
      return this.http.post(environment.baseApi + "document/add", document, {
        reportProgress: true,
        observe: 'events',
        responseType: 'json',
      })
  }

  getAllDocuemntsByLearner(idUser: number):Observable<Document[]> {
    return this.http.get<Document[]>(environment.baseApi + "document/learner?idUser=" + idUser)
  }

  getAllDocuemnts():Observable<Document[]> {
    return this.http.get<Document[]>(environment.baseApi + "document/get")
  }

  deleteDocument(id: number): Observable<boolean> {
    return this.http.get<boolean>(environment.baseApi + "document/delete?id=" + id)
  }

  getOneDocument(id: number): Observable<Document> {
    return this.http.get<Document>(environment.baseApi + "document/one?id=" + id)
  }

  updateDocument(document: FormData): Observable<any> {
    return this.http.post(environment.baseApi + "document/update", document, {
      reportProgress: true,
      observe: 'events',
      responseType: 'json',
    })
  }

}
