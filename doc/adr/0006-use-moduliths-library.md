# Code Structure

## Context and Problem Statement
How can we grant a modular structure (e. g. prevent cyclic dependencies) to keep our project maintainable?

## Considered Options

* Moduliths library
* Self-defined module rules with ArchUnit tests

## Decision Outcome

Chosen option: "Moduliths", because 

* it provides predefined module definitions 
* it provides predefined verifier for unit tests
