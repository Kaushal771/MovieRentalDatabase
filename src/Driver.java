
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
 
class Driver {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
 
        try {
            Class.forName("oracle.jdbc.OracleDriver");
           
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:ashi/07112231@oracle.scs.ryerson.ca:1521:orcl");
           
           
 
            // Connection established
 
            loop: while (true) {
                System.out.println("Enter 1 to drop tables");
                System.out.println("Enter 2 to create tables");
                System.out.println("Enter 3 to populate tables");
                System.out.println("Enter 4 to query tables");
                System.out.println("Enter 5 to exit program");
                System.out.print("Enter choice:");
 
                int choice = in.nextInt();
 
                if (choice == 1) {
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("DROP TABLE MOVIE_MEMBER CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE DIRECTOR CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE MOVIE CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE TITLE CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE GENRE CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE BELONGS_TO CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE CUSTOMER CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE PURCHASES_OR_RENT CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE REVIEW CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE ACTOR CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE STARS CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE TRANSACTION CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE PAYPAL CASCADE CONSTRAINTS");
                    stmt.executeUpdate("DROP TABLE CREDIT_CARD CASCADE CONSTRAINTS");
                    System.out.println("Tables sucessfully deleted.");
                } else if (choice == 2) {
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(
                            "CREATE TABLE movie_member (ID INTEGER PRIMARY KEY,FirstName VARCHAR(20) NOT NULL,LastName VARCHAR(20) NOT NULL)");
                    stmt.executeUpdate(
                            "CREATE TABLE director (DirectorID INTEGER REFERENCES movie_member(ID) PRIMARY KEY,FirstName VARCHAR(20) NOT NULL,LastName VARCHAR(20) NOT NULL)");
                    stmt.executeUpdate(
                            "CREATE TABLE movie (FilmID INTEGER PRIMARY KEY,AgeRating VARCHAR(3),Rating NUMBER,UserRating NUMBER,DirectorID INTEGER REFERENCES director(DirectorID) NOT NULL)");
                    stmt.executeUpdate(
                            "CREATE TABLE title (Title VARCHAR(50),FilmID INTEGER REFERENCES movie(FilmID),PRIMARY KEY (Title, FilmID))");
                    stmt.executeUpdate("CREATE TABLE genre (GenreID INTEGER PRIMARY KEY,Name VARCHAR(20))");
                    stmt.executeUpdate(
                            "CREATE TABLE belongs_to (FilmID INTEGER REFERENCES movie(FilmID),GenreID INTEGER REFERENCES genre(GenreID) NOT NULL,PRIMARY KEY (FilmID, GenreID))");
                    stmt.executeUpdate(
                            "CREATE TABLE customer (Email VARCHAR(50) PRIMARY KEY,FirstName VARCHAR(20) NOT NULL,LastName VARCHAR(20) NOT NULL,Password VARCHAR(30) NOT NULL,Age INTEGER,StreetNumber INTEGER NOT NULL,StreetName VARCHAR(30) NOT NULL,AptNumber INTEGER,City VARCHAR(20) NOT NULL,Zip VARCHAR(6) NOT NULL,DateOfBirth DATE NOT NULL)");
                    stmt.executeUpdate("CREATE TABLE purchases_or_rent (Email VARCHAR(50) REFERENCES customer(Email),FilmID INTEGER REFERENCES movie(FilmID),PRIMARY KEY (Email, FilmID))");
                    stmt.executeUpdate("CREATE TABLE review (UserRating NUMBER NOT NULL,Email VARCHAR(50) REFERENCES customer(Email),User_Comment VARCHAR(1000) NOT NULL,FilmID INTEGER REFERENCES movie(FilmID),PRIMARY KEY (FilmID, Email))");
                    stmt.executeUpdate(
                            "CREATE TABLE actor (ActorID INTEGER REFERENCES movie_member(ID) PRIMARY KEY,FirstName VARCHAR(20) NOT NULL,LastName VARCHAR(20) NOT NULL)");
                    stmt.executeUpdate(
                            "CREATE TABLE stars (FilmID INTEGER REFERENCES movie(FilmID),ActorID INTEGER REFERENCES actor(ActorID),PRIMARY KEY (FilmID, ActorID))");
                    stmt.executeUpdate(
                            "CREATE TABLE transaction (TransactionID INTEGER PRIMARY KEY,TransactionAmount NUMBER NOT NULL,TransactionDate DATE NOT NULL,Email REFERENCES customer(Email) NOT NULL)");
                    stmt.executeUpdate(
                            "CREATE TABLE paypal (TransactionID INTEGER REFERENCES transaction(TransactionID) PRIMARY KEY,Email REFERENCES customer(Email) NOT NULL,PayPalEmail VARCHAR(50) NOT NULL)");
                    stmt.executeUpdate(
                            "CREATE TABLE credit_card (TransactionID INTEGER REFERENCES transaction(TransactionID) PRIMARY KEY,Email REFERENCES customer(Email) NOT NULL,CardNumber VARCHAR(20) NOT NULL,BankName VARCHAR(30) NOT NULL)");
                    System.out.println("Tables successfully created.");
                } else if (choice == 3) {
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("INSERT INTO MOVIE_MEMBER VALUES(1,'Hugh','Jackman')");
                    stmt.executeUpdate("INSERT INTO MOVIE_MEMBER VALUES(2,'Steven','Spielberg')");
                    stmt.executeUpdate("INSERT INTO MOVIE_MEMBER VALUES(3,'Jon','Favreau')");
                    stmt.executeUpdate("INSERT INTO MOVIE_MEMBER VALUES(4,'George','Miller')");
                    stmt.executeUpdate("INSERT INTO MOVIE_MEMBER VALUES(5,'Tom','Hardy')");
                    stmt.executeUpdate("INSERT INTO DIRECTOR VALUES(2,'Steven','Spielberg')");
                    stmt.executeUpdate("INSERT INTO DIRECTOR VALUES(3,'Jon','Favreau')");
                    stmt.executeUpdate("INSERT INTO DIRECTOR VALUES(4,'George','Miller')");
                    stmt.executeUpdate("INSERT INTO MOVIE VALUES(1,'PG',5,4,2)");
                    stmt.executeUpdate("INSERT INTO MOVIE VALUES(2,'PG',8,7,3)");
                    stmt.executeUpdate("INSERT INTO MOVIE VALUES(3,'R18',7,8,4)");
                    stmt.executeUpdate("INSERT INTO TITLE VALUES('X-Men',1)");
                    stmt.executeUpdate("INSERT INTO TITLE VALUES('Iron Man',2)");
                    stmt.executeUpdate("INSERT INTO TITLE VALUES('Mad Max:Fury Road',3)");
                    stmt.executeUpdate("INSERT INTO GENRE VALUES(1,'Action')");
                    stmt.executeUpdate("INSERT INTO BELONGS_TO VALUES(1,1)");
                    stmt.executeUpdate("INSERT INTO BELONGS_TO VALUES(2,1)");
                    stmt.executeUpdate("INSERT INTO BELONGS_TO VALUES(3,1)");
                    stmt.executeUpdate(
                            "INSERT INTO CUSTOMER VALUES ('dave@gmail.com','Dave','Smith','123',NULL,1234,'Fake Street',NULL,'Toronto','M5T2L4',TO_DATE('1998/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'))");
                    stmt.executeUpdate(
                            "INSERT INTO CUSTOMER VALUES ('sam@gmail.com','Sam','Smith','abc',NULL,100,'Some Street',NULL,'Toronto','M1G8W3',TO_DATE('1997/03/01 21:02:44', 'yyyy/mm/dd hh24:mi:ss'))");
                    stmt.executeUpdate("INSERT INTO PURCHASES_OR_RENT VALUES('dave@gmail.com',1)");
                    stmt.executeUpdate("INSERT INTO REVIEW VALUES(4.5,'dave@gmail.com','Good movie.',1)");
                    stmt.executeUpdate("INSERT INTO REVIEW VALUES(8,'sam@gmail.com','Great movie.',2)");
                    stmt.executeUpdate("INSERT INTO ACTOR VALUES(1,'Hugh','Jackman')");
                    stmt.executeUpdate("INSERT INTO ACTOR VALUES(5,'Tom','Hardy')");
                    stmt.executeUpdate("INSERT INTO STARS VALUES(1,1)");
                    stmt.executeUpdate("INSERT INTO STARS VALUES(3,5)");
                    System.out.println("Records successfully created.");
                } else if (choice == 4) {
                    Statement stmt = con.createStatement();
                    ResultSet rs = null;
 
                    rs = stmt.executeQuery("SELECT * FROM MOVIE_MEMBER");
                    while (rs.next()) {
                        System.out.println("ID " + rs.getInt("ID"));
                        System.out.println("First Name " + rs.getString("FirstName"));
                        System.out.println("Last Name " + rs.getString("LastName"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM DIRECTOR");
                    while (rs.next()) {
                        System.out.println("Director ID " + rs.getInt("DirectorID"));
                        System.out.println("First Name " + rs.getString("FirstName"));
                        System.out.println("Last Name " + rs.getString("LastName"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM MOVIE");
                    while (rs.next()) {
                        System.out.println("Film ID " + rs.getInt("FilmID"));
                        System.out.println("Age Rating " + rs.getString("AgeRating"));
                        System.out.println("Rating " + rs.getFloat("Rating"));
                        System.out.println("User Rating " + rs.getFloat("UserRating"));
                        System.out.println("Director ID " + rs.getInt("DirectorID"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM TITLE");
                    while (rs.next()) {
                        System.out.println("Title " + rs.getString("Title"));
                        System.out.println("Film ID " + rs.getInt("FilmID"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM GENRE");
                    while (rs.next()) {
                        System.out.println("Genre ID " + rs.getInt("GenreID"));
                        System.out.println("Name " + rs.getString("Name"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
                    while (rs.next()) {
                        System.out.println("Email " + rs.getString("Email"));
                        System.out.println("First Name " + rs.getString("FirstName"));
                        System.out.println("Last Name " + rs.getString("LastName"));
                        System.out.println("Password " + rs.getString("Password"));
                        System.out.println("Age " + rs.getInt("Age"));
                        System.out.println("Street Number " + rs.getInt("StreetNumber"));
                        System.out.println("Street Name " + rs.getString("StreetName"));
                        System.out.println("Apartment Number " + rs.getInt("AptNumber"));
                        System.out.println("City  " + rs.getString("City"));
                        System.out.println("ZIP " + rs.getString("Zip"));
                        System.out.println("Date of Birth " + rs.getDate("DateOfBirth"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM PURCHASES_OR_RENT");
                    while (rs.next()) {
                        System.out.println("Email " + rs.getString("Email"));
                        System.out.println("Film ID" + rs.getInt("FilmID"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM REVIEW");
                    while (rs.next()) {
                        System.out.println("User Rating " + rs.getFloat("UserRating"));
                        System.out.println("Email " + rs.getString("Email"));
                        System.out.println("User Comment " + rs.getString("User_Comment"));
                        System.out.println("Film ID" + rs.getInt("FilmID"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM ACTOR");
                    while (rs.next()) {
                        System.out.println("Actor ID " + rs.getInt("ActorID"));
                        System.out.println("First Name " + rs.getString("FirstName"));
                        System.out.println("Last Name " + rs.getString("LastName"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM STARS");
                    while (rs.next()) {
                        System.out.println("Film ID " + rs.getInt("FilmID"));
                        System.out.println("Actor ID " + rs.getInt("ActorID"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM TRANSACTION");
                    while (rs.next()) {
                        System.out.println("Transaction ID " + rs.getInt("TransactionID"));
                        System.out.println("Transaction Amount " + rs.getFloat("TransactionAmount"));
                        System.out.println("Transaction Date " + rs.getDate("TransactionDate"));
                        System.out.println("Email " + rs.getString("Email"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM PAYPAL");
                    while (rs.next()) {
                        System.out.println("Transaction ID " + rs.getInt("TransactionID"));
                        System.out.println("Email " + rs.getString("Email"));
                        System.out.println("PayPal Email " + rs.getString("PayPalEmail"));
                    }
 
                    rs = stmt.executeQuery("SELECT * FROM CREDIT_CARD");
                    while (rs.next()) {
                        System.out.println("Transaction ID " + rs.getInt("TransactionID"));
                        System.out.println("Email " + rs.getString("Email"));
                        System.out.println("Card Number " + rs.getString("CardNumber"));
                        System.out.println("Bank Name " + rs.getString("BankName"));
                    }
                } else if (choice == 5) {
                    break loop;
                }
            }
 
            con.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}