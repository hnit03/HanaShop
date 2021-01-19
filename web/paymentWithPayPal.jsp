<%-- 
    Document   : paymentWithPayPal
    Created on : Jan 17, 2021, 11:02:47 PM
    Author     : PC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="https://www.paypal.com/sdk/js?client-id=AQw86f13UkME0dpCSBiLcTRc_c2-hEhhGgA1QusoaHS0WWLU0XsJFuR_O1b6HBk5rMfhz63EnV3qNGlC"></script>

        <div class="container">
            <jsp:include page="navbar.jsp"/>

            <div id="paypal-button-container"></div>
            <c:set var="billID" value="${sessionScope.BILLID}" />
            <c:set var="totalPayment" value="${sessionScope.TOTAL_PRICE}" />
        </div>


        <script>
            paypal.Buttons(
                    {
                        createOrder: function (data, actions) {
                            // This function sets up the details of the transaction, including the amount and line item details.
                            return actions.order.create({
                                purchase_units: [{
                                        amount: {
                                            value: '${totalPayment}'
                                        }
                                    }]
                            });
                        }
                        ,
                        onApprove: function (data, actions) {
                            return actions.order.capture().then(function () {
                                actions.redirect('http://localhost:8084/J3LP0013/PaymentWithPaypalServlet?billID=${billID}');
                            }
                            )
                        }
                    }
            ).render('#paypal-button-container');
        </script>
    </body>
</html>
