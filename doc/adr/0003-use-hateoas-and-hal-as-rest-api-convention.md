# REST API convention

## Context and Problem Statement
There are many possibilities to define a REST API
Which one we want to use?

## Considered Options

* HATEOAS with HAL
* other HATEOAS convention (like SIREN etc.)
* custom definition

## Decision Outcome

Chosen option: "HATEOAS with HAL", because 

* Spring Boot provides a HTEOAS/HAL integration module
* HAL is simple to use and easy to understand while having a powerfull structure.
* HAL is the de-facto standard

### HATEOAS
With HATEOAS (Hypermedia as the Engine of Application State) we choose a standard REST API convention 
that ensures a hypermedia centric client communication. 
Clients can interact with our application entirely through hypermedia provided dynamically by the app server.

The core principal of HATEOAS is that every resource includes further or consecutive links. 

E.g. every entry in a search result provides a link to its details.
And further, every detail resource provides links to further functions or facets of this entity.
See [https://en.wikipedia.org/wiki/HATEOAS#Details](https://en.wikipedia.org/wiki/HATEOAS#Details) for an example.

With HATEOAS we can encapsulate the link building in the resource creation at the server.
The client does not need to know anything about the correct links to our REST API.

HATEOAS makes our API a real REST-API like it's described in the [Richardson Maturity Model](http://martinfowler.com/articles/richardsonMaturityModel.html).
