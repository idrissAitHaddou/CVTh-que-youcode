import { HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Alert } from 'src/app/core/interfaces/alert';
import { Comment, Document } from 'src/app/core/interfaces/document';
import { AuthService } from 'src/app/core/services/auth.service';
import { DocumentService } from 'src/app/core/services/document.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  idDocument: number = 0
  $documentUpdate: Document = new Document()
  _documents: Document[] = []
  document: Document = new Document()
  documentUpdate: any = null
  uploadAddDocument: any = null
  uploadUpdateDocument: any = null
  messageErrorModal: string = ""
  alertStyle: Alert = new Alert()
  messageAlert: string = ""
  color: string = "red"
  progress: number = 0
  progressWidth: string = ""
  comments: any
  comment: string  = ""
  idDoc: number = 0
  dtoogleCommentDoc: boolean = false
  currentRole: string =""
  showFileName: string =""
  constructor(private doucmentService: DocumentService, private authService: AuthService, private sanitizer: DomSanitizer) {   
   }

   transform(url: string): any  {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  ngOnInit(): void {
    this.getAllDocument()
    this.alertStyleCss()
  }

  sortComment(comments: any): any {
   return null;
  }

  getAllComment(idDoc: number): void {
      this.doucmentService.getAllComment(idDoc).subscribe((responce: any) => {
        this.comments = responce
        this.comments.sort(function(a: any,b: any){
          return new Date(b.created_at) > new Date(a.created_at);
        });
      })
  }

  sendComment(): void{
    const formData = new FormData();
    formData.append("comment", new Blob([JSON.stringify(this.comment)],{type: "application/json"}));
    formData.append("idUser", new Blob([JSON.stringify(this.authService.currentUser.id)],{type: "application/json"}));
    formData.append("idDoc", new Blob([JSON.stringify(this.idDoc)],{type: "application/json"}));
    this.doucmentService.createComment(formData).subscribe((responce: any) => {
      this.comment = ""
        this.comments.push(responce)
    })
  }

  getOneDocument(id: number): void {
    this.doucmentService.getOneDocument(id).subscribe((responce: any) => {
      this.$documentUpdate = responce
    })
  }

  deleteDocument(id: number): void {
      this.doucmentService.deleteDocument(id).subscribe((responce: boolean) => {
        if(responce == true) {
        this._documents =  this._documents.filter(doc => doc.id != id)
          this.color = "green"
          this.messageAlert = "Document deleted successfully"
        }else {
          this.color = "red"
          this.messageAlert = "Document not deleted !!!"
        }
        this.alertStyleCss()
        this.hideAlertAfterTime()
      })
  }

  getAllDocument():void {
    this.currentRole = this.authService.currentUser.role
    console.log("documents")
    console.log(this.currentRole)
    if(this.currentRole == "learner") {
      this.doucmentService.getAllDocuemntsByLearner(this.authService.currentUser.id).subscribe((response: any) => {
        this._documents = response
      })
      return
    }
    this.doucmentService.getAllDocuemnts().subscribe((response: any) => {
      this._documents = response
    })
    console.log(this._documents)
  }

  uploadDocument(event:any):void {
    this.uploadAddDocument = event.target.files[0]
}

//   uploadDocument(event:any):void {
//     const reader = new FileReader();
//     reader.readAsDataURL(event.target.files[0]);
//     reader.onload = (event) => {
//       if (reader.result) {
//         this.document.data_file = reader.result
//       }
//     }
// }

  addDocument():void {
    if(!this.validateForm(this.document, this.authService.currentUser.id)) {
        this.messageErrorModal = "You information invalid" 
        this.color = "red"
        setTimeout(() => {
          this.messageErrorModal = "";
        }, 5000);
        return
    }
    const formData = new FormData();
    formData.append('document', new Blob([JSON.stringify(this.document)],{type: "application/json"}));
    formData.append('file', this.uploadAddDocument);
    formData.append('idUser', new Blob([JSON.stringify(this.authService.currentUser.id)],{type: "application/json"}));
    this.doucmentService.addDocument(formData).subscribe((event: HttpEvent<any>) => {
     switch(event.type) {
       case HttpEventType.UploadProgress:
        this.progress = Math.round(event.loaded / (event.total || 0) * 100)
        this.progressWidth = this.progress + "%";
        break;
      case HttpEventType.Response: 
      const modal = document.getElementById("cancelModal")
                modal?.click()
        if(event.body) {
              this._documents.push(event.body)
              this.color = "green"
              this.alertStyleCss()
              this.messageAlert = "Document upload successfully"
          }else {
              console.log("error")
              this.messageAlert = "Document upload failed"
            }
            setTimeout(() => {
              this.progress = 0;
            }, 1500);
            this.hideAlertAfterTime()
            break;
        
     }

    })
  }

  updateUploadDocument(event:any):void {
    this.uploadUpdateDocument = event.target.files[0]
}

  alertStyleCss():void {
    this.alertStyle.svgstyle = `flex-shrink-0 w-5 h-5 text-${this.color}-700`
    this.alertStyle.message = `ml-3 text-sm font-medium text-${this.color}-700`
    this.alertStyle.button = `ml-auto -mx-1.5 -my-1.5 bg-${this.color}-100 text-${this.color}-500 rounded-lg focus:ring-2 focus:ring-${this.color}-400 p-1.5 hover:bg-${this.color}-200 inline-flex h-8 w-8 dark:bg-${this.color}-200`
    this.alertStyle.bg = `w-64 text-xs flex p-4 mb-4 bg-${this.color}-100 rounded-lg`
  }

  validateForm(document: Document, idUser: number): boolean {
      if(document.name == "" || document.category == "" || document.description == "" || this.uploadAddDocument == null || idUser == null)  return false
      else return true
  }

  validateUpdateForm(document: Document): boolean {
    if(document.name == "" || document.category == "" || document.description == "")  return false
    else return true
}

  hideAlertAfterTime(): void {
    setTimeout(() => {
      this.messageAlert = "";
    }, 5000);
  }

  updateDocument(id: number): void {
      if(!this.validateUpdateForm(this.$documentUpdate)) {
          this.messageErrorModal = "You information invalid" 
          this.color = "red"
          setTimeout(() => {
            this.messageErrorModal = "";
          }, 5000);
          return
      }

      const formData = new FormData();
      formData.append('document', new Blob([JSON.stringify(this.$documentUpdate)],{type: "application/json"}));
      formData.append('file', this.uploadUpdateDocument);
      formData.append('id', new Blob([JSON.stringify(id)],{type: "application/json"}));
      this.doucmentService.updateDocument(formData).subscribe((event: HttpEvent<any>) => {
        switch(event.type) {
          case HttpEventType.UploadProgress:
           this.progress = Math.round(event.loaded / (event.total || 0) * 100)
           this.progressWidth = this.progress + "%";
           break;
         case HttpEventType.Response: 
              const btnCancelUpdate = document.getElementById("btnCancelUpdate")
              btnCancelUpdate?.click()
              if(event.body) {
                this._documents =  this._documents.filter(doc => doc.id != id)
                this._documents.push(event.body)
                this.color = "green"
                this.messageAlert = "Document updated successfully"
              }else {
                this.color = "red"
                this.messageAlert = "Document not updated !!!"
              }
              this.alertStyleCss()
              this.hideAlertAfterTime()
            }
      })

  }

  showFileDetails(fileName: string) {
      this.showFileName = fileName;
      console.log(this.showFileName)
  }

}
