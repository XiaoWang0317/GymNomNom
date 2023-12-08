# README About GymNomNom

## Code structure
### main: Java backend with SpringBoot
### test: Testing classes by SpringBoot Test
### www: Frontend code with HTML, CSS and JavaScript
### nginx.conf: Nginx configuration file
### pom.xml: SpringBoot configuration file


## Nginx deployment

The frontend and backend is deployed separately. To make the project running, the backend runs on localhost:8080, which is the SpringBoot default running port. The frontend is deployed on nginx server which is running on localhost:91, which is set by developers (we are Team 6).  Please check out the nginx.conf for the server setting. 

Nginx download: https://nginx.org/en/download.html

There is URL re-writing implemented in the nginx.conf to make sure the URL request sent by frontend will be directed from localhost:91 to localhost:8080. Only URLs starts with /api will be rewritten. 

## Inspected Bugs
1. The backend exception handling on the frontend is not working perfectly
2. For the food recommendation, if users input extreme values for as their nutrition intake, the result maybe less effective
3. Users cannot change their username, password or fitness type.

## Website User Manual

This manual provides detailed instructions on how to use our website effectively. 

### Table of Contents

1. [Home](#home)
2. [Login](#login)
3. [BMI](#bmi)
4. [Nutrition](#nutrition)
5. [About](#about)

#### Home

The Home page is the general landing page of our website. Here, you will find an overview of the services we offer, including suggestions of healthy habits, wellness strategies, etc.

#### Login

- **Create an Account**
  - If you don't have an account, click on the 'Sign Up' option.
  - You will need to enter your username, password, fitness goal (losing weight/building muscle), age, and sex.
- **Existing Users**
  - Enter your username and password to log in.
- **Forgot Password**
  - Click on the 'Forgot Password' option and follow the instructions to reset your password.

#### BMI

- **Body Management**
  - Navigate to the Body Management page.
  - Enter your height and weight to calculate your Body Mass Index (BMI).
- **BMI Tracker**
  - The BMI Record page tracks your BMI over time, allowing you to monitor your progress.

#### Nutrition

- **Record Intake & Recommendation**
  - Click on the 'Record Intake & Recommendation' option.
  - Enter your daily intake of fat, vitamin C, vitamin A, protein, carbohydrates, and calories.
  - Submit this information to keep track of your nutritional intake.
- **Diet History**
  - Select the 'Diet History' option to view your previous nutritional intake records.
  - This feature helps you adjust your diet over time based on past intake.

#### About

- This page details the membership benefits.
- **Contact Form**
  - For any inquiries, fill out the contact form with your name, email address, and message.

For further assistance, please refer to the FAQs or contact our support team.

