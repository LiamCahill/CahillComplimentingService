# CCS: A Complimenting System

### Why?
**Happiness is scarce. The world has become uncomfortably polarized.**\
This complimentary service allows for uses to submit compliments to each other. The idea is that a user can login, create a compliment for another user, and then submit it. When the recipient signs-on to the service, they are given the option to check their inbox.

### Application Requirements:
- app should be accessible,
    - ex.: a .jar that people can download and run, or a webapp - something accessible via link online and mobile-friendly
    - the MYSQL database is configured on AWS, highly-accessible
- app is distributed in a way the allows for future updates; i.e. service-able

### Users can:
- send a compliment
- retrieve a compliment (possibly one that they haven't already read. [requires updating the schema with a 'read' column])
  - either at random, or from a select user if possible
- see a count of unread compliments
- write a compliment, and send anonymously [optional]
- login/out

### Compliments:
- need to be hashed before being storing the database; security and privacy are priorities
