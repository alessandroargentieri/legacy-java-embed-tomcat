# legacy-java-embed-tomcat
An approach how to embed tomcat into a legacy Java frameworkless application 

## How to test it

There are two possibilities: use as a fatjar or as a war to be deployed into an external servlet container.

### Use as a fatjar

To use it as a **fatjar** with Tomcat embedded:

- Run it from the `Main.java` class through **IntelliJ IDE** or build the artifact with maven command `mvn clean package -P fatjar` from the terminal. Then run:
```bash
java -jar ./target/TomcatEmbeddedTest-1.0-SNAPSHOT.jar
```
- Open the browser and search the following:
```
# servlets defined into the web.xml file
http://localhost:8080/foo
http://localhost:8080/bar

# servlet defined with annotation
http://localhost:8080/annotated

# check the filter correctly works
http://localhost:8080/bar?block

# check the public jsp is visible
http://localhost:8080/login.jsp

# check the private jsp is accessible through a servlet forward
http://localhost:8080/private

# take a look at the logs to see the RequestLoggerFilter working correctly

```

### Use as a war and deploy into Tomcat through a Dockerfile

- Build the war artifact with `mvn clean package -P war`
- Build the docker image: `docker build -t mytomcat .` from your project folder
- Run the docker image: `docker run -it --rm -p 8080:8080 --name mytomcatinstance mytomcat`
- Go to the browser and call the enpoints:
```
# servlets defined into the web.xml file
http://localhost:8080/embedded/foo
http://localhost:8080/embedded/bar

# servlet defined with annotation
http://localhost:8080/embedded/annotated

# check the filter correctly works
http://localhost:8080/embedded/bar?block

# check the public jsp is visible
http://localhost:8080/embedded/login.jsp

# check the private jsp is accessible through a servlet forward
http://localhost:8080/embedded/private

# take a look at the logs to see the RequestLoggerFilter working correctly

```

I was not able to let Tomcat get a context.xml and to read a web-fragment.xml in place of a web.xml
