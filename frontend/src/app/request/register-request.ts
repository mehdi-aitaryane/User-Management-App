import { first, last } from "rxjs";

export class RegisterRequest {
    firstname : string;
    lastname : string;
    username : string;
    password : string;
    confirmPassword : string;
    
    constructor(firstname : string, lastname : string, username : string, password : string, confirmPassword : string)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

}
