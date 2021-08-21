<%@ page language="java"
       contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Квиз</title>
</head>
<body>
<form action="/Mk-JD2-82-21-0.0.0-SNAPSHOT/vote" method="POST">
    Исполнитель: <input type="radio" name="artist" value="1" />Zivert
    <input type="radio" name="artist" value="2" />INNA
    <input type="radio" name="artist" value="3" />Minelli
    <input type="radio" name="artist" value="4" />Asti
    <br><br>
    Любимые жанры музыки:
    <input type="checkbox" name="genre" value="1" />Classic
    <input type="checkbox" name="genre" value="2" />Jazz
    <input type="checkbox" name="genre" value="3" />Opera
    <input type="checkbox" name="genre" value="4" />Rock
    <input type="checkbox" name="genre" value="5" />Techno
    <input type="checkbox" name="genre" value="6" />Pop
    <input type="checkbox" name="genre" value="7" />Hard-Rock
    <input type="checkbox" name="genre" value="8" />Disco
    <input type="checkbox" name="genre" value="9" />Rap
    <input type="checkbox" name="genre" value="10" />Folk
    <br><br>
    Краткая информация о себе: <input name="about" size="100">
    <br><br>
    <input type="submit" value="Готово">
</form>
</body>
</html>