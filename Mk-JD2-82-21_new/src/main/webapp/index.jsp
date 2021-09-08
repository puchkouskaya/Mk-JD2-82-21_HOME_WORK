<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="ru">
    <head>
        <title>Главная страница/title>
    </head>
    <body>
        <h1>Добро пожаловать на наш сайт!<h1>
        <h3>Вы можете зарегистрировать нового сотрудника:<h3>
        <p><input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/employee.jsp';" value="Зарегистрировать">
        </p>
        <h3>Также Вы можете:<h3>
        <p><input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/structure.jsp';" value="Посмотреть структуру" />
        </p>
        <p><input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/getProfile.jsp';" value="Посмотреть информацию о себе" />
        </p>
    </body>
</html>
