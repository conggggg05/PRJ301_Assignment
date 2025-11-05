package controllers;

import dao.FilmDAO;
import models.Film;
import java.io.IOException;
import java.util.List;

// Sử dụng thư viện Jakarta
import jakarta.servlet.ServletException;
// KHÔNG import @WebServlet
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class film_list_servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            FilmDAO dao = FilmDAO.getInstance();
            List<Film> filmList = dao.getAllFilms();
            request.setAttribute("listF", filmList);
            
        } catch (Exception e) {
            e.printStackTrace(); 
            request.setAttribute("errorMessage", "Lỗi khi tải danh sách phim.");
        }
        
        // Chuyển tiếp sang film.jsp
        request.getRequestDispatcher("Views/film.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Gọi lại doGet
        doGet(request, response);
    }
}