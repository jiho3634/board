<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>hello</title>
</head>
<body>
    <p>data(EL 문법) : ${myData}</p>
    <p>data(jstl : java 코드) : <%
        String getData = (String) request.getAttribute("myData");
        out.print(getData);
        %></p>
</body>
</html>
