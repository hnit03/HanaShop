<%-- 
    Document   : login
    Created on : Jan 4, 2021, 6:35:30 PM
    Author     : PC
--%>

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
        </style>
        <meta name="google-signin-client_id" 
              content="406424637817-efgsuneqpjvitod9h0b4b056s1h7ikg1.apps.googleusercontent.com">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container" style="background-color: white;padding: 20px;height: 500px;">
            <h1 class="text-center">Login</h1>
            <div class="form">
                <form action="DispatchServlet" method="POST">
                    <div class="form-group">
                        <label>UserID</label>
                        <input type="text" name="txtUserID" value="" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="txtPassword" value="" class="form-control"/>
                    </div>
                    <input type="submit" value="Login" name="btnAction" class="btn btn-primary form-control"/><br/><br/>
                    <input type="reset" value="Reset" class="btn btn-danger form-control"/>
                </form>
                <br>
                <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/J3LP0013/LoginWithGoogleServlet&response_type=code
                   &client_id=406424637817-efgsuneqpjvitod9h0b4b056s1h7ikg1.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
<!--                <div class="g-signin2" data-onsuccess="onSuccess"></div>-->
                <br>
<!--                <script>
                    function onSignIn(googleUser) {

                        var profile = googleUser.getBasicProfile();
                        var email = profile.getEmail();
                        var name = profile.getName();
                        var id = profile.getId();

                        var xhr = new XMLHttpRequest();
                        xhr.open('POST', 'http://localhost:8084/J3LP0013/LoginServlet');
                        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                        var params = 'txtUserID=' + email + '&name=' + name + '&txtPassword=' + id;
                        xhr.send(params);
                        xhr.onload = function () {
                            window.location.replace('StartUpServlet');
                        };

                    }
                    function onLoad() {
                        gapi.load('auth2,signin2', function () {
                            var auth2 = gapi.auth2.init();
                            auth2.then(function () {
                                // Current values
                                var isSignedIn = auth2.isSignedIn.get();
                                console.log(isSignedIn);
                                if (!isSignedIn) {
   
                                    gapi.signin2.render('google-signin-button', {
                                        'onsuccess': 'onSignIn'
                                    });
                                }
                            });
                        });
                    }
                </script>-->
                <script src="https://apis.google.com/js/platform.js" async defer></script>
            </div>
        </div>
    </body>
</html>
