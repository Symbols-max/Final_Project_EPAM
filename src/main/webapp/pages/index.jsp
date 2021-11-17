<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>">
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
        <form class="d-flex" action="/firstServlet" method="get" style="text-align: right">
            <button class="btn btn-outline-primary" type="submit" name="btn" value="Sign in">${sessionScope.get("bundle").getString("index.btn.signIn")}</button>
            <button class="btn btn-outline-success" type="submit" name="btn" value="Sign Up">${sessionScope.get("bundle").getString("index.btn.signUp")}</button>
        </form>
    </div>
</nav>
<h1 style="text-align:center">${sessionScope.get("bundle").getString("index.text.faculties")}</h1>
<div class="container">
    <div class="dropdown">
        <button class="dropbtn">${sessionScope.get("bundle").getString("index.btn.sort")}</button>
        <div class="dropdown-content">
            <a href="/mainServlet?sort=sortByName?">${sessionScope.get("bundle").getString("index.btn.sort.sortByName")}</a>
            <a href="/mainServlet?sort=sortByAllPlaces">${sessionScope.get("bundle").getString("index.btn.sort.sortByAllPlaces")}</a>
            <a href="/mainServlet?sort=sortByBudgetPlaces">${sessionScope.get("bundle").getString("index.btn.sort.sortByFundedPlaces")}</a>
        </div>
    </div>
    <br><br>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">№</th>
            <th scope="col">${sessionScope.get("bundle").getString("index.table.nav.name")}</th>
            <th scope="col">${sessionScope.get("bundle").getString("index.table.nav.numberPlaces")}</th>
            <th scope="col">${sessionScope.get("bundle").getString("index.table.nav.numberFundedPlaces")}</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${faculties}" var="faculty">
        <tr>
            <td>${faculty.getId()}</td>
            <td>${faculty.getNameFaculty()}</td>
            <td>${faculty.getAllPlaces()}</td>
            <td>${faculty.getFundedPlaces()}</td>
            <td><a href="/facultyInfoServlet?id=${faculty.getId()}">${sessionScope.get("bundle").getString("index.table.href.moreInfo")}</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <br><br>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:forEach var="i" begin="1" end="${pages}">
                <li class="page-item"><a class="page-link" href="/mainServlet?sort=${sessionScope.get("sort")}&&page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
            </c:forEach>
        </ul>
    </nav>

    <br><p style="text-align: center">${sessionScope.get("message_index")}</p>
    ${sessionScope.remove("message_index")}

</div>
<footer>
    <div style="text-align: right;margin-right: 3%">
        <a href="/pages/changeLanguageServlet?language=ru" style="padding-right: 2%;text-decoration: none">РУС</a>
        <a href="/pages/changeLanguageServlet?language=en" style="padding-right: 2%;text-decoration: none">ENG</a>
    </div>
</footer>
</body>
</html>
