This the read me file for this FYP.

The main goal of this FYP was to research and implement the security features when creating a web game. 
In this read me document I hope to break down the different features and components within this project.

The web game:

Blackjack was the web game I had chosen to develop for this project. As creating a web game is an entire 
different project in itself, I wanted to create a simple web game, that could also be used to demonstrate 
the security features that help to protect web games.

Authentication & Authorization:

Encryption:

The encryption algorithm used for the data encryption is the AES encryption algorithm. 

Database:

The data is stored in a MySQL database. Access to the database is done through MAMP local server.

HTTPS:

The HTTPS connectivity is achieved through an SSL certificate. The SSL certificate used for this application is a self generated SSL cert.

Payments:

The below methods are used to validate the card details for an order.

public Boolean cardValidator(CardDetails cardDetails);
public Boolean csvValidator(String card_number, String csv);
public Boolean CardNumberValidator(String card_number);
public Boolean expiryDateValidator(String expiry_date);
public Boolean cardHolderNameValidator(String name);

Tokens:

Tokens are used as an extra level of security when a user is placing an order.
When the card details are validated, a token is then created 
Before a payment is authorised, a check is done to see if the user has a valid token
If the token is valid then the payment is authorized, else the payment won't be authorized.

Registration: 

Registering to the application can be done at the sign in page. 
If you done have an account, you can click the create account button.
Once you fill out the registration form and your details are valid, your account is then created

How to setup the project enviornment:

step 1: import the sql script located in the sql folder in the static folder found in the resources section to MySQL workbench
step 2: once the sql script is imported, open up your local server provider and run mySQL
step 3: open up the application.properties file found in this project and edit the username and password, port number if needed
step 4: once step 3 is done, build the application and then run this application
step 5: if application run is successful, then project enviornment is set up right and the application is ready to use

additional step: if the admin view wants to be accessed use the following details:
username: rben1595@gmail.com 
password: benryan123

How to Play:

In order to play the blackjack game, you must select the play game button on the header.
Once this button is pressed, you are then redirect to the game page.

Steps to play:

1st step: place a bet using once of the 5 bet buttons (€100, €200, €300, €500, €1000)
2nd step: use one of the two commands - hit or double down
3rd step: wait to see if hand is gone bust or can you hit again
4th step: if round is over, either play another round or leave the game
5th step: once new round is start, follow the steps from the begining 




