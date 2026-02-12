package repository;

import model.Episode;
import database.IDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EpisodeRepository {

    private final IDB db;

    public EpisodeRepository(IDB db) {
        this.db = db;
    }

    public void create(Episode e) {

        Connection c = null;
        try {
            c = db.getConnection();

            // media_content part (because Episode extends MediaContent)
            PreparedStatement ps1 = c.prepareStatement("INSERT INTO media_content VALUES (?, ?, ?)");
            ps1.setInt(1, e.getMediaID());
            ps1.setString(2, e.getTitle());
            ps1.setInt(3, e.getReleaseYear());
            ps1.execute();

            // episodes table
            PreparedStatement ps2 = c.prepareStatement("INSERT INTO episodes VALUES (?, ?, ?, ?)");
            ps2.setInt(1, e.getMediaID());
            ps2.setInt(2, e.getEpisodeID());
            ps2.setInt(3, e.getDuration());
            ps2.setInt(4, e.getEpisodeNumber());
            ps2.execute();

            c.close();
        } catch (SQLException ex) {
            System.out.println("sql error: " + ex.getMessage());
        }
    }

    public Episode getById(int id) {
        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps = c.prepareStatement(
                    "SELECT * FROM media_content mc JOIN episodes e ON mc.media_id = e.media_id WHERE e.episode_id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Episode(
                        rs.getInt("media_id"),
                        rs.getInt("episode_id"),
                        rs.getInt("release_year"),
                        rs.getInt("episode_number"),
                        rs.getString("title"),
                        rs.getInt("duration")
                );
            }

            c.close();
        } catch (SQLException ex) {
            System.out.println("sql error: " + ex.getMessage());
        }
        return null;
    }

    public List<Episode> getAll() {
        List<Episode> list = new ArrayList<>();
        Connection c = null;
        try {
            c = db.getConnection();

            PreparedStatement ps = c.prepareStatement(
                    "SELECT * FROM media_content mc JOIN episodes e ON mc.media_id = e.media_id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Episode(
                        rs.getInt("media_id"),
                        rs.getInt("episode_id"),
                        rs.getInt("release_year"),
                        rs.getInt("episode_number"),
                        rs.getString("title"),
                        rs.getInt("duration")
                ));
            }

            c.close();
            return list;

        } catch (SQLException ex) {
            System.out.println("sql error: " + ex.getMessage());
        }
        return null;
    }

    public void update(int id, Episode e) {
        Connection c = null;
        try {
            c = db.getConnection();

            // update media_content
            PreparedStatement ps1 = c.prepareStatement(
                    "UPDATE media_content SET title=?, release_year=? WHERE media_id=?");
            ps1.setString(1, e.getTitle());
            ps1.setInt(2, e.getReleaseYear());
            ps1.setInt(3, e.getMediaID());
            ps1.executeUpdate();

            // update episode data
            PreparedStatement ps2 = c.prepareStatement(
                    "UPDATE episodes SET duration=?, episode_number=? WHERE episode_id=?");
            ps2.setInt(1, e.getDuration());
            ps2.setInt(2, e.getEpisodeNumber());
            ps2.setInt(3, id);
            ps2.executeUpdate();

            c.close();
        } catch (SQLException ex) {
            System.out.println("sql error: " + ex.getMessage());
        }
    }

    public void delete(int id) {
        Connection c = null;
        try {
            c = db.getConnection();
            PreparedStatement ps1 = c.prepareStatement("DELETE FROM episodes WHERE episode_id=?");
            ps1.setInt(1, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = c.prepareStatement("DELETE FROM media_content WHERE media_id=(SELECT media_id FROM episodes WHERE episode_id=?)");
            ps2.setInt(1, id);
            ps2.executeUpdate();

            c.close();
        } catch (SQLException ex) {
            System.out.println("sql error: " + ex.getMessage());
        }
    }
}
