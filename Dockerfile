# ~~~~~~~~~~~~~~ build stage
FROM maven:3.8.3-jdk-8-slim as build
# copy the whole project
COPY . /
# build the war with maven activating the war profile
RUN mvn clean package -Doutput=war
# change the name of the war file to embedded.war
RUN mv ./target/$(ls ./target | grep '.war') ./embedded.war

# ~~~~~~~~~~~~~~ final stage
FROM tomcat:8.5

WORKDIR /usr/local/tomcat
COPY --from=build ./embedded.war ./webapps

# curl http://localhost:8080/embedded/foo