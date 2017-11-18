<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title><%=request.getServletContext().getServerInfo() %></title>
    <link href="favicon.ico" rel="icon" type="image/x-icon"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <%--<link href="tomcat.css" rel="stylesheet" type="text/css" />--%>
</head>

<body>

<ul>
<%--suppress JspAbsolutePathInspection --%>
<%--suppress HtmlUnknownTarget --%>
    <li><a href="/manager/html">admin`ка</a></li>
    <li><a href="${pageContext.request.contextPath}/profile">Профиль</a></li>
</ul>

</body>

</html>
