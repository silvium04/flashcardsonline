

create table USERS(
  userId SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    password VARCHAR(255) NOT NULL
);


create table CATEGORIES(
  categoryId SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


create table DECKS(
  deckId SERIAL PRIMARY KEY,
      userId INT NOT NULL,
      name VARCHAR(100) NOT NULL,
      creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (userId) REFERENCES USERS(userId) ON DELETE CASCADE
);



create table FLASHCARDS(
  flashcardId SERIAL PRIMARY KEY,
     deckId INT NOT NULL,
     front TEXT,
     back TEXT,
     frontImage TEXT,
     backImage TEXT,
     creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     lastRight TIMESTAMP,
     step INT DEFAULT 0,
     FOREIGN KEY (deckId) REFERENCES DECKS(deckId) ON DELETE CASCADE
);



create table DECKS_CATEGORIES(
  deckCategoryId SERIAL PRIMARY KEY,
    deckId INT NOT NULL,
    categoryId INT NOT NULL,
    FOREIGN KEY (deckId) REFERENCES DECKS(deckId) ON DELETE CASCADE,
    FOREIGN KEY (categoryId) REFERENCES CATEGORIES(categoryId) ON DELETE CASCADE
);


