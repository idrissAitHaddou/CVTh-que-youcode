export class Document {
    id: number = 0
    name: string = ''
    description: string = ''
    type: string = ''
    category: string = ''
    data_file: any = null
    createdAt?: string = ""
}

export class Comment {
    id: number = 0
    comment: string = ""
}
