<%@ page language="java"
       contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Авторизация</title>
    </head>
    <body>
        <h2>Введите свои логин и пароль:<h2>
        <form action="/Mk-JD2-82-21-0.0.0-SNAPSHOT/signIn" method="GET">
            <table>
                <tbody>
                    <tr>
                        <td>Логин:</td>
                        <td>
                            <input type="text" name="login">
                        </td>
                    </tr>
                    <tr>
                        <td>Пароль:</td>
                        <td>
                            <input type="password" name="password">
                        </td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Войти">
            <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/site.jsp';" value="Назад"></p>
        </form>
    </body>
</html>