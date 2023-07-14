# Maurevair-template
This project was done as part of an technical assignment completion for an aviation company. Kindly find the details below:

MaurevAir is a new airline company starting business in a few months. The company has approached you to develop their online reservation system.

Documents:
•	Scheduled flights in XML containing flights available (flights.xml). 
Extract of flight.xml
Carrier: The organization
FlightNumber: Flight Number is not unique Origin: Origin airport
Destination: Destination airport DepartureTime: Departure time with timezone ArrivalTime: Arrival time with timezone
BookingInfo: Each flight can have one or more cabin classes (First, Premium Economy, Economy) SeatAvailable: Number of seats available.

Phase 1: REST API
Implement a REST API that allows the following:
1.	Upload and validate both documents. Insert the information from both documents in a database of your choice.
2.	List all the airports and their details.
3.	List a specific airport along with their details.
4.	List all available flights between an origin and a destination, as well as their details (seats available).
5.	Based on step 4, a user shall be able to make the necessary reservation.
6.	Generate OPENAPI specification.


Phase 2: Authentication
1.	Build an Authentication API to allow a user to log into the system and generate JWT tokens.
2.	Secure the REST API from unauthorized users.
