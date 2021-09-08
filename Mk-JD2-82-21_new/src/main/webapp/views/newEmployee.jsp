<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ru">
    <head>
        <title>Новый сотрудник</title>
    </head>
    <body>
        <h1>Поздравляю! Новый сотрудник успешно добавлен!</h1>

        ${'Ваш ID - '}
        ${id}

        <br><br>

        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/index.jsp';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/getProfile.jsp';" value="Посмотреть информацию о себе">
    </body>
</html>