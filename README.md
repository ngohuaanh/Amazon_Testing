## Getting started with Amazon_Testing
Perform automation test for Amazon website

### Build project
```
mvn clean install
```

### Execute test

java -jar target/amazon-0.0.1-SNAPSHOT.jar -c CONFIG_FILE_PATH  -g GROUP_OF_TESTCASE -o OUTPUT_TEST_REPORT_DIRECTORY


```
java -jar target/amazon-0.0.1-SNAPSHOT.jar -c Resource/Configuration/config.properties  -g "Full" -o ouput
```
or
```
java -jar target/amazon-0.0.1-SNAPSHOT.jar -c Resource/Configuration/config.properties  -g "Smoke"
```

