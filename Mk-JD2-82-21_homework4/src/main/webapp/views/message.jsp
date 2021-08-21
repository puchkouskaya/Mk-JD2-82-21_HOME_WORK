<%@ page language="java"
       contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<html>
    <body>
        <h2>Желаете отправить сообщение?<h2>
        <h3>Для этого Вам необходимо ввести имя получателя и написать текст сообщения.<h3>
        <form action="/Mk-JD2-82-21-0.0.0-SNAPSHOT/message" method="POST">
            <table>
                <tbody>
                    <tr>
                        <td>Получатель:</td>
                        <td>
                            <input type="text" name="recipient" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Сообщение:</td>
                        <td>
                            <input type="text" name="message" value="">
                        </td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Отправить">
            <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/login.jsp';" value="Вернуться назад"></p>
        </form>
    </body>
</html>