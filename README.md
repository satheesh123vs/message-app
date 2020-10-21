# Spring_Boot_DEMO : message-app

The :message-app, is a sample application using Spring boot, for calculating sum of random numbers.  
This application can be accessed by humans and not a by any computers or bot.

Start the application by running:
mvn spring-boot:run 

The server will run on port 8081. To change this, Please edit the src/main/resources/application.properties file.

http://localhost:8081/message-app.html

After starting the app using bat file , hit the below url to see the app.
http://localhost:8081/message-app.html

To display new CAPCHE, use refresh button given in the page.

Run the test
mvn test or run as Junit(for the controller to test)


working Scenario
----------------
hit the url :  http://localhost:8081/message-app.html
(Providing user with a question and then allowing user to submit an answer.) 

Service: “Please sum the numbers 9,5,3”.
Client:  “Please sum the numbers 9,5,3” and the answer is 15.
Service: "That’s wrong. Please try again.
Client:  “Please sum the numbers 9,5,3” and the answer is 17.
Service:  That’s correct

When the server receives the question and the sum of the numbers,
Server verifies the question it has received is previously given to a particular client request and the answer by calculating the sum of numbers mentioned in the question after matching the questions.

In message-app, the Encryption of data exchange is done using the basic approach of base64, 
I could try an alternative approach using: AES(Advanced Encryption standard), if had more time. 

A postman collection named as "message-app.postman_collection.json", is created and is available in root folder of message-app. 

