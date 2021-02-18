package com.ide.GUI;
import java.awt.*;
import javax.swing.*;

public class WindowFooter {
    GUI guiClass;

    public void Initialize(GUI guiClassInit) {
        guiClass = guiClassInit;

        guiClass.bottomPanel = new JPanel();
        guiClass.bottomPanelOutput = new JScrollPane();
    
        BorderLayout bottomPanelBorderLayout = new BorderLayout();
        guiClass.bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        guiClass.bottomPanel.setLayout(bottomPanelBorderLayout);
        guiClass.bottomPanelIDELabel = new JLabel("Brainfuck IDE");
        guiClass.bottomPanelFileLabel = new JLabel("No file chosen yet...");
    
        guiClass.bottomPanel.add(guiClass.bottomPanelIDELabel, "West");
        guiClass.bottomPanel.add(guiClass.bottomPanelFileLabel, "East");

    }

    public void setFileLabelText(String label) {
        guiClass.bottomPanelFileLabel.setText(guiClass.file.getPath());
    }

}
