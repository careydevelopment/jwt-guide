# jwt-guide
This repository includes a fully deployable Spring Boot app that demonstrates how to implement a JSON Web Token (JWT) solution.

If you're interested in learning more about how this app works, feel free to check out <a href="https://careydevelopment.us/blog/how-to-implement-a-json-web-token-jwt-solution-in-spring-boot" target="_blank">the tutorial.</a>

You can run the Spring Boot app by right-clicking on the JwtGuide class and selecting Run As... and Java Application.

You'll have to use a tool like Postman to POST the authentication request to this URL:

http://localhost:8080/authenticate

With this payload:

{"username" : "johnny", "password" : "kleptocracy"}

Then, you can put the bearer token in your request header for future requests.