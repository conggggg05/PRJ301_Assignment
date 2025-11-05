package dao;

import Models.Film;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lớp FilmDAO chịu trách nhiệm truy vấn cơ sở dữ liệu 
 * liên quan đến bảng 'Film'.
 * Lớp này kế thừa DBContext để sử dụng kết nối 'connection'.
 */
public class FilmDAO extends dal.DBContext {

    // Sử dụng Logger để ghi lại lỗi (thay vì System.out.println)
    private static final Logger LOGGER = Logger.getLogger(FilmDAO.class.getName());

    public List<Film> getAllFilms() {
        List<Film> list = new ArrayList<>();
        // Câu lệnh SQL truy vấn tất cả các cột từ bảng Film
        String sql = "SELECT [FilmID], [filmName], [description], [duration] FROM [Film]";

        // Sử dụng try-with-resources để đảm bảo PreparedStatement và ResultSet được đóng
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            // Lặp qua từng dòng kết quả trả về
            while (rs.next()) {
                Film film = new Film();
                film.setFilmID(rs.getInt("FilmID"));
                film.setFilmName(rs.getString("filmName"));
                film.setDescription(rs.getString("description"));
                film.setDuration(rs.getInt("duration"));
                
                // Thêm đối tượng Film vào danh sách
                list.add(film);
            }
        } catch (SQLException e) {
            // Ghi lại lỗi nếu có ngoại lệ SQL xảy ra
            LOGGER.log(Level.SEVERE, "Loi khi lay danh sach phim", e);
        }
        return list; // Trả về danh sách phim
    }

    public Film getFilmByID(int filmID) {
        String sql = "SELECT [FilmID], [filmName], [description], [duration] FROM [Film] WHERE [FilmID] = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Thiết lập tham số (dấu ? thứ 1) cho câu lệnh SQL
            ps.setInt(1, filmID);
            
            try (ResultSet rs = ps.executeQuery()) {
                // Kiểm tra xem có kết quả trả về không
                if (rs.next()) {
                    Film film = new Film();
                    film.setFilmID(rs.getInt("FilmID"));
                    film.setFilmName(rs.getString("filmName"));
                    film.setDescription(rs.getString("description"));
                    film.setDuration(rs.getInt("duration"));
                    return film; // Trả về đối tượng Film
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Loi khi tim phim bang ID", e);
        }
        return null; // Trả về null nếu không tìm thấy hoặc có lỗi
    }

    public boolean addFilm(Film film) {
        String sql = "INSERT INTO [Film] ([filmName], [description], [duration]) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, film.getFilmName());
            ps.setString(2, film.getDescription());
            ps.setInt(3, film.getDuration());

            // executeUpdate() trả về số dòng bị ảnh hưởng
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất 1 dòng bị ảnh hưởng
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Loi khi them phim moi", e);
            return false;
        }
    }

    public boolean updateFilm(Film film) {
        String sql = "UPDATE [Film] SET [filmName] = ?, [description] = ?, [duration] = ? WHERE [FilmID] = ?";

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
        String sql = "DELETE FROM [Film] WHERE [FilmID] = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, filmID);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            // Cẩn thận: Nếu phim đang được tham chiếu (ví dụ trong Showtime),
            // việc xóa có thể thất bại do ràng buộc khóa ngoại.
            LOGGER.log(Level.SEVERE, "Loi khi xoa phim", e);
            return false;
        }
    }
    
    /**
     * Hàm main để kiểm tra nhanh các chức năng của DAO.
     */
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