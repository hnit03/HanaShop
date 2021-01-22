<%-- 
    Document   : searchHistory
    Created on : Jan 21, 2021, 11:00:40 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Shopping History</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div style="background-color: white;
             padding: 20px 200px 20px 200px; margin-bottom: 20px;margin-top: -20px;">
            <form action="DispatchServlet" method="GET" class="d-flex">
                <label class="nav-link" style="font-weight: bold">Name:</label>
                <input type="text" class="form-control" placeholder="Name" name="txtSearchName" value="${param.txtSearchValue}" style="width: 50%;"/>
                <label class="nav-link" style="font-weight: bold">Date:</label>
                <input type="date" name="date" value="${param.date}" class="form-control" style="width: 50%;">
                <span class="input-group-btn">
                    <input class="btn btn-primary" type="submit" name="btnAction" value="Search History"/>
                </span>
            </form>
        </div>
        <div class="container" style="background-color: white;padding: 20px;">
            <c:set var="listHistory" value="${requestScope.SEARCH_HISTORY}"/>
            <c:if test="${not empty listHistory}">
                <table class="table" >
                    <c:forEach var="product" items="${listHistory}" varStatus="counter">
                        <tbody>
                            <tr >
                                <td style="width: 160px;">
                                    <img src="images/${product.key.image}" alt="${product.key.image}" style="width: 150px; height: 150px;max-height: 100%">
                                </td>
                                <td >
                                    <h4>${product.key.productName}</h4>
                                </td>
                                <td style="width: 160px;">
                                    <h4>${product.key.price}</h4>
                                </td>
                                <td style="width: 160px;">
                                    <h4>${product.key.cdto.categoryName}</h4>
                                </td>
                                <td>
                                    <h5>${product.value}</h5>
                                </td>
                            </tr>
                        </tbody>

                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty listHistory}">
                <div class="text-center">
                    <h2>No history</h2>
                    <p>Please shop now</p>
                    <c:url var="shopping" value="DispatchServlet">
                        <c:param name="btnAction" value=""/>
                    </c:url>
                    <a href="${shopping}" class="btn btn-warning">Shopping</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
