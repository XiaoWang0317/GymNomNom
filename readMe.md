# ReadMe About GymNomNom

## Code structure
### main: Java backend with SpringBoot
### test: Testing classes by SpringBoot Test
### www: Frontend code with HTML, CSS and JavaScript
### nginx.conf: Nginx configuration file
### pom.xml: SpringBoot configuration file


## Nginx deployment

The frontend and backend is deployed separately. To make the project running, the backend runs on localhost:8080, which is the SpringBoot default running port. The frontend is deployed on nginx server which is running on localhost:91, which is set by developers (we the team 6).  Please check out the nginx.conf for the server setting. 

Nginx download: https://nginx.org/en/download.html

There is URL re-writing implemented in the nginx.conf to make sure the URL request sent by frontend will be directed from localhost:91 to localhost:8080. Only URLs starts with /api will be rewritten. 

## Inspected Bugs
1. The backend exception handling on the frontend is not working perfectly
2. For the food recommendation, if users input extreme values for as their nutrition intake, the result maybe less effective
3. Users cannot change their username, password or fitness type.

