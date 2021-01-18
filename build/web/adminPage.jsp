<%-- 
    Document   : adminPage
    Created on : Jan 8, 2021, 6:36:55 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <c:set var="isCreate" value="${requestScope.CREATE_SUCCESSFULLY}"/>
        <c:if test="${isCreate eq 'true'}">
            <script>
                alert("Create Successfully!");
            </script>
        </c:if>
            <c:set var="isUpdate" value="${requestScope.UPDATE_SUCCESS}"/>
        <c:if test="${isUpdate eq 'true'}">
            <script>
                alert("Update Successfully!");
            </script>
        </c:if>
        <div style="background-color: white;
             padding: 20px; width: 100%;
             margin-top: -20px;">
            <c:url var="create" value="DispatchServlet">
                <c:param name="btnAction" value="Create"/>
            </c:url>

            <a href="${create}" class="btn btn-primary">
                <i class="fas fa-plus"></i>Create New Food
            </a>
            <br/><br/>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col" style="width: 120px;">Name</th>
                        <th scope="col" style="width: 160px;">Image</th>
                        <th scope="col" style="width: 100px;">Price</th>
                        <th scope="col">Description</th>
                        <th scope="col">Create Date</th>
                        <th scope="col" style="width: 130px;">Category</th>
                        <th scope="col" style="width: 130px;">Status</th>
                        <th scope="col" style="width: 100px;">Quantity</th>
                        <th scope="col" ></th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="productList" value="${requestScope.ALLPRODUCT}"/>
                    <c:if test="${not empty productList}">
                        <c:forEach var="dto" items="${productList}" varStatus="counter">
                            <tr>
                                <td style="width: 120px;">${dto.productName}</td>
                                <td>
                                    <img src="images/${dto.image}" alt="${dto.image}" style="width: 160px;height: 100px"/>
                                </td>
                                <td>
                                    ${dto.price}
                                </td>
                                <td>
                                    ${dto.description}
                                </td>
                                <td>
                                    ${dto.createDate}
                                </td>
                                <td>
                                    <c:set var="categoryList" value="${applicationScope.CATEGORY}"/>
                                    <form action="DispatchServlet">
                                        <select class="form-control" name="cboCategory">
                                            <c:if test="${not empty categoryList}">
                                                <c:forEach var="cate" items="${categoryList}" varStatus="counter">
                                                    <option value="${cate.categoryName}"
                                                            <c:if test="${cate.categoryName == dto.cdto.categoryName}">
                                                                selected
                                                            </c:if>
                                                            >${cate.categoryName}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                        <input type="submit" value="Update" name="btnAction" class="btn btn-warning form-control"/>
                                        <input type="hidden" name="productID" value="${dto.productID}" />
                                        <input type="hidden" name="cboCategory" value="${cboCategory}" />
                                        <input type="hidden" name="cboStatus" value="${dto.status}" />
                                        <input type="hidden" name="updateDetail" value="false" />
                                    </form>
                                </td>
                                <td>
                                    <c:set var="listStatus" value="${applicationScope.STATUS_LIST}"/>
                                    <form action="DispatchServlet">
                                        <c:if test="${not empty listStatus}">
                                            <select name="cboStatus" class="form-control">
                                                <c:if test="${not empty listStatus}">
                                                    <c:forEach var="status" items="${listStatus}" varStatus="counter">
                                                        <option value="${status}"
                                                                <c:if test="${status == dto.status}">
                                                                    selected
                                                                </c:if>
                                                                >${status}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </c:if>
                                        <input type="submit" value="Update" name="btnAction" class="btn btn-warning form-control"/>
                                        <input type="hidden" name="productID" value="${dto.productID}" />
                                        <input type="hidden" name="cboCategory" value="${dto.cdto.categoryName}" />
                                        <input type="hidden" name="cboStatus" value="${cboStatus}" />
                                        <input type="hidden" name="updateDetail" value="false" />
                                    </form>
                                </td>
                                <td
                                    >${dto.quantity}
                                </td>
                                <td>
                                    <c:url var="updateDetail" value="DispatchServlet">
                                        <c:param name="btnAction" value="Update Detail"/>
                                        <c:param name="productID" value="${dto.productID}"/>
                                    </c:url>
                                    <a href="${updateDetail}" class="btn btn-warning form-control" >Update Detail</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
