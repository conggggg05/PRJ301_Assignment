<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh Sách Phim</title> 
    </head>
    <body>
        
        <h1 style="width:100%; text-align:center;">Phimrap.org</h1>

        <table style="width:100%; border-collapse:collapse; text-align:center;">
            <thead>
                <tr>
                    <th><a href="Views/home.jsp">Trang chủ</a></th>

                    <c:if test="${not empty sessionScope.user}">
                        <th><a href="booking.jsp">Đặt vé</a></th>
                        
                        <th><a href="${pageContext.request.contextPath}/filmList">Phim</a></th>
                        
                        <th><a href="schedule.jsp">Lịch phim</a></th>
                        <th><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></th>
                        <th>Xin chào, ${sessionScope.user.username}</th>
                    </c:if>

                    <c:if test="${empty sessionScope.user}">
                        <th><a href="${pageContext.request.contextPath}/login_register">Đăng nhập</a></th>
                        <th><a href="${pageContext.request.contextPath}/login_register">Đăng ký</a></th>
                    </c:if>
                </tr>
            </thead>
        </table>

        <div style="text-align:center; margin-top:30px; width: 80%; margin-left: auto; margin-right: auto;">
            <h2>Phim đang chiếu</h2>

            <c:if test="${not empty requestScope.errorMessage}">
                <p style="color: red;">${requestScope.errorMessage}</p>
            </c:if>
            
            <c:if test="${empty requestScope.listF}">
                <p>Hiện tại chưa có phim nào.</p>
            </c:if>

            <table style="width:100%; border-collapse: collapse; text-align: left;" border="1">
                <c:forEach var="f" items="${requestScope.listF}">
                    <tr>
                        <td style="padding: 15px;">
                            <h3 style="margin-top: 0;">${f.filmName}</h3>
                            <p><i>Thời lượng: ${f.duration} phút</i></p>
                            <p>${f.description}</p>
                            
                            <a href="schedule?filmId=${f.filmID}" style="font-weight:bold; color:blue;">
                                Xem lịch chiếu & Đặt vé
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</html>