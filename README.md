# LockerManagementAPI
REST API for Locker Management System in SpringBoot

### Steps to Run the Application
1. Creating MySQL Database
#### Manually
  * Open MySQL (Preferred Version : 5.7) on your terminal and run following commands.
  * 'mysql -u root -p' (Enter your root password when prompted)
  * 'CREATE DATABASE locker;'
  * 'CREATE USER locker;'
  * 'ALTER USER locker IDENTIFIED BY 'locker';'
  * 'GRANT ALL PRIVILEGES ON locker.* TO locker;'
  * 'FLUSH PRIVILEGES;'
  * After entering these commands, try to Login to Your 'Locker' Database Using 'Locker' user.
  * 'mysql -u locker -p' (Enter your root password when prompted, password is 'locker')
  * 'USE locker;'
#### Via Docker
  * docker run -d --name locker -e MYSQL_ROOT_PASSWORD=locker -e MYSQL_USER=locker -e MYSQL_PASSWORD=locker -e MYSQL_DATABASE=locker -p 3306:3306 mysql:5.7
2. Extract the porject from the zip file or clone it your working repository.
3. Open the project in any IDE that supports Java SpringBoot.
4. Resolve the Maven Dependencies by entering command "mvn clean install" on terminal. (Version : Apache Maven 3.6.3)
5. Then on the IDEs terminal, start the application by using the command : 'mvn spring-boot:run'.
6. Your application (API) would be available at localhost://8080 (Or any other port used by you).
7. Add the customers first for whom you want to book the lockers.
8. Also add a few lockers before making bookings.

### Assumptions
1. We can book locker for customers that exist in our database, if a customer doesn't exist, first we need to add the customer to the database.
2. Customer's Phone Number is used to identify him/her and is saved within the locker whenever a locker is booked for the customer.
3. Location is determined by the PinCode only for simplicity. 
4. If there's a new Locker on a given pincode, first we need to add it to the database.
5. If a Locker is available at the Customer's pincode then we could book it for them else we can't make a booking.
6. A Locker could be opened only if it's booked and a OTP is already generated for it.
7. Once a locker is opened, it's available for a new booking.

### APIs

#### Customer
1. Get Customer By Id (GET) : ("/customers/{id}") [Utility]
2. Add Customer (POST) : ("/customers") [Utility]

#### Locker
1. Add a Locker with Specific PinCode (POST) : ("/lockers") [Utility]
2. Get Locker Details (GET) : ("/lockers/{id}/status") [Utility]
3. Book a Locker On Customer's PinCode (POST) : ("/lockers/book") [API - 1]
4. Send OTP for Given Locker (PATCH) : ("/lockers/{id}/notify") [API - 2]
5. Open the Locker Using OTP (PATCH) : ("/lockers/{id}/open") [API - 3]  

