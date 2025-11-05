package dao;

import Models.Film;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FilmDAO extends DBContext {

    // Sử dụng Logger để ghi lại lỗi (thay vì System.out.println)

    private static FilmDAO instance;
    private static final Logger LOGGER = Logger.getLogger(FilmDAO.class.getName());

    // --- CÁC HẰNG SỐ SQL CHO BẢNG FILM ---
    // (Đây là phần bạn muốn thêm, nhưng là cho Film)
    private final String SQL_GET_ALL_FILMS = "SELECT [FilmID], [filmName], [description], [duration] FROM [Film]";
    private final String SQL_GET_FILM_BY_ID = "SELECT [FilmID], [filmName], [description], [duration] FROM [Film] WHERE [FilmID] = ?";
    private final String SQL_INSERT_FILM = "INSERT INTO [Film] ([filmName], [description], [duration]) VALUES (?, ?, ?)";
    private final String SQL_UPDATE_FILM = "UPDATE [Film] SET [filmName] = ?, [description] = ?, [duration] = ? WHERE [FilmID] = ?";
    private final String SQL_DELETE_FILM = "DELETE FROM [Film] WHERE [FilmID] = ?";


    
    private FilmDAO() {
        // DBContext đã tự tạo connection trong constructor, không cần làm gì thêm
        super();
    }

    public static FilmDAO getInstance() {
        if (instance == null) {
            instance = new FilmDAO();
        }
        return instance;
    }
    public List<Film> getAllFilms() {
        List<Film> list = new ArrayList<>();
        // Sử dụng hằng số SQL
        String sql = SQL_GET_ALL_FILMS; 

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Film film = new Film(
                    rs.getInt("FilmID"),
                    rs.getString("filmName"),
                    rs.getString("description"),
                    rs.getInt("duration")
                );
                list.add(film);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Loi khi lay danh sach phim", e);
        }
        return list;
    }

    /**
     * Lấy thông tin một bộ phim bằng FilmID.
     * @param filmID ID của phim cần tìm.
     * @return một đối tượng Film nếu tìm thấy, ngược lại trả về null.
     */
    public Film getFilmByID(int filmID) {
        // Sử dụng hằng số SQL
        String sql = SQL_GET_FILM_BY_ID; 

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, filmID);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Film film = new Film(
                        rs.getInt("FilmID"),
                        rs.getString("filmName"),
                        rs.getString("description"),
                        rs.getInt("duration")
                    );
                    return film;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Loi khi tim phim bang ID", e);
        }
        return null;
    }

    /**
     * Thêm một bộ phim mới vào cơ sở dữ liệu.
     * @param film Đối tượng Film chứa thông tin cần thêm.
     * @return true nếu thêm thành công, false nếu thất bại.
     */
    public boolean addFilm(Film film) {
        // Sử dụng hằng số SQL
        String sql = SQL_INSERT_FILM; 

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, film.getFilmName());
            ps.setString(2, film.getDescription());
            ps.setInt(3, film.getDuration());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Loi khi them phim moi", e);
            return false;
        }
    }

    /**
     * Cập nhật thông tin một bộ phim đã có.
     * @param film Đối tượng Film chứa thông tin cần cập nhật.
     * @return true nếu cập nhật thành công, false nếu thất bại.
     */
    public boolean updateFilm(Film film) {
        // Sử dụng hằng số SQL
        String sql = SQL_UPDATE_FILM; 

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, film.getFilmName());
            ps.setString(2, film.getDescription());
            ps.setInt(3, film.getDuration());
            ps.setInt(4, film.getFilmID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Loi khi cap nhat phim", e);
            return false;
        }
    }

    /**
     * Xóa một bộ phim khỏi cơ sở dữ liệu bằng FilmID.
     * @param filmID ID của phim cần xóa.
     * @return true nếu xóa thành công, false nếu thất bại.
     */
    public boolean deleteFilm(int filmID) {
        // Sử dụng hằng số SQL
        String sql = SQL_DELETE_FILM;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, filmID);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Loi khi xoa phim", e);
            return false;
        }
    }
    public static void main(String[] args) {
        // Tạo một đối tượng FilmDAO (sẽ tự động kết nối DB trong constructor của DBContext)
        FilmDAO dao = new FilmDAO();

        // 1. Kiểm tra GetAllFilms
        System.out.println("--- Dang lay tat ca phim ---");
        List<Film> films = dao.getAllFilms();
        if (films.isEmpty()) {
            System.out.println("Khong co phim nao trong CSDL.");
        } else {
            for (Film f : films) {
                System.out.println(f);
            }
        }
        
        System.out.println("---------------------------------");

        // 2. Kiểm tra GetFilmByID
        System.out.println("--- Dang lay phim co ID = 2 ---");
        Film film = dao.getFilmByID(2);
        if (film != null) {
            System.out.println("Tim thay: " + film);
        } else {
            System.out.println("Khong tim thay phim co ID = 2.");
        }
        
        System.out.println("---------------------------------");
        
        // 3. (Tùy chọn) Kiểm tra Add, Update, Delete
        // Ví dụ thêm phim mới:
        /*
        System.out.println("--- Dang them phim moi ---");
        Film newMovie = new Film(0, "Oppenheimer", "Phim tieu su ve J. Robert Oppenheimer.", 180);
        boolean added = dao.addFilm(newMovie);
        System.out.println("Them phim moi thanh cong: " + added);
        
        // In lại danh sách để xem phim mới
        if (added) {
            dao.getAllFilms().forEach(System.out::println);
        }
        */
    }
}