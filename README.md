============Q1================
# Amazon_Testing
Perform automation test for Amazon website

# Build project
mvn clean install

# Execute test

java -jar target/amazon-0.0.1-SNAPSHOT.jar -c CONFIG_FILE_PATH  -g GROUP_OF_TESTCASE -o OUTPUT_TEST_REPORT_DIRECTORY

Ex: java -jar target/amazon-0.0.1-SNAPSHOT.jar -c Resource/Configuration/config.properties  -g "Full" -o ouput
	java -jar target/amazon-0.0.1-SNAPSHOT.jar -c Resource/Configuration/config.properties  -g "Smoke"

============Q1================
# Linux
./script.sh INPUT_PATH
Ex: ./script.sh input.csv
# Mac
sh script.sh INPUT_PATH
Ex: sh script.sh input.csv