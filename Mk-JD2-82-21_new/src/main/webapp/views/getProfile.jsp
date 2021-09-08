<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ru">
    <head>
        <title>Получение карточки сотрудника/title>
    </head>
    <body>
        <form action="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employee" method="GET">
        <h2>Для получения карточки сотрудника необходимо ввести ID сотрудника</h2>
            <table>
                <tbody>
                    <tr>
                        <td>ID сотрудника:</td>
                        <td>
                            <input type="text" name="id_employee">
                        </td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Получить сведения">
            <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/index.jsp';" value="Назад"></p>
        </form>
    </body>
</html>
