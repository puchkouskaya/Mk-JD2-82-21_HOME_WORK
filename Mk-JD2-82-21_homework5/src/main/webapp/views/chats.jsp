<%@ page language="java"
       contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Cooбщения</title>
    </head>
    <body>
        <h2>Полученные сообщения:<h2>
        <br/>

        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <table border="2">
            <tbody>
            <tr>
                <th>Логин</th>
                <th>Дата получения сообщения</th>
                <th>Текст сообщения</th>
            </tr>
            <c:forEach items="${messages}" var="item" >
                <tr>
                    <td width="20%">${item.getSenderOfMessage().getLogin()}</td>
                    <td width="20%">${item.getDate().toString()}</td>
                    <td width="60%">${item.getMessage()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/login.jsp';" value="Вернуться назад">
        </p>
    </body>
</html>

