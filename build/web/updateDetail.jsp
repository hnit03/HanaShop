<%-- 
    Document   : updateDetail
    Created on : Jan 14, 2021, 10:08:31 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Details</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
        <style >
            .form {
                width: 500px;
                left:50%;
                border: 2px solid #ccc;
                padding: 30px;
                border-radius: 15px;
                margin-left:auto;
                margin-right:auto;
            }
            .fontWeight{
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container" style="background-color: white;padding: 20px; width: 1280px;">
            <c:set var="role" value="${sessionScope.ISADMIN}"/>
            <c:if test="${role == 'true'}"> 
            <h1 class="text-center">Update Product</h1>
            <c:set var="product" value="${requestScope.PRODUCT}"/>
            <c:if test="${not empty product}">
                <div class="form">
                    <form  method="POST" action="DispatchServlet" 
                           class="form-group" enctype="multipart/form-data"
                           >                
                        <label class="form-label fontWeight">Product name:</label>
                        <input class="form-control" type="text" name="txtProductName" 
                               value="${product.productName}"
                               placeholder="Food Name"
                               maxlength="50" />
                        <br/>

                        <div class="text-center">
                            <img  src="images/${product.image}" id="image" style="width: 252px; height: 160px;"/>
                            <div id="displayImg" >

                            </div>
                        </div>
                        <label class="form-label fontWeight">Choose image:</label>
                        <input type="file" class="form-control-file border"  id="upload" onchange="getImage()"
                               name="file" value="${product.image}" accept="image/*"
                               />
                        <label class="form-label fontWeight">Description:</label>
                        <input class="form-control" type="text" name="txtDescription" 
                               value="${product.description}"
                               placeholder="Description"
                               maxlength="250" />
                        <label class="form-label fontWeight">Price:</label>
                        <input class="form-control" type="number" name="txtPrice" 
                               value="${product.price}"
                               placeholder="Price"  min="0" max="1000"
                               maxlength="50" />
                        <label class="form-label fontWeight">Category:</label>
                        <select class="form-control" name="cboCategory">
                            <c:set var="categoryList" value="${applicationScope.CATEGORY}"/>
                            <c:if test="${not empty categoryList}">
                                <c:forEach var="cate" items="${categoryList}" varStatus="counter">
                                    <option value="${cate.categoryName}"
                                            <c:if test="${cate.categoryName == product.cdto.categoryName}">
                                                selected
                                            </c:if>
                                            >${cate.categoryName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <label class="form-label fontWeight">Status:</label>
                        <c:set var="listStatus" value="${applicationScope.STATUS_LIST}"/>
                            <c:if test="${not empty listStatus}">
                                <select name="cboStatus" class="form-control">
                                    <c:if test="${not empty listStatus}">
                                        <c:set var="active" value="${product.status}"/>
                                        <c:if test="${active == 'true'}">
                                            <c:set var="active" value="Active"/>
                                        </c:if>
                                        <c:if test="${active == 'false'}">
                                            <c:set var="active" value="Inactive"/>
                                        </c:if>
                                        <c:forEach var="status" items="${listStatus}" varStatus="counter">
                                            <option value="${status}"
                                                    <c:if test="${status == active}">
                                                        selected
                                                    </c:if>
                                                    >${status}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </c:if>
                            <label class="form-label fontWeight">Quantity:</label>
                            <input class="form-control" type="number" name="txtQuantity" 
                                   value="${product.quantity}"
                                   placeholder="Quantity"
                                   maxlength="50" min="0" max="100"/>
                            <div class="text-center" style="margin-top: 20px;">
                                <input type="submit" value="Save" name="btnAction" class="btn btn-primary" style="width: 200px"/>
                                <input type="hidden" name="productID" value="${product.productID}" />
                                <input type="hidden" name="createDate" value="${product.createDate}" />
                                <input type="hidden" name="updateDetail" value="true" />
                                <c:url var="cancel" value="DispatchServlet">
                                    <c:param name="btnAction" value=""/>
                                </c:url>
                                <a href="${cancel}" class="btn btn-warning">Cancel</a>
                            </div>

                        </form>
                </div>
            </c:if>
            </c:if>
            <c:if test="${role == 'false' or empty role}">
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
        <script type="text/javascript">
            function getImage() {
                var fileSelected = document.getElementById("upload").files;
                if (fileSelected.length > 0) {
                    var fileToLoad = fileSelected[0];
                    var fileReader = new FileReader();
                    fileReader.onload = function (fileLoaderEvent) {
                        var srcData = fileLoaderEvent.target.result;
                        var newImage = document.createElement('img');
                        newImage.style.width = "252px";
                        newImage.style.height = "160px";
                        newImage.style.margin = "10px";
                        newImage.src = srcData;
                        document.getElementById("displayImg").innerHTML = newImage.outerHTML;
                        document.getElementById("image").style.display = "none";
                    }
                    fileReader.readAsDataURL(fileToLoad);
                }

            }
        </script>
    </body>
</html>
