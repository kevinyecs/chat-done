package hu.alkfelj.dao;
import hu.alkfelj.config.UrlConfiguration;
import hu.alkfelj.model.ChatRoom;
import hu.alkfelj.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomDAOimpl implements ChatRoomDAO {

    private static final String SELECT_ALL_CHATROOM = "SELECT * FROM CHATROOM";
    private static final String INSERT_ROOM = "INSERT INTO CHATROOM (id,name,category,rule) VALUES (?,?,?,?)";
    private static final String UPDATE_ROOM = "UPDATE CHATROOM SET name=?, category=?, rule=? WHERE id = ?";
    private static final String DELETE_ROOM = "DELETE FROM CHATROOM WHERE id = ?";
    private static final String SELECT_ALL_CHAT_BY_USER = "SELECT * FROM CHATROOM WHERE user_id = ?";
    private String CONNECT_URL;



    public ChatRoomDAOimpl(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CONNECT_URL = UrlConfiguration.getValue("db.url");
    }

    @Override
    public List<ChatRoom> findAll() {
        return findAll(null);
    }
    @Override
    public List<ChatRoom> findAll(User user) {
        List<ChatRoom> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONNECT_URL);
        ){
            ResultSet rs;
            if(user == null){
                Statement stmt = c.createStatement();
                rs = stmt.executeQuery(SELECT_ALL_CHATROOM);
            }
            else{
                PreparedStatement stmt = c.prepareStatement(SELECT_ALL_CHAT_BY_USER);
                stmt.setInt(1, user.getId());
                rs = stmt.executeQuery();
            }

            while(rs.next()){
                ChatRoom chatRoom = new ChatRoom();
                chatRoom.setId(rs.getInt("id"));
                chatRoom.setName(rs.getString("name"));
                chatRoom.setCategory(rs.getString("category"));
                chatRoom.setRule(rs.getString("rule"));

                result.add(chatRoom);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public void deleteRoom(ChatRoom chatRoom) {
        try(Connection c = DriverManager.getConnection(CONNECT_URL);
            PreparedStatement stmt = c.prepareStatement(DELETE_ROOM);
        ) {
            stmt.setInt(1,chatRoom.getId());
            stmt.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
