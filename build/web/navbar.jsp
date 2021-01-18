<%-- 
    Document   : Navbar
    Created on : Jan 9, 2021, 4:27:21 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nav Bar</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
        <meta name="google-signin-client_id" 
              content="406424637817-efgsuneqpjvitod9h0b4b056s1h7ikg1.apps.googleusercontent.com">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg mb20" style="background: white;">
            <a class="navbar-brand" href="PageServlet">Hana Shop</a>
            <div class="navbar-nav ml-auto" >
                <c:set var="role" value="${sessionScope.ISADMIN}"/>
                <c:url var="btnSearch" value="DispatchServlet">
                    <c:param name="btnAction" value="Search Button"/>
                </c:url>
                <a class="btn btn-primary" aria-current="page" href="${btnSearch}"><i class="fas fa-search"></i></a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="StartUpServlet">Home</a>
                    </li>
                    <c:if test="${role != 'true'}">
                        <li class="nav-item">
                            <c:url var="viewCart" value="DispatchServlet">
                                <c:param name="btnAction" value="View Cart"/>
                            </c:url>
                            <a class="nav-link" href="${viewCart}">
                                <i class="fas fa-shopping-cart"></i>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${empty sessionScope.FULLNAME}">
                        <li class="nav-item">
                            <c:url var="login" value="DispatchServlet">
                                <c:param name="btnAction" value="Login Button"/>
                            </c:url>
                            <a class="nav-link" href="${login}">Login</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.FULLNAME}">

                        <li class="nav-item">
                            <span class="nav-link">Welcome, ${sessionScope.FULLNAME}
                                <c:if test="${role == 'true'}">
                                    (Admin)
                                </c:if>
                            </span>
                        </li>
                        <li class="nav-item">
                            <c:url var="logout" value="DispatchServlet">
                                <c:param name="btnAction" value="Logout"/>
                            </c:url>
                            <a class="nav-link" href="${logout}">Logout</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </body>
</html>
