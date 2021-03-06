CREATE TABLE movie_member (
ID INTEGER PRIMARY KEY,
FirstName VARCHAR(20) NOT NULL,
LastName VARCHAR(20) NOT NULL
);

CREATE TABLE director (
DirectorID INTEGER REFERENCES movie_member(ID) PRIMARY KEY
FirstName VARCHAR(20) NOT NULL,
LastName VARCHAR(20) NOT NULL
);

CREATE TABLE movie (
FilmID INTEGER PRIMARY KEY,
AgeRating VARCHAR(3),
Rating NUMBER,
UserRating NUMBER,
DirectorID INTEGER REFERENCES director(DirectorID) NOT NULL
);

CREATE TABLE title (
Title VARCHAR(50),
FilmID INTEGER REFERENCES movie(FilmID),
PRIMARY KEY (Title, FilmID)
);

CREATE TABLE genre (
GenreID INTEGER PRIMARY KEY,
Name VARCHAR(20)
);

CREATE TABLE belongs_to (
FilmID INTEGER REFERENCES movie(FilmID),
GenreID INTEGER REFERENCES genre(GenreID) NOT NULL,
PRIMARY KEY (FilmID, GenreID)
);

CREATE TABLE customer (
Email VARCHAR(50) PRIMARY KEY,
FirstName VARCHAR(20) NOT NULL,
LastName VARCHAR(20) NOT NULL,
Password VARCHAR(30) NOT NULL,
Age INTEGER,
StreetNumber INTEGER NOT NULL,
StreetName VARCHAR(30) NOT NULL,
AptNumber INTEGER,
City VARCHAR(20) NOT NULL,
Zip VARCHAR(6) NOT NULL,
DateOfBirth DATE NOT NULL
);

CREATE TABLE purchases_or_rent (
Email VARCHAR(50) REFERENCES customer(Email),
FilmID INTEGER REFERENCES movie(FilmID),
PRIMARY KEY (Email, FilmID)
);

CREATE TABLE review (
UserRating NUMBER NOT NULL,
Email VARCHAR(50) REFERENCES customer(Email),
User_Comment VARCHAR(1000) NOT NULL,
FilmID INTEGER REFERENCES movie(FilmID),
PRIMARY KEY (FilmID, Email)
);

CREATE TABLE actor (
ActorID INTEGER REFERENCES movie_member(ID) PRIMARY KEY
FirstName VARCHAR(20) NOT NULL,
LastName VARCHAR(20) NOT NULL
);

CREATE TABLE stars (
FilmID INTEGER REFERENCES movie(FilmID),
ActorID INTEGER REFERENCES actor(ActorID),
PRIMARY KEY (FilmID, ActorID)
);

CREATE TABLE transaction (
TransactionID INTEGER PRIMARY KEY,
TransactionAmount NUMBER NOT NULL,
TransactionDate DATE NOT NULL,
Email REFERENCES customer(Email) NOT NULL
);


CREATE TABLE paypal (
TransactionID INTEGER REFERENCES transaction(TransactionID) PRIMARY KEY,
Email REFERENCES customer(Email) NOT NULL,
PayPalEmail VARCHAR(50) NOT NULL
);

CREATE TABLE credit_card (
TransactionID INTEGER REFERENCES transaction(TransactionID) PRIMARY KEY,
Email REFERENCES customer(Email) NOT NULL,
CardNumber VARCHAR(20) NOT NULL,
BankName VARCHAR(30) NOT NULL
);
