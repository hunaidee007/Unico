# Unico
Problem Statement : 
Expose REST and SOAP Services using J2EE technologies. Calculate the GCD of input parameters and provide the some of all calculated GCD

Solution:
The application committed used below mentioned technologies to expose REST and SOAP services. After the GCD is calculated the result is moved to another queue. The sum of all the calculated GCD is done from this queue.

Technologies  : 
Java 1.8
JAX-RS
JAX-WS
JBOSS WildFly
JMS


Steps to build and deploy:

Build the application using Maven
Copy the war in WidlFly Application Server
Create 2 message queues one to push input values and the second to keep the calculated GCD's. These two message queues need to be create in JBOSS with Admin login. 

    1. Name - jms/queue/gcdRestQueue
       Destination - "java:jboss/exported/jms/queue/gcdRestQueue
    2. Name - jms/queue/gcdSoapQueue
       Destination - java:jboss/exported/jms/queue/gcdSoapQueue                   


1. REST Service to push data  - 

URL  - http://<host>:<port>/unicotest-web/context/gcdrestservice/push

Message Body - i1=100&i2=120


2. REST Service to List all the pushed data

URL - http://<host>:<port>/unicotest-web/context/gcdrestservice/list

Message Body - Blank



3. SOAP service for GCD operation

Client stub can be created using WSDL exposed at http://<host>:<port>/unicotest-web/GCDService?wsdl
Following are the soap requests operations:

1. gcd
Fetches first two elements from message queue gcdRestQueue and find the GCd of it and pushes the result in gcdsoapqueue

2. gcdlist
Fetches all computed gcd elements from gcdsoapqueue

3. gcdsub
Calculates the sum of all elements from gcdsoapqueue

The application used layered architecture with best coding practices. The application is a EAR so can be deployed on any application server.

