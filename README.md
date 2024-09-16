
# Airline Ticket and Payment System ✈️

This project is an airline ticketing system backend application developed using Spring Boot and Hibernate. It provides RESTful APIs to manage passengers' flight reservations. The system includes basic functions such as adding an airline company, adding an airport, adding a flight route, listing a company's flights, creating and canceling a ticket, as well as the payment method for the ticket.




## Project Content

• Flight adding, updating, listing and cancellation operations

• Reservation creation, updating and cancellation operations

• Database operations with H2 Database

• API responses in JSON format
## Technologies

• Java 17

• Spring Boot

• Hibernate ORM

• H2 Database

• Maven

• SLF4J (Logging)

• Junit5 , Mockito (Tests)




  
## Database Configuration

**URL:** `jdbc:h2:mem:testdb`

**Username:** sa

**Password:** You can add 🤗

To access the H2 Console:

    1) Open your browser
    2) Go to `http://localhost:8080/h2` url

**JDBC URL:** `jdbc:h2:mem:testdb`

## API DOCUMENTATION

[You can access the entire postman api documentation of the project here :](https://documenter.getpostman.com/view/29652406/2sAXqp8PPV)

### Airline ✈️
#### Create Airline

```http
  POST /api/airlines
```

```json
Request JSON

{
    "name": "Emirates Airlines",
    "code": "EK"
}
```
```json
Response JSON

{
    "id": 3,
    "name": "Emirates Airlines",
    "code": "EK"
}
```

#### Search Airlines

```http
  GET /api/airlines/search?keyword=P
```
```json
Response JSON

[
    {
        "id": 2,
        "name": "Pegasus Airlines",
        "code": "PC"
    }
]
```
### Airports 🛬
#### Create Airports

```http
  POST /api/airports
```

```json
Request JSON
{
    "name":"Malatya Airport",
    "iatacode":"MLX"
}
```
```json
Response JSON

{
    "id": 6,
    "name": "Malatya Airport",
    "iatacode": "MLX"
}
```
#### Search Airports

```http
  GET /api/airports/search?keyword=M
```
```json
Response JSON

[
    {
        "id": 5,
        "name": "İzmir Adnan Menderes Airport",
        "iatacode": "ADB"
    },
    {
        "id": 6,
        "name": "Malatya Airport",
        "iatacode": "MLX"
    }
]
```


### Route 🚦
#### Create Route

```http
  POST /api/routes
```

```json
Request JSON

{
    "name":"Malatya Airport",
    "iatacode":"MLX"
}
```
```json
Response JSON

{
    "id": 6,
    "name": "Malatya Airport",
    "iatacode": "MLX"
}
```
#### Search Route

```http
  GET /api/routes/search?id=7
```
```json
Response JSON

{
    "id": 7,
    "departureAirport": {
        "id": 5,
        "name": "İzmir Adnan Menderes Airport",
        "iatacode": "ADB"
    },
    "arrivalAirport": {
        "id": 6,
        "name": "Malatya Airport",
        "iatacode": "MLX"
    }
}
```

### Ticket 🎫
#### Create Ticket

```http
  POST /api/tickets
```

```json
Request JSON

{
    "flightId" : 11,
    "passengerName": "Elif Cakan",
    "seatNumber": "2A",
    "price": 150.0
}
```
```json
Response JSON

{
    "flightId": 11,
    "passengerName": "Elif Cakan",
    "seatNumber": "2A",
    "price": 150.0,
    "ticketNumber": "PRHUVG"
}
```
#### Search Ticket

```http
  GET /api/tickets/ticket/PRHUVG
```
```json
Response JSON

{
    "flightId": 11,
    "passengerName": "Elif Cakan",
    "seatNumber": "2A",
    "price": 150.0,
    "ticketNumber": "PRHUVG"
}
```

### Payment 💳
#### Pay The Ticket

```http
  POST /api/payments
```

```json
Request JSON

{
    "ticketNumber": "PRHUVG",
    "cardNumber": "123*456789*012",
    "cardHolderName": "Pinar Yanik",
    "expirationDate": "12/25",
    "CVV": "123"
}
```

```json
Response JSON

{
    "id": 7,
    "ticketId": 9,
    "ticketNumber": "PRHUVG",
    "passengerName": "Elif Cakan",
    "price": 150.0,
    "status": "Sold"
}
```

## TESTS

To run tests written in JUnit 5, you can use the following command:

```bash
  mvn test
```

  
