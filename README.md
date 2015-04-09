# Unico

Technologies  : 

Java 1.8

JAX-RS
JAX-WS

JBOSS WildFly

JMS


Steps to build and deploy:

Build the application using Maven
Copy the war in WidlFly Application Server
Create 2 message queues 

                   Name - jms/queue/gcdRestQueue
                    Destination - "java:jboss/exported/jms/queue/gcdRestQueue
                    Name - jms/queue/gcdSoapQueue
                      Destination - java:jboss/exported/jms/queue/gcdSoapQueue                   


1. REST Service URL to push data  - 
URL  - http://localhost:8080/unicotest-web/context/gcdrestservice/push

Message Body - i1=100&i2=120



2. 

http://localhost:8080/unicotest-web/context/gcdrestservice/list

3. SOAP service for GCD operation

Client stub can be created using WSDL exposed at http://localhost:8080/unicotest-web/GCDService?wsdl




1. gcd
Fetches first two elements from message queue gcdRestQueue and find the GCd of it and pushes the result in gcdsoapqueue

2. gcdlist
Fetches all computed gcd elements from gcdsoapqueue

3. gcdsub
Calculates the sum of all elements from gcdsoapqueue
