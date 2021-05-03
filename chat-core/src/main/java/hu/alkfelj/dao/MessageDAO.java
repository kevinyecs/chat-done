package hu.alkfelj.dao;

import hu.alkfelj.model.Message;

import java.util.List;

public interface MessageDAO {
    List<Message> findAll();
    void addUzenet(Message uzenet);
}