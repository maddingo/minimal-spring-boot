# Minimal Spring Boot Application 
This little project demonstrates how to attach the openapi specification to your Maven artifact.

The problem is that the openapi.yaml is deployed as minimal-spring-1.0.0-SNAPSHOT-openapi.json

To test this:
1. clone the project
1. Run mvn install
1. There is a ~/.m2/repository/no/maddin/minimal-spring/1.0.0-SNAPSHOT/minimal-spring-1.0.0-SNAPSHOT-openapi.json

The correct file name should be ~/.m2/repository/no/maddin/minimal-spring/1.0.0-SNAPSHOT/minimal-spring-1.0.0-SNAPSHOT-openapi.yaml