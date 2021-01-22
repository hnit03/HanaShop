<%-- 
    Document   : productHistory
    Created on : Jan 22, 2021, 1:51:35 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping History</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container" style="background-color: white;padding: 20px;">
            <c:set var="listHistory" value="${requestScope.LIST_PRODUCT_HISTORY}"/>
            <c:if test="${not empty listHistory}">
                <table class="table" >
                    <c:forEach var="product" items="${listHistory}" varStatus="counter">
                        <tbody>
                            <tr >
                                <td >
                                    <img src="images/${product.productDTO.image}" alt="${product.productDTO.image}" style="width: 150px; height: 150px;max-height: 100%">
                                </td>
                                <td style="width: 160px;">
                                    <h4>${product.productDTO.productID}</h4>
                                </td>
                                <td>
                                    <h5>${product.action}</h5>
                                </td>
                                <td>
                                    <h5>${product.date}</h5>
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
