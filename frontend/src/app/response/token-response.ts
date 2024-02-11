export class TokenResponse {
    
    access_token : string;
    refresh_token : string;
    message : string;
    
    constructor(access_token : string, refresh_token : string, message : string)
    {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.message = message;
    }
}
