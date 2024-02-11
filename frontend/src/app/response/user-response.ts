export class UserResponse {
    firstname: string;
    lastname: string;
    username: string;

    constructor(firstname: string, lastname: string, username: string)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }
}
