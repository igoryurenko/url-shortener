# URL Shortener 

This app gives you an ability to enter some URL and shrink it.
Upon entering the URL, a "shortened" version of that url is created and shown to the user as this app URL.
When visiting that "shortened" version of the URL, the user is redirected to the original URL.

Written in Java using Spring Boot.

The Redis acts as a data storage. 

It was chosen because Redis is a key-value store and very suitable for such tasks. Our pairs are  an url and a contracted url. 

But the architecture of this app allows you easily switch to another storage - just add a necesessary implementation for the DAO API.

## Getting Started

To run:

```
$ mvn clean package
$ java -jar target/url-shortener-0.0.1-SNAPSHOT.jar
```

Then open you browser and go the main page of the app.
Default url is http://localhost:9000/

But you can change the port in the property file.


### Prerequisites

You need to install and start Redis. 
https://redis.io/topics/quickstart

```
$ brew install redis
$ redis-server
```


## Running the tests

Tests will run automatically.
But if you want you can skip them. Just add -Dmaven.test.skip=true

``` 
$ mvn clean package -Dmaven.test.skip=true
```


