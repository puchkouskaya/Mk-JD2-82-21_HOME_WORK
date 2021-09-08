<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ru">
    <head>
        <title>Карточка сотрудника/title>
    </head>
    <body>
        <h1>Карточка пользователя</h1>
        ${'ID пользователя: '}
        ${employee.getId()}<br>

        ${'Имя: '}
        ${employee.getName()}<br>

        ${'Зарплата: '}
        ${employee.getSalary()}<br>

        <br><br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/index.jsp';" value="Назад"></p>
    </body>
</html>
