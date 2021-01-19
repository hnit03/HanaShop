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
        <style>
            .modal {
                display: none;
                position: fixed; 
                z-index: 1; 
                padding-top: 100px;
                padding-left: 30%;
                padding-right: 30%;
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
            <c:set var="productList" value="${requestScope.ALLPRODUCT}"/>
            <c:if test="${not empty productList}">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col" style="width: 120px;">Name</th>
                            <th scope="col" style="width: 160px;">Image</th>
                            <th scope="col" style="width: 100px;">Price</th>
                            <th scope="col">Description</th>
                            <th scope="col">Create Date</th>
                            <th scope="col" style="width: 150px;">Category</th>
                            <th scope="col" style="width: 130px;">Status</th>
                            <th scope="col" style="width: 100px;">Quantity</th>
                            <th scope="col" ></th>
                        </tr>
                    </thead>
                    <tbody>

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
                                            
                                            <a class="btn btn-danger form-control"
                                               id="btn${dto.productID}"
                                               onclick="remove(${dto.productID})">Update</a>

                                            <div id="modal${dto.productID}" class="modal">
                                                <div class="modal-content">
                                                    <h5 class="modal-title">Do you want to delete the ${dto.productID} product?</h5><br/>
                                                    <div class="modal-footer">
                                                        <input type="submit" value="Update" name="btnAction" class="btn btn-warning"/>
                                                        <input type="hidden" name="productID" value="${dto.productID}" />
                                                        <input type="hidden" name="cboCategory" value="${dto.cdto.categoryName}" />
                                                        <input type="hidden" name="cboStatus" value="${cboStatus}" />
                                                        <input type="hidden" name="updateDetail" value="false" />
                                                        <span class="btn btn-danger" id="close" 
                                                              onclick="document.getElementById('modal' + ${dto.productID}).style.display = 'none'">Cancel</span>
                                                    </div>

                                                </div>
                                            </div>
                                            <script>
                                                function remove(id) {
                                                    document.getElementById('modal' + id).style.display = "block";
                                                }
                                            </script>
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
                <!--paging-->
                <nav aria-label="Page navigation example">
                    <c:set var="pageNo" value="${requestScope.PAGENO}"/>                   
                    <c:set var="pageMaxAdmin" value="${requestScope.PAGE_MAX_ADMIN}"/>
                    <c:if test="${pageMaxAdmin > 1}">
                        <form action="DispatchServlet">
                            <ul class="pagination justify-content-end">
                                <c:if test="${pageNo <= 1}">
                                    <li class="page-item disabled">
                                        <input type="submit" value="Previous" name="btnAction" class="page-link"/>
                                    </li>   
                                </c:if>
                                <c:if test="${pageNo > 1}">
                                    <li class="page-item">
                                        <input type="submit" value="Previous" name="btnAction" class="page-link"/>
                                    </li>   
                                </c:if>
                                <c:if test="${pageNo < pageMaxAdmin}">
                                    <li class="page-item">
                                        <input type="submit" value="Next" name="btnAction" class="page-link"/>
                                    </li>   
                                </c:if>   
                                <c:if test="${pageNo == pageMaxAdmin}">
                                    <li class="page-item disabled">
                                        <input type="submit" value="Next" name="btnAction" class="page-link"/>
                                    </li>   
                                </c:if>
                                <input type="hidden" name="pageNo" value="${pageNo}" />
                                <input type="hidden" name="pageView" value="adminPage.jsp" />
                            </ul>
                        </form>
                    </c:if>
                </nav>
            </c:if>
        </div>
    </body>
</html>
