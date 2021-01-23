<%-- 
    Document   : productDetail
    Created on : Jan 8, 2021, 9:15:44 AM
    Author     : PC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <c:if test="${not empty requestScope.MAXIMUM_AMOUNT}">
            <script>
                alert('${requestScope.MAXIMUM_AMOUNT}')
            </script>
        </c:if>
        <div class="container" style="background-color: white;padding: 20px;">
            <c:set var="role" value="${sessionScope.ISADMIN}"/>
            <c:if test="${role == 'false' or empty role}"> 
                <c:set var="product" value="${requestScope.PRODUCTDETAIL}"/>
                <c:if test="${not empty product}">
                    <div class="row">
                        <div class="col-lg-6">
                            <img src="images/${product.image}" alt="${product.image}" style="width: 560px;height: 366px;">
                        </div>
                        <div class="col-lg-5" style="margin-left: 40px;">
                            <h3>${product.productName}</h3>
                            <p>Description: ${product.description}</p>
                            <p>Category: ${product.cdto.categoryName}</p>
                            <p>Price: ${product.price}</p>
                            <p>Remaining amount: ${product.quantity}</p>
                            <c:set var="number" value="${1}"/>
                            <form action="DispatchServlet">
                                <c:if test="${requestScope.QUANTITY != '0'}">  
                                    <div class="input-group"> 
                                        <div style="width: 40px;height: 38px;">
                                            <span class="btn btn-number" onclick="down()" id="minus"> 
                                                <i class="fas fa-minus"></i>
                                            </span> 
                                        </div>

                                        <input type="text" 
                                               name="txtAmount" 
                                               value="1" 
                                               id="number" 
                                               class="btn btn-number" 
                                               style="width: 50px;border: 1px solid #bdc3c7"
                                               readonly>

                                        <span class="btn btn-number" onclick="up(${product.quantity})" id="plus">
                                            <i class="fas fa-plus"></i>
                                        </span> 
                                    </div> 
                                </c:if>
                                <br>
                                <input type="hidden" name="productID" value="${product.productID}"/>
                                <c:if test="${requestScope.QUANTITY <= '0'}">
                                    <a class="btn btn-danger" style="width: 250px;">Out Of Stock</a>
                                </c:if>
                                <c:if test="${requestScope.QUANTITY != '0'}">
                                    <input type="submit" value="Add To Cart" name="btnAction" class="btn btn-primary" style="width: 250px;"/>
                                </c:if>
                            </form>
                        </div>
                    </div>
                    <br/>
                    <c:set var="productRe" value="${requestScope.PRODUCT_RECOMMENDATION}"/>
                    <c:if test="${not empty productRe}">
                        <c:url var="moreButton" value="DispatchServlet">
                            <c:param name="btnAction" value="More"/>
                            <c:param name="productID" value="${productRe.productID}"/>
                        </c:url>
                        <div class="row" style="margin: 20px;">
                            <div class="col-lg-7" style="border: 2px dotted #ecf0f1">
                                <h4>Product Recommendation</h4>
                                <div class="row">
                                    <div class="col-lg-4">
                                        <img src="images/${productRe.image}" style="width: 200px; height: 160px;margin: 10px;"/>
                                    </div>
                                    <div class="col-lg-4" style="margin: 20px">
                                        <h5>${productRe.productName}</h5>
                                        <p><span style="font-weight: bold">Price:</span> ${productRe.price}</p>
                                    </div>
                                    <div class="col-lg-3" style="margin-top: 80px;">
                                        <a class="btn btn-danger" href="${moreButton}">View Details</a>
                                    </div>
                                </div>
                            </div>
                        </div>
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
        <script>
            var number = document.getElementById("number").value;
            if (number == 1) {
                document.getElementById("minus").style.display = "none";
            }
            function up(max) {
                if (number < max) {
                    number++;
                    document.getElementById("minus").style.display = "block";
                }
                if (number == max) {
                    document.getElementById("plus").style.display = "none";
                }
                document.getElementById("number").value = number;
            }

            function down() {

                if (number > 0) {
                    number--;
                    document.getElementById("plus").style.display = "block";
                }
                document.getElementById("number").value = number;
            }

        </script>
    </body>
</html>
