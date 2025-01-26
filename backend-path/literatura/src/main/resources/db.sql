-- Drop the database if it already exists
DROP DATABASE IF EXISTS g7_db;

-- Create the database
CREATE DATABASE g7_db;

-- Connect to the newly created database
\c g7_db;

-- Create the Person table
CREATE TABLE person
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    birth_year VARCHAR(255),
    death_year VARCHAR(255)
);

-- Create the Book table
CREATE TABLE book
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(255),
    copyright      BOOLEAN,
    media_type     VARCHAR(50),
    download_count INTEGER
);

-- Create the bookshelves table for storing multiple bookshelves for each book
CREATE TABLE book_bookshelves
(
    book_id   INTEGER REFERENCES book (id) ON DELETE CASCADE,
    bookshelf VARCHAR(255),
    PRIMARY KEY (book_id, bookshelf)
);

-- Create the subjects table for storing multiple subjects for each book
CREATE TABLE book_subjects
(
    book_id INTEGER REFERENCES book (id) ON DELETE CASCADE,
    subject VARCHAR(255),
    PRIMARY KEY (book_id, subject)
);

-- Create the languages table for storing multiple languages for each book
CREATE TABLE book_languages
(
    book_id  INTEGER REFERENCES book (id) ON DELETE CASCADE,
    language VARCHAR(50),
    PRIMARY KEY (book_id, language)
);

-- Create the book formats table for storing different formats for each book
CREATE TABLE book_formats
(
    book_id INTEGER REFERENCES book (id) ON DELETE CASCADE,
    format  VARCHAR(255),
    url     VARCHAR(255),
    PRIMARY KEY (book_id, format)
);

-- Create the many-to-many relation between Book and Author (Person)
CREATE TABLE book_authors
(
    book_id   INTEGER REFERENCES book (id) ON DELETE CASCADE,
    author_id INTEGER REFERENCES person (id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, author_id)
);

-- Create the many-to-many relation between Book and Translator (Person)
CREATE TABLE book_translators
(
    book_id       INTEGER REFERENCES book (id) ON DELETE CASCADE,
    translator_id INTEGER REFERENCES person (id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, translator_id)
);

-- Create the summaries table for storing multiple summaries for each book
CREATE TABLE book_summaries
(
    book_id INTEGER REFERENCES book (id) ON DELETE CASCADE,
    summary TEXT,
    PRIMARY KEY (book_id, summary)
);
