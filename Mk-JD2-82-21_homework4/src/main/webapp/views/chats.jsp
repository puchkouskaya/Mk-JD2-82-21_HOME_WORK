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

        <c:forEach items="${messages}" var="item" >
            ${item.getSenderOfMessage().getLogin()}
            ${" - "}
            ${item.getDate().toString()}<br>
            ${item.getMessage()}<br><br>
        </c:forEach>

        <p>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/login.jsp';" value="Вернуться назад">
        </p>
    </body>
</html>

