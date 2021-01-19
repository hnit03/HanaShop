<%-- 
    Document   : login
    Created on : Jan 4, 2021, 6:35:30 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <style >
            .form {
                width: 360px;
                left:50%;
                border: 2px solid #ccc;
                padding: 30px;
                background: #f7f7f7;
                border-radius: 15px;
                margin-left:auto;
                margin-right:auto;
            }
            .loginBtn {
                box-sizing: border-box;
                position: relative;
                width: 13em;
                margin: 0.2em;
                padding: 0 0px 0 10px;
                border: none;
                text-align: left;
                line-height: 34px;
                white-space: nowrap;
                border-radius: 0.2em;
                font-size: 16px;
                color: #FFF;
            }
            .loginBtn:focus {
                outline: none;
            }
            .loginBtn:active {
                box-shadow: inset 0 0 0 32px rgba(0,0,0,0.1);
            }
            .loginBtn--google {
                font-family: "Roboto", Roboto, arial, sans-serif;
                background: #DD4B39;
            }
            .loginBtn--google:hover,
            .loginBtn--google:focus {
                background: #E74B37;
            }

        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container" style="background-color: white;padding: 20px;height: 500px;">
            <h1 class="text-center">Login</h1>
            <div class="form">
                <form action="DispatchServlet" method="POST">
                    <div class="form-group">
                        <label>UserID</label>
                        <input type="text" name="txtUserID" value="${param.txtUserID}" class="form-control" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="txtPassword" value="${param.txtPassword}" class="form-control"/>
                    </div>
                    <c:set var="error" value="${requestScope.LOGIN_FAILED}"/>
                    <c:if test="${not empty error}">
                        <div class="text-center">
                            <p style="color: red;font-weight: bold">${error}</p>
                        </div>
                    </c:if>
                    <input type="submit" value="Login" name="btnAction" class="btn btn-primary form-control"/><br/><br/>
                    <input type="reset" value="Reset" class="btn btn-danger form-control"/>
                </form>
                <br>
                <div class="text-center">
                    <button class="loginBtn loginBtn--google">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/J3LP0013/LoginWithGoogleServlet&response_type=code
                           &client_id=406424637817-efgsuneqpjvitod9h0b4b056s1h7ikg1.apps.googleusercontent.com&approval_prompt=force" style="color: white;text-decoration: none">
                            <i class="fab fa-google mr-2"></i> Sign in with Google
                        </a>
                    </button>
                </div>
                <script src="https://apis.google.com/js/platform.js" async defer></script>
            </div>
        </div>
    </body>
</html>
