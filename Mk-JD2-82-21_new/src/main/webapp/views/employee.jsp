<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ru">
    <head>
        <title>Новый сотрудник/title>
    </head>
    <body>
        <form action="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employee" method="POST">
        <h2>Заполните все следующие поля:<h2>
            <table>
                <tbody>
                    <tr>
                        <td>Имя сотрудника:</td>
                        <td>
                            <input type="text" name="name">
                        </td>
                    </tr>
                    <tr>
                        <td>Размер заработной платы:</td>
                        <td>
                            <input type="text" name="salary">
                        </td>
                    </tr>

                </tbody>
            </table>
            <p><input type="submit" value="Внести сведения">
            <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/index.jsp';" value="Назад"></p>
        </form>
    </body>
</html>
