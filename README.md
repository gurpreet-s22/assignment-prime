## assignment-prime

### Application developed in Spring Boot, Java 17, Maven

### How to run the application
- Run as Spring Boot application which has embedded tomcat server. Run "MainApplication.java"
- or deploy in an container Server as war

### Configure
- By default the application will run on locahost port: 8080. It can be changed "server.port" in Application.properties. Note: This change is applicable only for embedded tomcat server port.

### Features
- Supports JSON (default) and XML response type. Pass Accept: application/xml or Accept: application/json in request header. 
- Support multiple algorithms for prime number.  Algo can be switched by passing request parameter as "?algo=one" or "?algo=two". If "algo" request parameter is not passed then by default the algo is "one".
- Application caches prime numbers in LRU cache which has max capacity to hold 10 entries. If prime numbers are already calculated for a given input value then it is a cache hit or else cache miss. In case of cache miss, the application will calculate the prime numbers for a given input value.

### Rest API
- Send a GET request "localhost:8080/assignment/primes/10"
- Switch prime number algo. GET request "localhost:8080/assignment/primes/10?algo=two"
- JSON response
  - {"initial":10,"primes":[2,3,5,7]}
- XML response
  - <PrimeNumbers><initial>10</initial><primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes></primes></PrimeNumbers> 
- Error handling
  - if the request parameter "algo" is invalid value which is not supported by the application. Example "localhost:8080/assignment/primes/11?algo=anything"
    - {"errorMessage":"Prime numbers algo does not exists with name anything"} 
