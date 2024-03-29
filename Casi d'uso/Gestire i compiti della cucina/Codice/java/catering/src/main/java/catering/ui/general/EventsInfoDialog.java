package catering.ui.general;

import catering.businesslogic.CatERing;
import catering.businesslogic.eventmanagement.event.Event;
import catering.businesslogic.eventmanagement.event.EventInfo;
import catering.businesslogic.eventmanagement.service.Service;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class EventsInfoDialog {

    private Stage myStage;

    @FXML
    TreeView<EventInfo> eventTree;

    public void initialize() {
        ObservableList<Event> all = CatERing.getInstance().getEventManager().getEventInfo();
        eventTree.setShowRoot(false);
        TreeItem<EventInfo> root = new TreeItem<>(null);

        for (Event e: all) {
            TreeItem<EventInfo> node = new TreeItem<>(e);
            root.getChildren().add(node);
            ObservableList<Service> serv = e.getServices();
            for (Service s: serv) {
                //node.getChildren().add(new TreeItem<>(s));
            }
        }

        eventTree.setRoot(root);
    }

    @FXML
    public void okButtonPressed() {
        myStage.close();
    }

    public void setOwnStage(Stage stage) {
        myStage = stage;
    }
}
