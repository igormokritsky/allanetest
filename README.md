## Features

This API provides HTTP endpoint's and tools for the following:

* Create a customer: `POST/api/v1/customers`
* Create a leasing contract: `POST/api/v1/leasing-contract`
* Get a leasing overview: `GET/api/v1/leasing-contract`
* Create a vehicle: `POST/api/v1/vehicle`

### Details

`POST/api/v1/customers`

This end-point is called to create a new customer.

**Body Example:**

```json
{
   "id":1,
   "firstName":"First Name",
   "lastName":"Last Name",
   "email":"email@gmail.com",
   "links":[
      
   ],
   "birthdate":"1986-04-08T12:30"
}
```

I have added an `email` field so that at least one of the parameters of each client is unique (for searching, etc.)


To have a local DB need to create `leasing_app` local database

I've created tests for each layer just for one entity as an example

### Documentation

* Swagger (dev environment): [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
