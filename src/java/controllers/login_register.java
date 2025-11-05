/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import dao.*;

/**
 *
 * @author Admin
 */
public class login_register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        Account user = null;

        if (action != null && action.equals("login")) {
            user = AccountDAO.getInstance().login(username, password);
            if (user != null) {
                // Đăng nhập thành công
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/Views/home.jsp");
            } else {
                // Đăng nhập thất bại
                request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
                request.getRequestDispatcher("/Views/login_register.jsp").forward(request, response);
            }
        } else if (action != null && action.equals("register")) {
            if(){
                request.setAttribute("error", "Đăng ký thành công");
            }else{
                request.setAttribute("error", "Đăng ký không thành công");
            }
            request.getRequestDispatcher("/Views/login_register.jsp").forward(request, response);
        } else {
            // Nếu không có action, quay về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/Views/login_register.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
