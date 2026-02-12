package repository;

import model.Series;
import database.IDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeriesRepository {
    private final IDB db;

    public SeriesRepository(IDB db) {
        this.db = db;
    }

    public void create(Series s) {
        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps1 = c.prepareStatement("INSERT INTO media_content VALUES (?, ?, ?)");
            ps1.setInt(1, s.getMediaID());
            ps1.setString(2, s.getTitle());
            ps1.setInt(3, s.getReleaseYear());
            ps1.execute();

            PreparedStatement ps2 = c.prepareStatement("INSERT INTO series VALUES (?, ?, ?)");
            ps2.setInt(1, s.getMediaID());
            ps2.setString(2, s.getGenre());
            ps2.setInt(3, s.getSeasons());
            ps2.execute();

            c.close();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    public Series getById(int id) {
        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps = c.prepareStatement(
                    "SELECT * FROM media_content mc JOIN series s ON mc.media_id = s.media_id WHERE mc.media_id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Series(
                        rs.getInt("media_id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getString("genre"),
                        rs.getInt("seasons")
                );
            }
            c.close();

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    public List<Series> getAll() {

        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps = c.prepareStatement("SELECT * FROM media_content mc JOIN series s ON mc.media_id = s.media_id");
            ResultSet rs = ps.executeQuery();
            List<Series> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Series(
                        rs.getInt("media_id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getString("genre"),
                        rs.getInt("seasons")
                ));
            }
            c.close();
            return list;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    public void update(int id, Series s) {
        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps1 = c.prepareStatement("UPDATE media_content SET title=?, release_year=? WHERE media_id=?");
            ps1.setString(1, s.getTitle());
            ps1.setInt(2, s.getReleaseYear());
            ps1.setInt(3, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = c.prepareStatement("UPDATE series SET genre=?, seasons=? WHERE media_id=?");
            ps2.setString(1, s.getGenre());
            ps2.setInt(2, s.getSeasons());
            ps2.setInt(3, id);
            ps2.executeUpdate();

            c.close();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    public void delete(int id) {
        Connection c = null;
        try {
            c = db.getConnection();
            PreparedStatement ps1 = c.prepareStatement("DELETE FROM series WHERE media_id=?");
            ps1.setInt(1, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = c.prepareStatement("DELETE FROM media_content WHERE media_id=?");
            ps2.setInt(1, id);
            ps2.executeUpdate();

            c.close();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }
}
