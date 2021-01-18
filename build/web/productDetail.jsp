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

        <div class="container" style="background-color: white;padding: 20px;">
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
                            <div class="input-group"> 
                                <span class="btn btn-number" onclick="down()"> 
                                    <i class="fas fa-minus"></i>
                                </span> 

                                <input type="text" 
                                       name="txtAmount" 
                                       value="1" 
                                       id="number" 
                                       class="btn btn-number" 
                                       style="width: 50px;border: 1px solid #bdc3c7"
                                       readonly>

                                <span class="btn btn-number" onclick="up(${product.quantity})">
                                    <i class="fas fa-plus"></i>
                                </span> 
                            </div> 
                            <br>
                            <input type="hidden" name="productID" value="${product.productID}"/>
                            <input type="hidden" name="txtAmount" value="${number}">
                            <input type="submit" value="Order" name="btnAction" class="btn btn-danger" style="width: 250px;"/>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>   
        <script>
            var number = document.getElementById("number").value;
            function up(max) {
                if (number < max) {
                    number++;
                }
                document.getElementById("number").value = number;
            }

            function down() {

                if (number > 0) {
                    number--;
                }
                document.getElementById("number").value = number;
            }

        </script>
    </body>
</html>
