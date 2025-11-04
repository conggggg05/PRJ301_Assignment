<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1 style="width:100%; text-align:center;">Phimrap.org</h1>

        <table style="width:100%; border-collapse:collapse; text-align:center;">
            <thead>
                <tr>
                    <th><a href="home.jsp">Trang chủ</a></th>

                    <!-- đã đăng nhập -->
                    <c:if test="${not empty sessionScope.user}">
                        <th><a href="booking.jsp">Đặt vé</a></th>
                        <th><a href="movie.jsp">Phim</a></th>
                        <th><a href="schedule.jsp">Lịch phim</a></th>
                        <th><a href="logout">Đăng xuất</a></th>
                        <th>Xin chào, ${sessionScope.user.username}</th>
                    </c:if>

                    <!-- chưa đăng nhập -->
                    <c:if test="${empty sessionScope.user}">
                        <th><a href="login_register.jsp">Đăng nhập</a></th>
                        <th><a href="login_register.jsp">Đăng ký</a></th>
                    </c:if>
                </tr>
            </thead>
        </table>

        <div style="text-align:center; margin-top:50px;">
            <h2>Chào mừng đến với Phimrap.org</h2>
            <p>Trang web đặt vé xem phim trực tuyến dành cho mọi người.</p>
        </div>
    </body>
</html>
