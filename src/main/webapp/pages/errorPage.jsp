<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div style="text-align: center; margin-top: 5%">
    <h1>${sessionScope.get("bundle").getString("error.text.message1")}</h1>
    <p>${sessionScope.get("bundle").getString("error.text.message2")}</p>
    <p>${sessionScope.get("bundle").getString("error.text.message3")}</p>
</div>
</body>
</html>
