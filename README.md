cinema
==============
Cinema is a project that automates the ticketing process, scheduling, report generation.


Objectives
========
1. Three roles with different rights: admin, cashier and user.
2. View / add / edit / delete movies and seances.
3. Different types of sorts and filters for movies and seances.
4. Required fields:
- for film: title, description (up to 1000 characters), the IMDB rating, cost,
poster, hire date;
- for session: hall, movie date, costs of zones.
5. Two halls with three zones in each. In the first hall 90 seats, in the second - 120.
6. Seances time: 10:00-22:00.
7. To receive reports: ratings for halls and zones, quantity of sold tickets.
8. Booking tickets and printing / sending them by email.
9. Automatic removal of booking, in the case of non-payment for 60 minutes before
the start of the seance.


For launching
-------------------------
Use Tomcat for run.

For VM options set `-Duser.language=en -Duser.country=en`.

Set properties in `src\main\resources\spring\application.properties`.

Initialize database with `src\main\resources\db\initDB.sql` and `src\main\resources\db\rating_table.sql`.

It is necessary to install a Lombok plugin!


Technologies
-------------------------
- Java 8
- Oracle Object-Oriented model
- Spring Framework 4.3.3
- Spring JDBC
- Spring Security
- Vaadin
- Maven
- Lombok
- Tomcat