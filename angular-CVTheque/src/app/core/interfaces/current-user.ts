import {Promotion} from './promotion'
export class CurrentUser {
    id:number = 0;
    email:string = "";
    firstName:string = "";
    lastName:string = "";
    role:string = "";
    image:string = "";
    promotions: Promotion[] = new Array<Promotion>();
}
