package ru.flendger.schoollib2.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.gui.forms.reports.LocationStorageReportController;
import ru.flendger.schoollib2.gui.utils.ListFormUtils;
import ru.flendger.schoollib2.gui.utils.ReportFormUtils;
import ru.flendger.schoollib2.model.catalog.*;
import ru.flendger.schoollib2.model.operation.Invention;
import ru.flendger.schoollib2.model.operation.ReceiptFromPublisher;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class MainController implements Initializable {

    private final ListFormUtils listFormLoader;
    private final ReportFormUtils reportFormUtils;

    @FXML
    TreeItem<String> cmdBookTypes, cmdBooks, cmdLocations,
            cmdOwners, cmdPublishers, cmdSubjects, cmdLocationTypes, cmdPeople,
            cmdInvention, cmdLocationStorageReport, cmdReceiptFromPublisher;

    @FXML
    TabPane tabPane;

    @FXML
    TreeView<String> cmdTree;


    public void btnExitAction() {
        Platform.exit();
    }

    public void cmdItemClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            TreeItem<String> itemClicked = ((TreeCell<String>) mouseEvent.getSource()).getTreeItem();
            if (!itemClicked.isLeaf()) return;

            String id = itemClicked.getValue();

            Optional<Tab> res = tabPane.getTabs().stream()
                    .filter(arg -> arg.getId().equals(id))
                    .findAny();
            if (res.isPresent()) {
                tabPane.getSelectionModel().select(res.get());
                return;
            }

            Parent root = null;
            if (cmdBooks.equals(itemClicked)) {
                root = listFormLoader.getRoot(Book.class);
            } else if (cmdBookTypes.equals(itemClicked)) {
                root = listFormLoader.getRoot(BookType.class);
            } else if (cmdLocations.equals(itemClicked)) {
                root = listFormLoader.getRoot(Location.class);
            } else if (cmdOwners.equals(itemClicked)) {
                root = listFormLoader.getRoot(Owner.class);
            } else if (cmdPublishers.equals(itemClicked)) {
                root = listFormLoader.getRoot(Publisher.class);
            } else if (cmdPeople.equals(itemClicked)) {
                root = listFormLoader.getRoot(Person.class);
            } else if (cmdSubjects.equals(itemClicked)) {
                root = listFormLoader.getRoot(Subject.class);
            } else if (cmdLocationTypes.equals(itemClicked)) {
                root = listFormLoader.getRoot(LocationType.class);
            } else if (cmdInvention.equals(itemClicked)) {
                root = listFormLoader.getRoot(Invention.class);
            } else if (cmdReceiptFromPublisher.equals(itemClicked)) {
                root = listFormLoader.getRoot(ReceiptFromPublisher.class);
            } else if (cmdLocationStorageReport.equals(itemClicked)) {
                root = reportFormUtils.getRoot(LocationStorageReportController.class);
            }
            Tab tab = new Tab(id, root);
            tab.setId(id);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmdTree.setCellFactory(arg -> {
            TreeCell<String> cell = new TreeCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setOnMouseClicked(this::cmdItemClicked);
            return cell;
        });
    }
}
