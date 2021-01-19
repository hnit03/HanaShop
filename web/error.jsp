<%-- 
    Document   : error
    Created on : Jan 19, 2021, 9:26:37 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
        
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <h1 class="text-center">OPPS, SOME THING WENT WRONG</h1>
            <p class="text-center">Sorry, the page you were looking for does not exist!</p>
            <c:url var="home" value="DispatchServlet">
                <c:param name="btnAction" value=""/>
            </c:url>
            <div class="text-center">
                <a href="${home}" class="btn btn-danger">Home Page</a>
            </div>
        </div>
    </body>
</html>
