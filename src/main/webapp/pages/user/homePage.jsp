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
    <title>HomePage</title>

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
        <a href="/pages/user/profileServlet" title="Посмотреть профиль" style="padding-right: 3%">
            <svg width="36px" height="36px" viewBox="0 0 16 16" class="bi bi-person" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M13 14s1 0 1-1-1-4-6-4-6 3-6 4 1 1 1 1h10zm-9.995-.944v-.002.002zM3.022 13h9.956a.274.274 0 0 0 .014-.002l.008-.002c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664a1.05 1.05 0 0 0 .022.004zm9.974.056v-.002.002zM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
            </svg>
        </a>
    </div>
</nav>
<h1 style="text-align:center">${sessionScope.get("bundle").getString("homePage.text.faculties")}</h1>
<div class="container">
    <div class="dropdown">
        <button class="dropbtn">${sessionScope.get("bundle").getString("homePage.btn.sort")}</button>
        <div class="dropdown-content">
            <a href="/pages/user/homePageServlet?sort=sortByName">${sessionScope.get("bundle").getString("homePage.btn.sort.sortByName")}</a>
            <a href="/pages/user/homePageServlet?sort=sortByAllPlaces">${sessionScope.get("bundle").getString("homePage.btn.sort.sortByAllPlaces")}</a>
            <a href="/pages/user/homePageServlet?sort=sortByBudgetPlaces">${sessionScope.get("bundle").getString("homePage.btn.sort.sortByFundedPlaces")}</a>
        </div>
    </div>
    <br><br>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">№</th>
            <th scope="col">${sessionScope.get("bundle").getString("homePage.table.nav.name")}</th>
            <th scope="col">${sessionScope.get("bundle").getString("homePage.table.nav.numberPlaces")}</th>
            <th scope="col">${sessionScope.get("bundle").getString("homePage.table.nav.numberFundedPlaces")}</th>
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
                <td><a href="/facultyInfoServlet?id=${faculty.getId()}">${sessionScope.get("bundle").getString("homePage.table.href.moreInfo")}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br><br>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:forEach var="i" begin="1" end="${pages}">
                <li class="page-item">
                    <a class="page-link" href="/pages/user/homePageServletServlet?sort=${sessionScope.get("sort")}&&page=<c:out value="${i - 1}"/>"><c:out value="${i}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>

    <br><p style="text-align: center">${sessionScope.get("message_home")}</p>
    ${sessionScope.remove("message_home")}
</div>
</body>
</html>
