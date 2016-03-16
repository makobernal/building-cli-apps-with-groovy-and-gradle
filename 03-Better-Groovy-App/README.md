To build:
$ ./gradlew clean test uberjar

To run:
$ java -jar build/libs/03-Better-Groovy-App.jar -t 20 NaN

To force exception:
$ java -jar build/libs/03-Better-Groovy-App.jar -t twenty NaN