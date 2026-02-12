package repository;

import model.Movie;
import database.IDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private final IDB db;

    public MovieRepository(IDB db) { this.db = db; }

    public void create(Movie m) throws SQLException {
        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps1 = c.prepareStatement("INSERT INTO media_content VALUES (?, ?, ?)");
            ps1.setInt(1, m.getMediaID());
            ps1.setString(2, m.getTitle());
            ps1.setInt(3, m.getReleaseYear());
            ps1.execute();

            PreparedStatement ps2 = c.prepareStatement("INSERT INTO movies VALUES (?, ?, ?)");
            ps2.setInt(1, m.getMediaID());
            ps2.setInt(2, m.getDuration());
            ps2.setString(3, m.getGenre());
            ps2.execute();

            c.close();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }


    }

    public Movie getById(int id) {
        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps = c.prepareStatement(
                    "SELECT * FROM media_content mc JOIN movies m ON mc.media_id = m.media_id WHERE mc.media_id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Movie(
                        rs.getInt("media_id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getInt("duration"),
                        rs.getString("genre")
                );
            }
            c.close();

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    public List<Movie> getAll() {

        Connection c = null;
        try {
            c = db.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM media_content mc JOIN movies m ON mc.media_id=m.media_id");
            ResultSet rs = ps.executeQuery();
            List<Movie> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new Movie(
                        rs.getInt("media_id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getInt("duration"),
                        rs.getString("genre")
                ));
            }
            c.close();
            return list;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    public void update(int id, Movie m) {
        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps1 = c.prepareStatement("UPDATE media_content SET title=?, release_year=? WHERE media_id=?");
            ps1.setString(1, m.getTitle());
            ps1.setInt(2, m.getReleaseYear());
            ps1.setInt(3, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = c.prepareStatement("UPDATE movies SET duration=?, genre=? WHERE media_id=?");
            ps2.setInt(1, m.getDuration());
            ps2.setString(2, m.getGenre());
            ps2.setInt(3, id);
            ps2.executeUpdate();

            c.close();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    public void delete(int id) throws SQLException {
        Connection c = null;
        try {
            c = db.getConnection();
            PreparedStatement ps2 = c.prepareStatement("DELETE FROM movies WHERE media_id=?");
            ps2.setInt(1, id);
            ps2.executeUpdate();

            PreparedStatement ps1 = c.prepareStatement("DELETE FROM media_content WHERE media_id=?");
            ps1.setInt(1, id);
            ps1.executeUpdate();


            c.close();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

    }
    public double findAverage() throws SQLException {
        Connection c = null;
        try {
            double avg = 0;
            c = db.getConnection();
            PreparedStatement ps2 = c.prepareStatement("SELECT AVG(duration) as avg_duration FROM movies");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                avg = rs.getDouble("avg_duration");
            }

            c.close();
            return avg;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return 0;
    }

}