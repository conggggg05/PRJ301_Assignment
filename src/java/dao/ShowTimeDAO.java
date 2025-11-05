/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ShowTime;

/**
 *
 * @author ASUS
 */
public class ShowTimeDAO extends DBContext {

    private static ShowTimeDAO instance;
    private static final Logger LOGGER = Logger.getLogger(FilmDAO.class.getName());

    private ShowTimeDAO() {
        super();
    }

    public static ShowTimeDAO getInstance() {
        if (instance == null) {
            instance = new ShowTimeDAO();
        }
        return instance;
    }

    private final String SQL_GET_ALL_SHOWTIME = "SELECT * FROM Showtime;";
    private final String SQL_GET_SHOWTIME_BY_SHOWTIME_ID = "SELECT *\n"
            + "FROM Showtime\n"
            + "WHERE ShowtimeID = [Giá trị ID];";
    private final String SQL_GET_SHOWTIME_BY_FILM_ID = "SELECT *\n"
            + "FROM Showtime\n"
            + "WHERE FilmID = [Giá trị FilmID];";
    private final String SQL_INSERT_SHOWTIME = "INSERT INTO Showtime (FilmID, startTime, Price)\n"
            + "VALUES (?,?,?);";
    private final String SQL_UPDATE_SHOWTIME = "UPDATE Showtime\n"
            + "SET FilmID = ?,\n"
            + "    startTime = ?,\n"
            + "    Price = ?\n"
            + "WHERE ShowtimeID = ?;";
    private final String SQL_DELETE_SHOWTIME = "DELETE FROM Showtime\n"
            + "WHERE ShowtimeID = ?;";

    public List<ShowTime> getAllShowTime() {
        List<ShowTime> list = new ArrayList<>();

        String sql = SQL_GET_ALL_SHOWTIME;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ShowTime showtime = new ShowTime(rs.getInt("ShowtimeID"),
                        rs.getInt("FilmID"),
                        rs.getDate("startTime"),
                        rs.getDouble("Price")
                );
                list.add(showtime);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Loi khi lay danh sach showtime", e);
        }
        return list;
    }

    public List<ShowTime> getShowTimeBySTID() {
        List<ShowTime> list = new ArrayList<>();
        String sql = SQL_GET_SHOWTIME_BY_SHOWTIME_ID;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ShowTime showtime = new ShowTime(rs.getInt("ShowtimeID"),
                        rs.getInt("FilmID"),
                        rs.getDate("startTime"),
                        rs.getDouble("Price")
                );
                list.add(showtime);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Loi khi lay danh sach showtime", e);
        }
        return list;
    }
    
    public List<ShowTime> getShowTimeByFilmID() {
        List<ShowTime> list = new ArrayList<>();
        String sql = SQL_GET_SHOWTIME_BY_FILM_ID;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ShowTime showtime = new ShowTime(rs.getInt("ShowtimeID"),
                        rs.getInt("FilmID"),
                        rs.getDate("startTime"),
                        rs.getDouble("Price")
                );
                list.add(showtime);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Loi khi lay danh sach showtime", e);
        }
        return list;
    }
    
    public boolean addFilm(ShowTime showtime) {
        // Sử dụng hằng số SQL
        String sql = SQL_INSERT_SHOWTIME; 

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, showtime.getFilmID());
            ps.setDate(2, (Date) showtime.getStartTime());
            ps.setDouble(3, showtime.getPrice());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Loi khi them phim moi", e);
            return false;
        }
    }

}
