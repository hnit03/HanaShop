<%-- 
    Document   : paypalWithPaypal
    Created on : Jan 22, 2021, 10:49:30 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment With Paypal</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
        <style>
            .wrap {
                width: 360px;
                left:50%;
                border: 2px solid #ccc;
                padding: 30px;
                border-radius: 15px;
                margin-left:auto;
                margin-right:auto;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container" style="background-color: white;padding: 20px;">
            <c:set var="role" value="${sessionScope.ISADMIN}"/>
            <c:if test="${role == 'false'}"> 
                <c:set var="totalPrice" value="${sessionScope.TOTAL_PRICE_PAYMENT_SS}"/>
                <c:if test="${not empty totalPrice}">
                    <div class="wrap">

                        <img src="https://cdn.iconscout.com/icon/free/png-512/paypal-5-226456.png" style="width: 100px;height: 100px;">
                        <p>Your order amount is <span style="font-weight: bold">${totalPrice} VND</span> . 
                            Please click <span style="font-weight: bold">BUY NOW</span> to pay</p>
                        <div class="text-center">
                            <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
                                <input type="hidden" name="business" value="sb-tdrwn4756671@business.example.com">
                                <input type="hidden" name="cmd" value="_xclick">
                                <input type="hidden" name="item_name" value="HoaDonMuaHang">
                                <input type="hidden" name="amount" value="${totalPrice}">
                                <input type="hidden" name="currency_code" value="USD">
                                <input type="hidden" name="return" value="http://localhost:8084/J3LP0013/PaymentWithPaypalServlet">
                                <input type="hidden" name="cancel_return" value="http://localhost:8084/J3LP0013/checkout.jsp">

                                <input type="image" name="submit" value="Pay With Paypal" 
                                       src="https://www.sandbox.paypal.com/en_US/i/btn/btn_buynowCC_LG.gif" 
                                       border="0" 
                                       alt="PayPal - The safer, easier way to pay online!">
                                <img alt="" src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">

                            </form>
                            <c:url var="cancel" value="DispatchServlet">
                                <c:param name="btnAction" value="Checkout"/>
                            </c:url>
                            <a class="btn btn-danger" href="${cancel}">Cancel</a>
                        </div>



                    </div>
                </c:if>
                <c:if test="${ empty totalPrice}">
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
