# Credit Simulator Console Application

A simple Java console application that simulates vehicle loan calculations based on user input.

## Features

- Input validation for vehicle type, condition, year, loan amount, down payment, and loan tenure.
- Displays monthly installments over multiple years based on vehicle type and condition.
- Calculates interest based on vehicle type (8% for cars and 9% for motorcycles) with yearly interest increase.
- Docker support for ease of containerized execution.
- CI/CD setup with GitHub Actions for automatic build and deployment to Docker Hub.

## Requirements

- **Java 17** or higher (Temurin).
- **Maven 3.x** for building the application.
- **Docker**
- **GitHub Actions** or **GitLab CI** for CI/CD

## Framework used
- **Java Spring Boot 3.4.4**

## Running the Application

### 1. Run Locally
Make sure to clone this repository to your local computer

To run the application locally, there are multiple options:

**1. With Maven or Java**
- **Make sure to have maven installed, refer to https://maven.apache.org/install.html for further information**
- **navigate to the root directory of the project folder and open terminal/bash**
- **Build the application by running**
  ```bash
  mvn clean package
- **After building the jar, execute:**
  ```bash
  java -jar target/credit-simulator.jar
  ```
  **or alternatively, you can run:**
  ```bash
  mvn spring-boot:run
  ```
  this will execute the application without needing to build the jar first

**2, On MacOS or Linux environment**
- **Execute this command:**
```bash
chmod +x run.sh
```
This makes the script inside the bin to be executable on the system, so you can test it locally with:
```bash
./bin/credit_simulator
```
**Note**
if the application does not run due to being unable to find the jar file, run the command:
```bash
mvn clean package``
```
**Ensure you have maven installed on your system**



### 2. By docker ###
You can run the application via docker without needing to pull the code
**Make sure you have docker installed**
- **Open a terminal, and run the command:**
  ```bash
  docker pull jemmiepriadi/credit-simulator
  ```
  This will pull the image that will be needed to run the docker container
- **Run the app by executing:**
  ```bash
  docker run -it --name credit-simulator-container jemmiepriadi/credit-simulator
  ```
  **Note**
  You can change 'credit-simulator-container' to any name you want
  This should be running on port 8080 by default, you can override it via -p command

**After running it, you or the user will be prompted to input into the terminal**  
You can refer to the image below
![image](https://github.com/user-attachments/assets/c0447874-5d64-4d33-bdbe-e84f9b7c1de5)
**After inputting the data needed, the system will automatically calculate and show the results below:**
![image](https://github.com/user-attachments/assets/27390866-7946-4dcd-b3c9-ae8cfd228514)


## Note
**On this current app's version, it cannot do load existing menu by hitting the given URL that is caused by the API/URL returning 404 error**
![image](https://github.com/user-attachments/assets/c49bba1f-90d5-4adf-8c2c-41378d5475e1)


**As also currently there is no txt file attached, the app will only be able to be executed by console/terminal/shell**
