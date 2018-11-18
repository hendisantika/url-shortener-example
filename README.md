# url-shortener-example


This example shows a simple URL Shortener implementation using

* Spring Boot 2.1.0 (Spring Web MVC + Spring Data Redis)
* Guava 18.0
* Common Validator 1.6

Run this project by this command : `mvn clean spring-boot:run`

#### URLs
* POST `/rest/url` with body as `long_url_string` - For creating the short url from long url
* GET `/rest/url/{id}` - For retrieving the long URL from short `id`
