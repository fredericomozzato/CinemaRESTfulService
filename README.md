# Cinema Room REST Service

**Author:**  Frederico Mozzato

This is a study project done for the Java Backend Developer course by JetBrains Academy in Hyperskill. The objective was to understand the basics of web development with Spring Boot 3 and write my first REST API.
 

## Functionalities
The program is an API that simulates a service to buy tickets to a movie theater. The client side can make HTTP requests to four endpoints:
  
###
1. **/seats** - accepts GET requests and returns in JSON the list of available seats with the row, seat number (column) and the price of the ticket. When a ticket is purchased to a specific seat it no longer appears in this list. If the ticket is returned the seat returns to the list also.
  
2. **/purchase** - accepts POST requsts with information passed through the request's body. It requires "row"and "column" parameters with values from 1 to 9. The room contains 9 rows of 9 seats. This method returns information from the bought ticket like the row and seat number, as well as the price and a unique UUID compliant token that can be used to return the ticket. It returns 401 status code error messages if the user sends a row and/or column outside the accepted range (9x9).
  
3. **/return** - accepts POST requests with the token information to return a bought ticket. It returns the information from the ticket returned. The request's body requires a "token" parameter with the UUID string associated with the token bought. It returns a 401 status message if the token is not linked to any purchased seat.
  
4. **/stats** - Reprents a simple statistics endpoint to be used by the "owners" or admins. It returns the number of available seats, the number of bought tickets and the total income. It gets updated by each ticket bought or returned. Accepts POST requests and requires a URL parameter for "password", which is "super_secret" by the way, but don't tell anyone ;)
  
The course didn't get to any security implementations, so this is a very simple API. The ticket information is saved to an H2 database just to have a simple persistence layer and give some experience on how to work with repositories, mappers and DTOs.
  
  I didn't implement any design patterns but this is a next step in my learning journey. If you have any feedback on the code, please don't hesitate to share with me, I'm looking forward to learn from more experienced developers!

  ### Thank you for reading!

