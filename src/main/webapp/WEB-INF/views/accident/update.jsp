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
<div class="container" style="width: 50%">
    <div class="card">
        <div class="card-header">
            Обновить инцидент.
        </div>
        <div class="card-body">
            <form action="<c:url value='/update'/>" method='POST'>
                <div class="form-group">
                    <input type="hidden" name="id" value="${accident.id}">
                    <input type="hidden" name="accidentType.id" value="${accident.accidentType.id}">
                    <c:forEach var="rule" items="${accident.rules}">
                        <input type="hidden" name="rIds" value="${rule.id}">
                    </c:forEach>
                    <label>Имя
                        <input type="text" class="form-control" name="name" value="${accident.name}" required>
                    </label>
                </div>
                <div class="form-group">
                    <label>Описание
                        <input type="text" class="form-control" name="text" value="${accident.text}" required>
                    </label>
                </div>
                <div class="form-group">
                    <label>Адрес
                        <input type="text" class="form-control" name="address" value="${accident.address}" required>
                    </label>
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
                <a class="btn btn-primary" href="<c:url value='/'/>">Назад</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>