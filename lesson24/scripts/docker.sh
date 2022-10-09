docker network create my-bridge-network

#######################################
#Run tomcat with built project inside
#######################################

docker run --rm --name tomcat --network my-bridge-network -p 8080:8080  -v "C:\Users\natas\OneDrive\Рабочий стол\JavaEE\javaEE\lesson24\target\lesson24-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/servlet.war" tomcat:9.0.53-jdk11

docker run --rm --name demo-postgres --network my-bridge-network -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 15432:5432 -v "C:\Users\natas\OneDrive\Рабочий стол\JavaEE\javaEE\lesson24\dev-env\postgres\init.sql:/docker-entrypoint-initdb.d/1-init.sql" postgres:12.12-alpine
