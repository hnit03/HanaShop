<%-- 
    Document   : searchForm
    Created on : Jan 9, 2021, 4:27:43 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Form</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div style="background-color: white;
             padding: 20px 100px 20px 100px; margin-bottom: 20px;margin-top: -20px;">
            <form action="DispatchServlet" method="GET" class="d-flex">
                <label class="nav-link">Name:</label>
                <input type="text" class="form-control" placeholder="Name" name="txtSearchValue" value="${param.txtSearchValue}" style="width: 50%;"/>
                <label class="nav-link">Category:</label>
                <select class="form-control" style="width: 50%;" name="cboCategory">
                    <option value="--Select Category--">--Select Category--</option>
                    <c:set var="categoryList" value="${applicationScope.CATEGORY}"/>
                    <c:if test="${not empty categoryList}">
                        <c:forEach var="cate" items="${categoryList}" varStatus="counter">
                            <option value="${cate.categoryName}"
                                    <c:if test="${cate.categoryName eq param.cboCategory}">
                                        selected
                                    </c:if>
                                    >${cate.categoryName}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <label class="nav-link">Price:</label>
                <input type="number" class="form-control" 
                       name="txtPriceMin" 
                       value="${param.txtPriceMin}" 
                       placeholder="Min"
                       max="1000"
                       min="0"
                       style="margin-left: 10px;margin-right: 10px;width: 20%">
                <label class="nav-link">-</label>
                <input type="number" 
                       class="form-control" 
                       name="txtPriceMax" 
                       value="${param.txtPriceMax}"
                       min="0"
                       max="1000"
                       placeholder="Max" 
                       style="margin-left: 10px;margin-right: 10px;width: 20%">
                <span class="input-group-btn">
                    <input class="btn btn-primary" type="submit" name="btnAction" value="Search"/>
                </span>
            </form>
        </div>
    </body>
</html>
