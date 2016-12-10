package com.netcracker.cinema.web.adminModifyMovie;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
        import com.vaadin.ui.Button.ClickEvent;

public class ConfirmationDialog {

    public Window infoDialog(UI myUI, String message) {
        Window subWindow = new Window("Confirmation");
        subWindow.setHeight("225px");
        subWindow.setWidth("300px");

        VerticalLayout subContent = new VerticalLayout();
        subContent.setMargin(true);
        subContent.setSpacing(true);
        subWindow.setContent(subContent);
        subWindow.center();
        subWindow.setModal(true);

        Button button = new Button("OK");
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        button.addClickListener(e -> {
            myUI.removeWindow(subWindow);
        });
        Label label = new Label(message);

        subContent.addComponents(label, button);
        return subWindow;
    }

}