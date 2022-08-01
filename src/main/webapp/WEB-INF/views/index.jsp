<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
<div class="container">
    <div class="row">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Имя</th>
                <th scope="col">Текст</th>
                <th scope="col">Адрес</th>
                <th scope="col">Тип</th>
                <th scope="col">Статья</th>
                <th scope="col">Обновить</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accident" items="${accidents}">
                <tr>
                    <td>${accident.id}</td>
                    <td>${accident.name}</td>
                    <td>${accident.text}</td>
                    <td>${accident.address}</td>
                    <td>${accident.accidentType.name}</td>
                    <td>
                        <c:forEach var="rule" items="${accident.rules}">
                            <p>${rule.name}</p>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="<c:url value='/formUpdate?id=${accident.id}'/>">Обновить</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <a class="btn btn-primary" href="<c:url value='/create'/>">Добавить инцидент</a>
    <a class="btn btn-primary" href="<c:url value='/logout'/>">Выход</a>
</div>
</body>
</html>