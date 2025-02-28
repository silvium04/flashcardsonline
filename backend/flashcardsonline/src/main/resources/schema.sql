

create table USERS(
    userId SERIAL PRIMARY KEY,
    userId VARCHAR(50) UNIQUE NOT NULL,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    password VARCHAR(255) NOT NULL
);


create table DECKS(
  deckId SERIAL PRIMARY KEY,
      userId INT NOT NULL,
      name VARCHAR(100) NOT NULL,
      creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (userID) REFERENCES USER(UserID) ON DELETE CASCADE
);



create table FLASHCARDS(


);


create table DECKS_CATEGORIES(

);


create table CATEGORIES(

);