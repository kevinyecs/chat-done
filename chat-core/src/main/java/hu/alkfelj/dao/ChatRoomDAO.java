package hu.alkfelj.dao;
import hu.alkfelj.model.ChatRoom;
import hu.alkfelj.model.User;

import java.util.List;

public interface ChatRoomDAO {
    List<ChatRoom> findAll();
    List<ChatRoom> findAll(User user);
    void deleteRoom(ChatRoom szoba);
}
