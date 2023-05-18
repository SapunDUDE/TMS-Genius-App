This project is a simple version of genius web app. Here you can check song lyrics, add comments and rate your favourite songs.

## Database

Application use PostgreSQL database. For start the application you need Postgres server (jdbc:postgresql://localhost:
5432/storage) with created database 'music_db'. Database contains fourteen  tables.

* Table _album_ - contains all information about songs,genres, labels and producers;
* Table _album_type_ - contains possible album types (SINGLE,ALBUM,EP);
* Table _artist_ - contains such information about artist as country,firstname,lastname, date of birth etc.
* Table _comment_ - contains all ratings and opinions about songs.
* Table _genre_ - contains music genres;
* Table _l_artist-album_ - link table between artists and albums;
* Table _l_artist_song- - link table between artists and songs;
* Table _l_sound_producer_album_ - link table between producers and albums;
* Table _label_ - contains information about labels;
* Table _person_info_ - contains detailed information about artists and producers;
* Table _role_ - separates user and admin capabilities;
* Table _song_ - contains song lyrics and detailed information;
* Table _sound_producer_ - contains information about sound producers;
* Table _user_table_ - contains information about users;

## Album Controller endpoints

* http://localhost:8080/album/create - POST method to create a new album
* http://localhost:8080/album/delete - DELETE method to delete album
* http://localhost:8080/album/ - GET method to get album by title
* http://localhost:8080/album/producers/ - GET method to get album producers
* http://localhost:8080/album/artists - GET method to get artists by album title
* http://localhost:8080/album/songs - GET method to album songs

## AlbumTypeController endpoints

* http://localhost:8080/admin/albumtype- GET method, show all album's types
* http://localhost:8080/admin/albumtype - POST method, create album types
* http://localhost:8080/admin/albumtype - PUT method, update album types
* http://localhost:8080/admin/albumtype - DELETE method, delete album type 

## ArtistController endpoints

* http://localhost:8080/admin/artist - POST method, create an artist
* http://localhost:8080/artist - PUT method, update artist
* http://localhost:8080/artist - DELETE method, delete artist
* http://localhost:8080/artist/{nickname} - DELETE method, delete artist by nickname
* http://localhost:8080/artist/{nickname} - GET method, get artist by nickname
* http://localhost:8080/artist/albums/{nickname} - GET method, get all artist albums
* http://localhost:8080/artist/songs/{nickname} - GET method, get all artist songs

## SongController endpoints

* http://localhost:8080/song/comment/add - POST method, add comment to song
* http://localhost:8080/song/comment/edit - PUT method, edit comment
* http://localhost:8080/song/comment/delete - DELETE method, delete comment
* http://localhost:8080/admin/song/comments - GET method, get all song comments

## UserController endpoints

* http://localhost:8080/admin/user - POST method, create user
* http://localhost:8080/user/{login} - DELETE method, delete user by login
* http://localhost:8080/user/{login} - GET method, get user by login
* http://localhost:8080/user/comments- GET method, get all user comments
