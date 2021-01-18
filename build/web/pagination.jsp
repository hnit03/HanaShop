<%-- 
    Document   : pagination
    Created on : Jan 9, 2021, 4:35:42 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagination</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <nav aria-label="Page navigation example">
            <c:set var="pageNo" value="${requestScope.PAGENO}"/>
            <c:set var="pageMax" value="${requestScope.PAGEMAX}"/>
            <c:if test="${pageMax != 1}">
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
                        <c:if test="${pageNo < pageMax}">
                            <li class="page-item">
                                <input type="submit" value="Next" name="btnAction" class="page-link"/>
                            </li>   
                        </c:if>   
                        <c:if test="${pageNo >= pageMax}">
                            <li class="page-item disabled">
                                <input type="submit" value="Next" name="btnAction" class="page-link"/>
                            </li>   
                        </c:if>
                        <input type="hidden" name="pageNo" value="${pageNo}" />
                    </ul>
                </form>
            </c:if>
        </nav>
    </body>
</html>
