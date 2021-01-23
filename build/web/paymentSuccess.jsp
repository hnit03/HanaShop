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
        <title>Pay Successfully</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container" style="background-color: white;padding: 20px;">
            <c:set var="role" value="${sessionScope.ISADMIN}"/>
            <c:if test="${role == 'false'}"> 
                <c:set var="success" value="${requestScope.PAYMENT_SUCCESS}"/>
                <c:if test="${success == 'true'}">
                    <div class="text-center">
                        <h1 >Payment Successfully!</h1>
                        <c:url var="shopping" value="DispatchServlet">
                            <c:param name="btnAction" value=""/>
                        </c:url>
                        <a class="btn btn-success" href="${shopping}">Continue to shopping</a>
                    </div>
                </c:if>
                <c:if test="${empty success}">
                    <h1 class="text-center">OPPS, SOME THING WENT WRONG</h1>
                    <p class="text-center">Sorry, the page you were looking for does not exist!</p>
                    <c:url var="home" value="DispatchServlet">
                        <c:param name="btnAction" value=""/>
                    </c:url>
                    <div class="text-center">
                        <a href="${home}" class="btn btn-danger">Home Page</a>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${role == 'true'}">
                <h1 class="text-center">OPPS, ACCESS DENIED</h1>
                <p class="text-center">You don’t have permission to access on this page</p>
                <c:url var="home" value="DispatchServlet">
                    <c:param name="btnAction" value=""/>
                </c:url>
                <div class="text-center">
                    <a href="${home}" class="btn btn-danger">Back To Home Page</a>
                </div>
            </c:if>
        </div>

    </body>
</html>
