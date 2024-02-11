
export class ProfileResponse {
    firstname: string;
    lastname: string;
    username: string;
    type: string;

    constructor(firstname: string, lastname: string, username: string, type: string)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.type = type;
    }
}
