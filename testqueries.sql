SELECT FIRSTNAME AS "FIRST NAME",
TITLE AS "FILM" FROM STARS,
ACTOR, MOVIE, TITLE WHERE ACTOR.ACTORID = STARS.ACTORID AND MOVIE.FILMID = STARS.FILMID AND MOVIE.FILMID = TITLE.FILMID;


SELECT TITLE,
NAME AS "GENRE" FROM BELONGS_TO, MOVIE, GENRE, TITLE
WHERE BELONGS_TO.FILMID = MOVIE.FILMID AND
BELONGS_TO.GENREID = GENRE.GENREID AND TITLE.FILMID = MOVIE.FILMID;

SELECT COUNT(*) AS "MOVIE MEMBERS" FROM MOVIE_MEMBER;

SELECT AVG(USERRATING) AS "AVERAGE RATING FOR ALL FILMS" FROM MOVIE;

SELECT COUNT(*) AS "NUMBER OF FILMS", AGERATING AS "AGE RATING" FROM MOVIE GROUP BY AGERATING;
