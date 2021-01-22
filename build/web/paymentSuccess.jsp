<%-- 
    Document   : paymentWithPayPal
    Created on : Jan 17, 2021, 11:02:47 PM
    Author     : PC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container">
            <div class="text-center">
                <h1 >Payment Successfully!</h1>
                <c:url var="shopping" value="DispatchServlet">
                    <c:param name="btnAction" value=""/>
                </c:url>
                <a class="btn btn-success" href="${shopping}">Continue to shopping</a>
            </div>
        </div>

    </body>
</html>
