<%-- 
    Document   : createNewProduct
    Created on : Jan 10, 2021, 10:46:51 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Product</title>
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
            <h1 class="text-center">Create New Product</h1>
            <div class="form">
                <form  method="POST" action="DispatchServlet" 
                       class="form-group" enctype="multipart/form-data"
                       >             
                    <label class="form-label fontWeight">Product name:</label>
                    <input class="form-control" type="text" name="txtProductName" 
                           value="${param.txtProductName}"
                           placeholder="Name" required
                           maxlength="50" />

                    <label class="form-label fontWeight">Choose image:</label>
                    <input type="file" class="form-control-file border"  id="upload" onchange="getImage()"
                           name="file" required 
                           />
                    <div class="text-center">
                        <div id="displayImg" >

                        </div>
                    </div>

                    <label class="form-label fontWeight">Description:</label>
                    <input class="form-control" placeholder="Description" 
                           name="txtDescription" 
                           value="${param.txtDescription}" maxlength="250"
                           required />
                    <label class="form-label fontWeight">Price:</label>
                    <input class="form-control" type="number" name="txtPrice" 
                           value="${param.txtPrice}"
                           placeholder="Price" required step="0.01"
                           maxlength="50" /> 
                    <label class="form-label fontWeight">Category:</label>
                    <select class="form-control" name="cboCategory">
                        <c:set var="categoryList" value="${applicationScope.CATEGORY}"/>
                        <c:if test="${not empty categoryList}">
                            <c:forEach var="cate" items="${categoryList}" varStatus="counter">
                                <option value="${cate.categoryName}"
                                        >${cate.categoryName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <label class="form-label fontWeight">Quantity:</label>
                    <input class="form-control" type="number" name="txtQuantity" 
                           value="${param.txtQuantity}"
                           placeholder="Quantity" required
                           maxlength="50" min="0" max="100"/>
                    <div class="text-center" style="margin-top: 20px;">
                        <input type="submit" value="Create New Product" name="btnAction" class="btn btn-primary"  />
                        <c:url var="cancel" value="DispatchServlet">
                            <c:param name="btnAction" value=""/>
                        </c:url>
                        <a href="${cancel}" class="btn btn-warning">Cancel</a>
                    </div>
                </form>
            </div>
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
                        console.log(newImage.src);
                        document.getElementById("displayImg").innerHTML = newImage.outerHTML;
                    }
                    fileReader.readAsDataURL(fileToLoad);
                }
            }
        </script>
    </body>
</html>
