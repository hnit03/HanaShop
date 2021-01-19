<%-- 
    Document   : index
    Created on : Jan 5, 2021, 3:34:21 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HanaShop</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <c:set var="isAdded" value="${requestScope.ADD_SUCCESS}"/>
        <c:if test="${isAdded eq 'true'}">
            <script>
                alert("Add Successfully!");
            </script>
        </c:if>
        <!--Food-->
        <div class="container">
            <c:set var="productList" value="${requestScope.ALLPRODUCT}"/>
            <c:if test="${not empty productList}">
                <div class="row">
                    <c:forEach var="dto" items="${productList}" varStatus="counter">
                        <c:url var="moreButton" value="DispatchServlet">
                            <c:param name="btnAction" value="More"/>
                            <c:param name="productID" value="${dto.productID}"/>
                        </c:url>
                        <div class="col-lg-3 card mb20">
                            <img src="images/${dto.image}" class="card-img-top mt10" alt="${dto.image}" style="width: 253px; height: 160px">
                            <div class="card-body">
                                <h5 class="card-title">${dto.productName}</h5>
                                <p class="card-text">
                                    <span style="font-weight: bold;">Price:</span> ${dto.price} VND
                                </p>
                                <a href="${moreButton}" class="btn btn-warning form-control">View Details</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!--paging-->
                <nav aria-label="Page navigation example">
                    <c:set var="pageNo" value="${requestScope.PAGENO}"/>                   
                    <c:set var="pageMaxUser" value="${requestScope.PAGE_MAX_USER}"/>
                    <c:if test="${pageMaxUser > 1}">
                        <form action="DispatchServlet">
                            <ul class="pagination justify-content-end">
                                <c:if test="${pageNo <= 1}">
                                    <li class="page-item disabled">
                                        <input type="submit" value="Previous" name="btnAction" class="page-link"/>
                                    </li>   
                                </c:if>
                                <c:if test="${pageNo > 1}">
                                    <li class="page-item">
                                        <input type="submit" value="Previous" name="btnAction" class="page-link"/>
                                    </li>   
                                </c:if>
                                <c:if test="${pageNo < pageMaxUser}">
                                    <li class="page-item">
                                        <input type="submit" value="Next" name="btnAction" class="page-link"/>
                                    </li>   
                                </c:if>   
                                <c:if test="${pageNo == pageMaxUser}">
                                    <li class="page-item disabled">
                                        <input type="submit" value="Next" name="btnAction" class="page-link"/>
                                    </li>   
                                </c:if>
                                <input type="hidden" name="pageNo" value="${pageNo}" />
                                <input type="hidden" name="pageView" value="index.jsp" />
                            </ul>
                        </form>
                    </c:if>
                </nav>
            </c:if>
        </div>
    </body>
</html>
