<%@ page language="java"
       contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Информация о пользователях</title>
</head>

<body>
	<h2>Все зарегистрированные пользователи</h2>
<table border="2">
    <tr>
        <th>Логин</th>
        <th>ФИО пользователя</th>
        <th>Пароль</th>
        <th>Дата рождения</th>
    </tr>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <td>${user.getLogin()}</td>
            <td>${user.getFio()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getDateOfBirth()}</td>
        </tr>
    </c:forEach>
</table>
<p>
<p><input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/about';" value="Узнать информацию о приложении"></p>
<p><input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/login.jsp';" value="Вернуться назад"></p>
</form>
</body>
</html>