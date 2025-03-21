
# Event and Ticket Management Microservices

## Overview

This repository contains two microservices designed to manage events and tickets:

1. **ms-event-manager**: Handles event-related operations such as creation, retrieval, updating, and deletion of events.
2. **ms-ticket-manager**: Manages ticket-related operations such as creation, retrieval, updating, and cancellation of tickets.

Both services use RESTful APIs and are documented using Swagger. Docker is used to containerize the applications for easy deployment.

---

## Features

### **ms-event-manager**

- **Create Event**: Create a new event with details such as name, date, and location.
- **Get All Events**: Retrieve a list of all registered events.
- **Get Event by ID**: Fetch details of a specific event by its ID.
- **Get Events Sorted by Name**: Retrieve events sorted alphabetically by their names.
- **Update Event**: Update the details of an existing event by its ID.
- **Delete Event**: Mark an event as deleted if it has no active tickets associated with it.

### **ms-ticket-manager**

- **Create Ticket**: Create a new ticket and associate it with an event.
- **Get All Tickets**: Retrieve a list of all tickets.
- **Get Tickets by Event ID**: Fetch tickets associated with a specific event ID.
- **Get Tickets by CPF**: Retrieve tickets linked to a specific CPF.
- **Update Ticket**: Update details of a specific ticket.
- **Cancel Ticket**: Mark a ticket as inactive.
- **Cancel Tickets by CPF**: Mark all tickets associated with a CPF as inactive.
- **Check Tickets by Event**: Check if an event has active tickets.

---

## Technologies

- **Language**: Java
- **Framework**: Spring Boot
- **Database**: MongoDB
- **API Documentation**: Swagger
- **Messaging**: RabbitMQ (for ticket creation notifications)
- **Containerization**: Docker

---


## API Endpoints

### **ms-event-manager**

#### Event Endpoints:

- `POST /create-event`: Create a new event.
- `GET /get-all-events`: Get all events.
- `GET /get-event/{id}`: Get event by ID.
- `GET /get-all-events/sorted`: Get all events sorted by name.
- `PUT /update-event/{id}`: Update an event.
- `DELETE /delete-event/{id}`: Delete an event (if no active tickets).

### **ms-ticket-manager**

#### Ticket Endpoints:

- `POST /create-ticket`: Create a new ticket.
- `GET /get-all-tickets`: Get all tickets.
- `GET /get-tickets-by-event/{eventId}`: Get tickets by event ID.
- `GET /get-ticket-by-cpf/{cpf}`: Get tickets by CPF.
- `PUT /update-ticket/{id}`: Update a ticket.
- `DELETE /cancel-ticket/{id}`: Cancel a ticket.
- `DELETE /cancel-ticket-by-cpf/{cpf}`: Cancel tickets by CPF.
- `GET /check-tickets-by-event/{eventId}`: Check if an event has active tickets.

---

## Running Tests

To execute tests for both services:

```bash
mvn clean test
```


