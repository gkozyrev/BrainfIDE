package com.ide.GUI;

import java.awt.*;
import javax.swing.*;

public class MenuBar {
    GUI guiClass;

    public void Initialize(GUI guiClassInit) {
        guiClass = guiClassInit;

        // Creating the MenuBar and adding components
        guiClass.menubar = new JMenuBar();
        guiClass.menubarFile = new JMenu("File");
        guiClass.menubarRun = new JMenu("Run");
        guiClass.menubarHelp = new JMenu("Help");
        guiClass.menubar.add(guiClass.menubarFile);
        guiClass.menubar.add(guiClass.menubarRun);
        guiClass.menubar.add(guiClass.menubarHelp);
        guiClass.menubarFileOpen = new JMenuItem("Open");
        guiClass.menubarFileSave = new JMenuItem("Save");
        guiClass.menubarFileSaveAs = new JMenuItem("Save as");
        guiClass.menubarFile.add(guiClass.menubarFileOpen);
        guiClass.menubarFile.add(guiClass.menubarFileSave);
        guiClass.menubarFile.add(guiClass.menubarFileSaveAs);
        guiClass.menubarRunBuild = new JMenuItem("Build");
        guiClass.menubarRun.add(guiClass.menubarRunBuild);

    }

}
