## Allure Reporting

### Project Setup

Add the following plugin in pom:

```
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.21.0</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <argLine>
                        -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                    </argLine>
                    <systemPropertyVariables>
                        <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
                    </systemPropertyVariables>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.aspectj</groupId>
                        <artifactId>aspectjweaver</artifactId>
                        <version>${aspectj.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
```

Then run the build as usual:

1. mvn clean test

Allure results will appear in target/allure-results folder. To generate html report and automatically open it in a web
browser, run the following command:

2. allure serve target/allure-results

## Configuration

Location of allure-results directory, and patterns for @TmsLink and @Issue links can be set by placing allure.properties
file with following properties to resources directory: src/test/resources

allure.properties

```
1 allure.results.directory=target/allure-results
2 allure.link.issue.pattern=https://example.org/browse/{}
3 allure.link.tms.pattern=https://example.org/browse/{}
```

Or it can also be done by adding this in pom:

```
<systemPropertyVariables>
    <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
</systemPropertyVariables>
```
