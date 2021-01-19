<%-- 
    Document   : viewCart.jsp
    Created on : Jan 8, 2021, 2:53:41 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
        <style>
            .modal {
                display: none;
                position: fixed; 
                z-index: 1; 
                padding-top: 100px;
                padding-left: 40%;
                padding-right: 40%;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%; 
                overflow: auto;
                background-color: rgb(0,0,0); 
                background-color: rgba(0,0,0,0.4); 
            }
            .modal-content {
                background-color: #fefefe;
                margin: auto;
                padding: 20px;
                border: 1px solid #888;
                width: 60%;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <c:set var="isCreate" value="${requestScope.REMOVE_SUCCESS}"/>
        <c:if test="${isCreate eq 'true'}">
            <script>
                alert("Remove Successfully!");
            </script>
        </c:if>
        <div class="container" style="background-color: white;padding: 20px;min-height: 400px;">
            <h1>Cart</h1>
            <c:if test="${not empty sessionScope}">
                <c:set var="cart" value="${sessionScope.CUSTCART}"/>
                <c:if test="${empty cart}">
                    <h2 class="text-center">
                        No product in cart
                    </h2>
                </c:if>
                <c:if test="${not empty cart}">
                    <c:set var="products" value="${cart.products}"/>
                    <c:if test="${empty products}">
                        <h2 class="text-center">
                            No item in cart
                        </h2>
                    </c:if>
                    <c:if test="${not empty products}">
                        <c:set var="totalPrice" value="0"/>
                        <form action="DispatchServlet">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col" style="width: 262px;">Product</th>
                                        <th scope="col"></th>
                                        <th scope="col">Unit Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Total</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <c:forEach var="product" items="${products}" varStatus="counter">
                                    <tbody>
                                        <tr>
                                            <td style="width: 262px;">
                                                <img src="images/${product.key.image}"  alt="${product.key.image}" style="width: 100%; height: 160px">
                                            </td>
                                            <td>
                                                <h4>${product.key.productName}</h4>
                                                <input type="hidden" name="productID" value="${product.key.productID}" />
                                            </td>
                                            <td>
                                                <h5>${product.key.price}</h5>
                                            </td>
                                            <td>
                                                <div class="input-group"> 
                                                    <c:url var="minus" value="DispatchServlet">
                                                        <c:param name="btnAction" value="Minus"/>
                                                        <c:param name="productID" value="${product.key.productID}"/>
                                                        <c:param name="minus" value="true"/>
                                                    </c:url>
                                                    <a class="btn btn-number" href="${minus}"> 
                                                        <i class="fas fa-minus"></i>
                                                    </a> 

                                                    <input type="text" 
                                                           name="txtAmount" 
                                                           value="${product.value}" 
                                                           id="number${product.key.productID}" 
                                                           class="btn btn-number" 
                                                           style="width: 50px;border: 1px solid #bdc3c7"
                                                           readonly>
                                                    <c:url var="plus" value="DispatchServlet">
                                                        <c:param name="btnAction" value="Plus"/>
                                                        <c:param name="productID" value="${product.key.productID}"/>
                                                        <c:param name="txtAmount" value="1"/>
                                                        <c:param name="plus" value="true"/>
                                                    </c:url>
                                                    <a class="btn btn-number" href="${plus}">
                                                        <i class="fas fa-plus"></i>
                                                    </a> 
                                                </div> 
                                            </td>
                                            <td>
                                                <p id="total${product.key.productID}">${product.value * product.key.price}</p>
                                                <c:set var="totalPrice" value="${totalPrice + product.value * product.key.price}"/>
                                            </td>
                                            <td>

                                                <a class="btn btn-danger" style="margin-top: 65%;margin-left: 55%;" 
                                                   id="btn${product.key.productID}"
                                                   onclick="remove(${product.key.productID})">Remove</a>

                                                <div id="modal${product.key.productID}" class="modal">
                                                    <div class="modal-content">
                                                        <h5 class="modal-title">Modal title${product.key.productID}</h5>
                                                        <p>Do you want delete this product </p><br/>
                                                        <div class="modal-footer">
                                                            <c:url var="remove" value="DispatchServlet">
                                                                <c:param name="btnAction" value="Remove"/>
                                                                <c:param name="productID" value="${product.key.productID}"/>
                                                            </c:url>
                                                            <a class="btn btn-primary" href="${remove}">Remove</a>
                                                            <span class="btn btn-danger" id="close" 
                                                                  onclick="document.getElementById('modal' + ${product.key.productID}).style.display = 'none'">Cancel</span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <script>
                                                    function remove(id) {
                                                        document.getElementById('modal' + id).style.display = "block";
                                                    }
                                                </script>
                                            </td>
                                        </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td style="width: 262px;"></td>
                                        <td style="width: 209px;"></td>
                                        <td style="width: 146px;"></td>
                                        <td style="width: 256px;">Total Price</td>
                                        <td>
                                            <input type="number" 
                                                   name="txtTotalPrice" 
                                                   value="${totalPrice}" 
                                                   readonly="readonly" 
                                                   id="totalPrice"
                                                   style="border: none;"/>
                                        </td>
                                        <td> 
                                            <input type="submit" value="Checkout" name="btnAction" class="btn btn-success"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>                    
                    </c:if>
                </c:if>
            </c:if>
        </div>
        <script>
            function up(max, id, price) {
                var number = document.getElementById("number" + id).value;
                if (number < max) {
                    number++;
                }
                var total = number * price;
                document.getElementById("number" + id).value = number;
                document.getElementById("total" + id).innerHTML = total;
                var totalPrice = document.getElementById("totalPrice").value;
                totalPrice = parseFloat(totalPrice);
                totalPrice = totalPrice + price;
                document.getElementById("totalPrice").value = totalPrice;

            }

            function down(id, price) {
                var number = document.getElementById("number" + id).value;
                if (number > 1) {
                    number--;
                }
                var total = number * price;
                document.getElementById("number" + id).value = number;
                document.getElementById("total" + id).innerHTML = total;
                var totalPrice = document.getElementById("totalPrice").value;
                totalPrice = parseFloat(totalPrice);
                totalPrice = totalPrice - price;
                document.getElementById("totalPrice").value = totalPrice;
            }
        </script>
    </body>
</html>
