
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login/Register</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/login_register" method="post" style="width:300px; margin:auto; text-align:center;">
            <h2>Đăng nhập / Đăng ký</h2>
            <input type="text" name="username" placeholder="Tên đăng nhập" required><br><br>
            <input type="password" name="password" placeholder="Mật khẩu" required><br><br>
            <button type="submit" name="action" value="login">Đăng nhập</button>
            <button type="submit" name="action" value="register">Đăng ký</button>
            <c:if test="${not empty requestScope.error}">
                <p style="color: red; margin-top: 10px;">${requestScope.error}</p>
            </c:if>
        </form>

    </body>
</html>
