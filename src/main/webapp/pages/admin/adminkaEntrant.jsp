<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>Привет мир!</title>

    <style>
        .dropbtn {
            background-color: #4c84af;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #f1f1f1}

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown:hover .dropbtn {
            background-color: #3e8e41;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" style="text-align: left">Университет Киева</a>
        <form class="d-flex" action="/pages/admin/adminkaServlet" method="get" style="text-align: right">
            <button class="btn btn-outline-success" type="submit" title="Посмотреть абитуриент">Факультеты</button>
        </form>
    </div>
</nav>
<h1 style="text-align:center">Абитуриенты</h1>
<div class="container">
    <div class="dropdown">
        <button class="dropbtn">Сортировать</button>
        <div class="dropdown-content">
            <a href="/pages/admin/adminkaEntrantsServlet">Все абитуриенты</a>
            <a href="/pages/admin/adminkaEntrantsServlet?sort=activeEntrant">Активные абитуриенты</a>
            <a href="/pages/admin/adminkaEntrantsServlet?sort=blockedEntrant">Заблокированые абитуриенты</a>
        </div>
    </div>
    <br><br>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">ФИО</th>
            <th scope="col">Email</th>
            <th scope="col">Статус</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${entrants}" var="entrant">
            <tr>
                <td>${entrant.getSurname()} ${entrant.getFirstName()} ${entrant.getMiddleName()}</td>
                <td>${entrant.getEmail()}</td>
                <td>${entrant.getStatus()}</td>
                <td><a href="/pages/admin/infoAboutEntrantServlet?id=${entrant.getId()}">Узнать больше</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br><br>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:forEach var="i" begin="1" end="${pages}">
                <li class="page-item">
                    <a class="page-link" href="/pages/admin/adminkaEntrantsServlet?sort=${sessionScope.get("sort")}&&page=<c:out value="${i - 1}"/>"><c:out value="${i}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>
    <p style="text-align: center">${message}</p>
    ${pageScope.remove("message")}
    <br>
    <form action="/exitServlet" method="post" style="text-align: center">
        <button class="btn btn-outline-success" type="submit">Выйти</button>
    </form>
</div>
<br><br><br>
</body>
</html>
