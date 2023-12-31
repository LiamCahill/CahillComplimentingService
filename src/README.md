**People are less happy now than they have ever been. The world has become uncomfortably polarized.**\
This complimentary service allows for uses to submit compliments to each other. The idea is that a user can login, create a compliment for another user, and then submit it. When the recipient signs-on to the service, they are given the option to check their inbox.

Project Requirements:
1. Application: A user must have the ability to sign-on, or create a new user.
2. Application: Once logged-in, a user can:
   3. Create a new compliment to send
   4. Check their inbox
      5. open a message, either:
         6. a specific one
         7. one chosen by the program, at random
   5. Logout
6. Database: The database needs to be hosted in a docker container, and the data must (obviously) be saved.
   7. In a further interation, it is expected that a Raspberry Pi will host/run the docker container, and this program will connnect
8. Developer Behaviour: The program must be structured intuitively, and as close to a profession program as possible.
   9. Best practices for naming, file structure, and all Git behavior must be implemented.


___
*Recreation of project:*
1. From docker-compose.yml, confirm the parameters you require are present. 
2. Execute the compose file, and not the creation of the container in the pane below (for IntelliJ development.)
3. Confirm the container started successfully.
4. Navigate to the Database tab on the right and connect to the mySQL database with the same parameters.
5. Run any necessary DDL to build the DB schema, as needed. 



