<%@ page language="java"
       contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<html>
    <head>
	    <title>Информация о приложении</title>
    </head>

    <body>
    <h2> Информация о приложении: </h2><br/>

    ${" 1. Время запуска приложения: "}
    ${time}<br>
    </p>
    <p> 2. Способ хранения данных:
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <c:if test="${storageType.equalsIgnoreCase('FILE')}">
         <p>- в файле.</p>
     </c:if>
     <c:if test="${storageType.equalsIgnoreCase('MEMORY')}">
         <p>- в памяти приложения.</p>
     </c:if>

    <p>
    <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/login.jsp';" value="Вернуться назад"></p>
    </form>
    </body>
</html>




