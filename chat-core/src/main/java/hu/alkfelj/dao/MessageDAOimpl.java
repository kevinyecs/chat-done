package hu.alkfelj.dao;
import hu.alkfelj.config.UrlConfiguration;
import hu.alkfelj.model.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MessageDAOimpl implements MessageDAO {

    private static MessageDAOimpl instance;
    private static final String SELECT_ALL_UZENET = "SELECT * FROM MESSAGE";



    private static final String CONNECTING_URL = UrlConfiguration.getValue("db.url");

    public static MessageDAOimpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new MessageDAOimpl();
        }
        return instance;
    }

    public MessageDAOimpl() {
    }


    @Override
    public List<Message> findAll() {

        List<Message> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONNECTING_URL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_UZENET)) {

            while (rs.next()) {
                Message uzenet = new Message();
                uzenet.setId(rs.getInt("id"));
                uzenet.setContent(rs.getString("content"));
                String when = rs.getString("sendTime");
                String beutify = when.substring(0,10);
                beutify += " " + when.substring(11,19);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(beutify,formatter);

                uzenet.setSendTime(dateTime);
                uzenet.setRoom_id(rs.getInt("room_id"));
                uzenet.setUserid(rs.getInt("user_id"));
                result.add(uzenet);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
    @Override
    public void addUzenet(Message uzenet) {
        try (Connection conn = DriverManager.getConnection(CONNECTING_URL);
             PreparedStatement pst = conn.prepareStatement("INSERT INTO MESSAGE (content,sendTime,room_id,user_id) VALUES (?,?,?,?)")
        ) {

            pst.setString(1, uzenet.getContent());
            pst.setString(2, uzenet.getSendTime().toString());
            pst.setInt(3, uzenet.getRoom_id());
            pst.setInt(4,uzenet.getUserid());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
