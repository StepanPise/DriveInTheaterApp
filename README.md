# Drive-In Theater Application

A web application for browsing and managing movie screenings. Users can explore a catalog of films and view showtimes by specific dates or all at once. After logging in, they can select a parking spot and make a reservation. All reservations are accessible from the user's profile with the option to cancel them.

![tnpwScreen (1)](https://github.com/user-attachments/assets/63e2669f-b2c4-472b-884b-320fac0b45d8)
![tnpwScreen2 (2)](https://github.com/user-attachments/assets/f67e9aea-5d1b-4be5-95ec-3693f6e2c867)

## Getting Started

### Database Initialization:
* First, you need to use the provided configuration file docker-compose.yml. You have to run DockerManager.src.DockerManager class and choose the desired database setup by modifying the main() method.
For example, to reset and start the database, call ResetAndBoot().

* Or you can run this command in the terminal (in the folder with the docker-compose.yml file): **docker compose up -d** and modify the main() method in DockerManager class.

This will create a container with a Postgres database on port **5433** and a volume named docktnpw_pgdata where the database data is stored.

### Starting the Application:
* First, install all required dependencies from pom.xml by running the command: **mvn install**

* Or, preferably, use the graphical interface of IntelliJ IDEA to do this automatically.

Then simply run the src.main.java.DriveinTheaterApplication class, and the application will be available at http://localhost:8080/.

### Login:

You can log in as either an admin or a user:
* Admin
  * Username: admin
  * Password: 1
* User
  * Username: user1
  * Password: 2


## Technologies Used

* Backend: Java (Spring Boot)

* Database: PostgreSQL

* Containerization: Docker

* Frontend: HTML, CSS
