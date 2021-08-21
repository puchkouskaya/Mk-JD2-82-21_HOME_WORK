<%@ page language="java"
       contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Регистрация</title>
    </head>
    <body>
        <form action="/Mk-JD2-82-21-0.0.0-SNAPSHOT/signUp" method="POST">
        <h2>ВНИМАНИЕ!!! Не было заполнено обязательное поле!<h2>
        <br>
        <h2>Для регистрации на сайте, пожалуйста, заполните обязательно ВСЕ следующие поля:<h2>
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
                    <tr>
                        <td>ФИО:</td>
                        <td>
                            <input type="text" name="fio">
                        </td>
                    </tr>
                    <tr>
                        <td>Дата рождения:</td>
                        <td>
                            <input type="date" name="birthday">
                        </td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Зарегистрироваться">
            <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/site.jsp';" value="Назад"></p>
        </form>
    </body>
</html>