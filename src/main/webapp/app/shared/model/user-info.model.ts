export interface IUserInfo {
    id?: number;
    userid?: number;
    phone?: string;
    isregister?: string;
    status?: string;
    logintime?: string;
    username?: string;
    firstname?: string;
    lastname?: string;
    isimage?: string;
    remark?: string;
    portraitContentType?: string;
    portrait?: any;
}

export class UserInfo implements IUserInfo {
    constructor(
        public id?: number,
        public userid?: number,
        public phone?: string,
        public isregister?: string,
        public status?: string,
        public logintime?: string,
        public username?: string,
        public firstname?: string,
        public lastname?: string,
        public isimage?: string,
        public remark?: string,
        public portraitContentType?: string,
        public portrait?: any
    ) {}
}
