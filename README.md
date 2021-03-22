# Test for Java Developer
As a software engineer, I would like to implement microservices based backend APIs to register/edit/read/(soft) delete multiple users via ​ bulk operations
> Key requirements
  1. The applications should be written in spring-boot, Java or other JVM languages
  2. The applications will be packaged via docker and can run in a multi-container way via
      docker-compose.yml. Please provide details of how to run your application in a README file
  3. Upon successful registration, send out a welcome email (you could use fake smtp server)
  4. Proper validation, error handling and testing.
  5. Two ​ separate microservices​ user-service and notification-service
  6. Communication between services can either be synchronous or asynchronous
Additional Requirements
  1. REST API documentation (Compulsory for senior position application)
  2. Partial handling (Optional)

#####################################################################

To run the app go to the main directory:
$ spring-cloud-main/    -- and execute the command
$ ./local_env rebuild

All microservices should start up in a few minutes.

Eureka server should start on http://localhost:8761/ - here you can check if all services are running correctly.

To create a new user you should make a request to this enpoint:
http://172.20.0.3:8085/user

with this Request body:

{
         "email": "your email address",
         "password": "fake_password",
         "firstName": "fake_first_name",
         "lastName": "fake_last_name"
  }

Afrer succesfull registration, Notification-service should send welcome message for "your email address".

