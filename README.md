#Freetasklist

Web application for task management.

Deployed at [http://freetasklist.com](http://freetasklist.com).

Technology stack: bootstrap, wicket, spring, hibernate, mysql.

**Building & installation**

First you need to install MySQL Server 5.x. I'm using version 5.1.71 in production. When you have this available initialize the schema with the script `services/src/main/resources/createtables.sql`.

Build & run all tests:
`mvn clean install`

This will produce `web/target/freetasklist.war` which you can then deploy on your application server such as Tomcat.
