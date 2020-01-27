# REST API specification format

## Context and Problem Statement
We very much like the idea of **Contract first** when it comes to interface design.
This leads to a more stable and well thought API and also to a more decoupled, interface centric implementation of client and server parts.
The developers (frontend and backend) can define a contract together and than go apart and implement or use the contract independently.

Another important topic is code generation from API specification. Generating request and response models from an API specification on client 
and server side prevents problem when integrating the API into the application, because the application is always "compiled" against the API.

There are different API specification formats on the market. Which one we want to use?

## Considered Options

* Swagger OpenApi
* RAML

## Decision Outcome

Chosen option: "Swagger OpenApi", because 

* Swagger OpenApi has a bigger community
* OpenApi provides a code generator that is much more maintained and developed than RAML.  
* OpenApi generates Controller interfaces, whereas RAML generates Controller classes that have to be overwritten. 
