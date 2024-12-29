# Super User Manager

This project is a microservices-based user management system built with Spring Boot 1.5.17 and Java 8, featuring authentication, user management, and email notifications.


## Features

This service has battery includes to send emails with your google account

you just have to create a app password here: https://security.google.com/settings/security/apppasswords
(**just make sure you have the 2fa enabled**)

and just copy and paste the password in the .env file


email-service/.env (if you are running the project locally)
<br>
.env (if you are running the project with docker)

```env
MAIL_USERNAME=
MAIL_PASSWORD=
````

## Something know

You can run the project locally and with docker compose

but the project understand the .env variables in two different ways

### running the project locally
make sure you are setting up your .env variables in their own services
<br>
e.g:
<br>
email-service/.env
<br>
user-service/.env
<br>
api-gateway/.env

### running the project with docker
make sure you are setting your .env variables in the .env file at the root of the project

## Services Architecture 

**all the ports mentioned overhere are the default ones**

- **API Gateway** (Port: 8080) - Entry point for all requests
- **User Service** (Port: 8081) - Handles user management and authentication
- **Email Service** (Port: 8082) - Manages email notifications
- **Eureka Server** (Port: 8761) - Service discovery
- **MySQL Database** (Port: 3306) - Data persistence

## Prerequisites
- Java 8 (JDK 1.8)
- Maven 3.6+
- MySQL 5.7+
- Docker and Docker Compose (for containerized deployment)

## Project Structure
```
super-user-manager/
├── api-gateway/
├── user-service/
├── email-service/
├── eureka-server/
├── pom.xml
├── docker-compose.dev.yml
└── .env.example
```

## Configuration

### Environment Variables
Copy `.env.example` to `.env` and set your values:
```bash
cp .env.example .env
```

Required environment variables:
```env
# Server Ports
USER_SERVICE_PORT=8081
EMAIL_SERVICE_PORT=8082
API_GATEWAY_PORT=${API_GATEWAY_PORT:-8080}
EUREKA_PORT=8761

# MySQL
MYSQL_ROOT_PASSWORD=
MYSQL_DATABASE=user_service_dev
MYSQL_PASSWORD=
MYSQL_USER=
MYSQL_PORT=3306

# JWT
JWT_SECRET=
JWT_EXPIRATION=86400000

# Email Service
MAIL_USERNAME=
MAIL_PASSWORD=

# User Seeder
SEED_ADMIN_EMAIL=
SEED_ADMIN_PASSWORD=
SEED_REGULAR_USER_EMAIL=
SEED_REGULAR_USER_PASSWORD=
```

## Running Locally

### 1. Start Services Manually
Start each service in order:

1. Start MySQL:
```bash
# Make sure MySQL is running on port 3306
```

2. Start Eureka Server:
```bash
cd eureka-server
mvn spring-boot:run
```

3. Start User Service:
```bash
cd user-service
mvn spring-boot:run
```

4. Start Email Service:
```bash
cd email-service
mvn spring-boot:run
```

5. Start API Gateway:
```bash
cd api-gateway
mvn spring-boot:run
```

### 2. Using Docker Compose
Start all services with a single command:
```bash
docker-compose -f docker-compose.dev.yml up --build
```

Stop all services:
```bash
docker-compose -f docker-compose.dev.yml down
```

Remove volumes (if needed):
```bash
docker-compose -f docker-compose.dev.yml down -v
```
Remove all (if needed):

```bash
docker-compose -f docker-compose.dev.yml down --rmi all --volumes --remove-orphans
```

## API Endpoints


## Default Users
The system seeds the following users on startup:
- Super Admin: admin@genericcompany.com / admin123
- Regular User: user@genericcompany.com / user123

## Development

### Adding New Features
1. Create a new branch
2. Make changes
3. Run tests
4. Submit PR

### Running Tests
```bash
# Run all tests
mvn test

# Run specific service tests
cd user-service
mvn test
```

## Troubleshooting

### Common Issues
1. **Port Already in Use**
   ```bash
   netstat -ano | findstr <PORT>
   taskkill /PID <PID> /F
   ```

2. **MySQL Connection Issues**
    - Verify MySQL is running
    - Check credentials in .env
    - Ensure database exists

3. **Service Discovery Issues**
    - Ensure Eureka Server is running
    - Check service registration at http://localhost:8761

## Security Considerations
- JWT tokens expire after 24 hours
- Passwords are encrypted using BCrypt
- Role-based access control implemented
- Environment variables used for sensitive data

## Production Deployment
For production:
1. Use proper secrets management
2. Configure SSL/TLS
3. Use production-grade database
4. Set up monitoring and logging
5. Configure backup strategy

## Monitoring
- Service health: http://localhost:8761 (Eureka Dashboard)
- API Documentation: http://localhost:{API_GATEWAY_PORT:-8080}/swagger-ui.html

## Contributing
1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Create Pull Request

## Version Information
- Spring Boot: 1.5.17.RELEASE
- Java: 1.8
- MySQL: 5.7
- Spring Cloud: Edgware.SR5

## License
[Your License Here]

## FAQS

### How to complete clean the project?
```shell
mvn clean
rm -r ~/.m2/repository/mysql/mysql-connector-java
mvn dependency:purge-local-repository
mvn clean install
```
