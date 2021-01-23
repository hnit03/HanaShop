<%-- 
    Document   : searchFood
    Created on : Jan 5, 2021, 5:58:00 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HanaShop</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <jsp:include page="searchForm.jsp"/>

        <div class="container">
            <c:set var="role" value="${sessionScope.ISADMIN}"/>
            <c:if test="${role == 'false' or empty role}"> 
                <c:if test="${not empty param.txtSearchValue ||
                              not empty param.cboCategory && param.cboCategory != '--Select Category--'|| 
                              not empty param.txtPriceMin || not empty param.txtPriceMax}">
                    <c:set var="productList" value="${requestScope.PRODUCTSEARCH}"/>
                    <c:if test="${not empty productList}">
                        <div class="row">
                            <c:forEach var="dto" items="${productList}" varStatus="counter">
                                <div class="col-lg-3 card mb20">
                                    <img src="images/${dto.image}" class="card-img-top mt10" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">${dto.productName}</h5>
                                        <p class="card-text">
                                            <span style="font-weight: bold;">Price:</span>  $${dto.price}
                                        </p>
                                        <c:url var="moreButton" value="DispatchServlet">
                                            <c:param name="btnAction" value="More"/>
                                            <c:param name="productID" value="${dto.productID}"/>
                                        </c:url>
                                        <a href="${moreButton}" class="btn btn-warning form-control">View Details</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
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
                                        <input type="hidden" name="pageView" value="searchProduct.jsp" />
                                        <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                                        <input type="hidden" name="cboCategory" value="${param.cboCategory}" />
                                        <input type="hidden" name="txtPriceMin" value="${param.txtPriceMin}" />
                                        <input type="hidden" name="txtPriceMax" value="${param.txtPriceMax}" />
                                    </ul>
                                </form>
                            </c:if>
                        </nav>
                    </c:if>
                    <c:if test="${empty productList}">
                        <h2 class="text-center">Not found</h2>
                    </c:if>
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
