# Distributed Tracing

## Context and Problem Statement
How can we trace requests within the log of a service or even over multiple services? 
This is essential when analysing errors in distributed systems which contain of multiple services communicating with each other. 

## Considered Options

* Spring Sleuth library
* other tracing libraries
* Self-implemented tracing and error ids

## Decision Outcome

Chosen option: "Spring Sleuth", because 

* it harmonises perfectly with Spring Boot
* it provides all desired behaviour out of the box without any additional configuration needed
