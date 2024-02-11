# User Management App

This is a User Management App project implemented with Spring Boot, Angular, and MySQL database. The application provides functionality for managing users with two types of roles: Admin and User.

## Features
* User Management: Admin users have the ability to manage their own profile and manage other users.
* Profile Management: Users can manage only their own profiles.
* JWT Authentication: The project utilizes JWT (JSON Web Tokens) for authentication, including blacklisting tokens, access tokens, and refresh tokens.
## Technologies Used
* Spring Boot: Backend framework for building the RESTful API.
* Angular: Frontend framework for building the user interface.
* MySQL: Database management system for storing user data.
* JWT: Authentication mechanism for securing API endpoints.
## Installation
### Prerequisites
* Java Development Kit (JDK) installed
* Node.js and npm installed
* Angular CLI installed
* MySQL Server installed
### Backend Setup
1. Clone the repository:

```console
git clone https://github.com/mehdi-aitaryane/User-Management-App.git
```
2. Navigate to the backend directory:

```console
cd User-Management-App/backend
```
3. Configure MySQL database settings in src/main/resources/application.properties.

4. Run the Spring Boot application:

```console
./mvnw spring-boot:run
```
### Frontend Setup

1. Navigate to the frontend directory:

```console
cd User-Management-App/frontend
```
2. Install dependencies:

```console
npm install
```

3. Start the Angular development server:

```console
ng serve
```

4. Access the application at http://localhost:4200 in your web browser.

### Data Import

1. Create umdb folder in your mysql upload folder

2. Copy the images in User-Management-App/db folder to your mysql upload folder

3. Edit the umdb.sql script

4. Execute the mysql scipt

## Usage

* Upon accessing the application, users can log in using their credentials.
* Admin users will have access to user management features.
* Regular users can manage their own profiles.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## License
This project is licensed under the MIT License.