<%-- 
    Document   : information
    Created on : Jan 17, 2021, 11:16:39 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Information</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container" style="background-color: white;padding: 20px;min-height: 400px;">
            
            <c:set var="cart" value="${sessionScope.CUSTCART}"/>
            <form action="DispatchServlet">
                <div class="row">
                    <div class="col-lg-9">
                        <c:if test="${not empty cart}">
                            <c:set var="products" value="${cart.products}"/>
                            <c:if test="${empty products}">
                                <h2 class="text-center">
                                    No item in cart
                                </h2>
                            </c:if>
                            <c:if test="${not empty products}">
                                <c:set var="totalPrice" value="0"/>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col" style="width: 262px;">Product</th>
                                            <th scope="col"></th>
                                            <th scope="col">Unit Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Total</th>
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

                                                    <input type="text" 
                                                           name="txtAmount" 
                                                           value="${product.value}" 
                                                           id="number${product.key.productID}" 
                                                           class="btn btn-number" 
                                                           style="width: 50px;border: 1px solid #bdc3c7"
                                                           readonly>

                                                </td>
                                                <td>
                                                    <p id="total${product.key.productID}">${product.value * product.key.price}</p>
                                                    <c:set var="totalPrice" value="${totalPrice + product.value * product.key.price}"/>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </c:forEach>
                                </table>                 
                            </c:if>
                        </c:if>
                    </div>
                    <div class="col-lg-3">
                        <h2 class="text-center">Information</h2>
                        <c:set var="user" value="${sessionScope.USER_DETAILS}"/>
                        <div class="form-group">
                            <label>Fullname</label>
                            <input type="text" name="txtFullnameInfo" value="${user.fullname}" class="form-control" placeholder="(6 - 50 chars)"/>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" name="txtPhone" value="${user.phone}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <input type="text" name="txtAddress" value="${user.address}" class="form-control" />
                        </div>

                    </div>
                </div>
                <div class="row">
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
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <h5>Payment methods:</h5>

                    </div>
                    <div class="col-lg-4">
                        <select class="form-control" name="cboMethod">
                            <option value="Cash">Cash</option>
                            <option value="Momo">Momo</option>
                        </select>

                    </div>
                    <div class="col-lg-4">
                        <a href="paymentWithPayPal.jsp">Paypal</a>
                        <input type="submit" value="Pay" name="btnAction" class="btn btn-primary form-control"/><br/>
                        <a href="checkout" class="btn btn-warning form-control">Cancel Payment</a>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
