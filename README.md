# legacy-java-embed-tomcat
An approach how to embed tomcat into a legacy Java frameworkless application 

## How to test it

- Clone the project

- Run it from the `Main.java` class through **IntelliJ IDE** or build the artifact with maven command `mvn clean package` from the terminal. Then run:
```bash
java -jar ./target/TomcatEmbeddedTest-1.0-SNAPSHOT.jar
```
- Open the browser and search the following:
```
# servlets defined into the web.xml file
http://localghost:8080/foo
http://localghost:8080/bar

# servlet defined with annotation
http://localghost:8080/annotated

# check the filter correctly works
http://localghost:8080/bar?block

# check the public jsp is visible
http://localhost:8080/login.jsp

# check the private jsp is accessible through a servlet forward
http://localhost:8080/private

# take a look at the logs to see the RequestLoggerFilter working correctly

```

I was not able to let Tomcat get a context.xml and to read a web-fragment.xml in place of a web.xml
