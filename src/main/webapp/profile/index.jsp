<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Профиль пользователя</title>
    <style>
        #user {
            border: 1px solid #333;
        }
    </style>
</head>
<body>

<jsp:useBean id="user" scope="request" class="model.User"/>

<table id="user">
    <tr>
        <th>название</th>
        <th>значение</th>
    </tr>
    <tr>
        <td>Имя</td>
        <td>${user.firstName + " " + user.lastName}</td>
    </tr>
    <tr>
        <td>Адрес</td>
        <td>${user.address}</td>
    </tr>
    <tr>
        <td>Дата рождения</td>
        <td>${user.dob}</td>
    </tr>
    <tr>
        <td>E-mail</td>
        <td>${user.email}</td>
    </tr>
    <tr>
        <td>Телефон</td>
        <td>${user.telephone}</td>
    </tr>
    <tr>
        <td>Роль</td>
        <td>${user.role}</td>
    </tr>
</table>

</body>
</html>
