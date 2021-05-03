package hu.alkfelj.controller;

import hu.alkfelj.dao.ChatRoomDAO;
import hu.alkfelj.dao.ChatRoomDAOimpl;
import hu.alkfelj.dao.UserDAO;
import hu.alkfelj.dao.UserDAOimpl;
import hu.alkfelj.model.ChatRoom;
import hu.alkfelj.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    UserDAO dao = new UserDAOimpl();
    ChatRoomDAO chatdao = new ChatRoomDAOimpl();

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, Void> actionsColumn;
    @FXML
    private TableView<ChatRoom> chatTable;
    @FXML
    private TableColumn<ChatRoom,String> labelColumn;
    @FXML
    private TableColumn<ChatRoom, Void> deleteColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
        refreshChatTable();

        labelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        deleteColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Delete Room");

            {
                deleteBtn.setOnAction(event -> {
                    ChatRoom c = getTableRow().getItem();
                    deleteChatroom(c);
                    refreshChatTable();
                });


            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setGraphic(null);
                }
                else {
                    HBox container = new HBox();
                    container.getChildren().addAll(deleteBtn);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }

        });

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        actionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Delete");


            {
                deleteBtn.setOnAction(event -> {
                    User u = getTableRow().getItem();
                    deleteUser(u);
                    refreshTable();
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setGraphic(null);
                }
                else {
                    HBox container = new HBox();
                    container.getChildren().addAll( deleteBtn);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });

    }

    private void refreshTable() {
        userTable.getItems().setAll(dao.findAll());
    }

    private void refreshChatTable() {
        chatTable.getItems().setAll(chatdao.findAll());
    }

    public void deleteUser(User u) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Sure you wanna delete?" + u.getName(), ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)) {
                dao.delete(u);
            }
        });
    }
    public void deleteChatroom(ChatRoom c){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Sure you wanna delete?" + c.getName(), ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)) {
                chatdao.deleteRoom(c);
            }
        });
    }

    @FXML
    public void onExit(){
        Platform.exit();
    }
}
