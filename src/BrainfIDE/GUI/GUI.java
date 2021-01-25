package BrainfIDE.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.io.FileReader;
import javax.swing.filechooser.FileNameExtensionFilter;

import BrainfIDE.Brainfuck.*;

public class GUI {
  JFrame frame;
  JPanel textAreaPanel;
  JSplitPane splitPanel;

  JMenuBar menubar;
  JTextArea textArea;
  JScrollPane scrollTextArea;
  JMenu menubarFile, menubarRun, menubarHelp;

  JMenuItem menubarFileOpen, menubarFileSave, menubarFileSaveAs;

  JMenuItem menubarRunBuild;

  JPanel bottomPanel;
  JScrollPane bottomPanelOutput;
  JLabel bottomPanelIDELabel, bottomPanelFileLabel;

  Popup buildOutput;
  JTextArea buildText;

  File file;
  WindowFooter windowFooterClass;
  MenuBar menuBarClass;

  public GUI() {
    // Creating the Frame
    frame = new JFrame("BrainfIDE");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    // Text Area at the Center
    textArea = new JTextArea();
    textArea.setLineWrap(true);
    textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    scrollTextArea = new JScrollPane(textArea);
    scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    buildText = new JTextArea();
    // buildText.setEditable(false);
    buildText.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));
    buildText.setRows(1);
    JScrollPane scrollBuildText = new JScrollPane(buildText);
    scrollBuildText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTextArea, scrollBuildText);
    splitPanel.setOneTouchExpandable(true);
    splitPanel.setDividerLocation(300);

    windowFooterClass = new WindowFooter();
    windowFooterClass.Initialize(this);

    menuBarClass = new MenuBar();
    menuBarClass.Initialize(this);

    // Provide minimum sizes for the two components in the split pane
    Dimension minimumSize = new Dimension(100, 50);
    scrollTextArea.setMinimumSize(minimumSize);
    scrollBuildText.setMinimumSize(minimumSize);

    // Adding Components to the frame.
    frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
    frame.getContentPane().add(BorderLayout.NORTH, menubar);
    frame.getContentPane().add(BorderLayout.CENTER, splitPanel);

    setupListeners();

    frame.setVisible(true);

  }

  public void Initialize() {
  }

  private void saveAs() {
    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showSaveDialog(frame);
    if (option == JFileChooser.APPROVE_OPTION) {
      file = fileChooser.getSelectedFile();
      windowFooterClass.setFileLabelText(file.getPath());
      bottomPanelFileLabel.setText(file.getPath());
      createFile();
      writeFile();
    } else {
      System.out.print("Save command canceled");
    }
  }

  private void save() {
    if (file != null && file.exists() && !file.isDirectory()) {
      System.out.println("File found");
      writeFile();
    } else {
      System.out.println("File not found");
      saveAs();
    }
  }

  private void createFile() {
    try {
      if (file.createNewFile()) {
        System.out.println("File created: " + file.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void writeFile() {
    try {
      FileWriter myWriter = new FileWriter(file);
      myWriter.write(textArea.getText());
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void build() {
    System.out.print("Building...");
    System.out.print(textArea.getText());
    buildText.setText("Building...");
    Interpreter BrainfuckInterpreter = new Interpreter();
    String buildOutput = BrainfuckInterpreter.interpret(textArea.getText());
    buildText.setText(buildOutput);
  }

  private void setupListeners() {
    menubarFileSaveAs.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        saveAs();
      }
    });

    menubarFileSave.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        save();
      }
    });

    menubarFileOpen.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        open();
      }
    });

    menubarRunBuild.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        build();
      }
    });
    // trying out the new lambda notation
    // textSizeBox.addActionListener(e -> resize());
  }

  private void open() {
    System.out.print("menubarFileOpen");
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("Brainfuck", "b", "bf"));
    fileChooser.setDialogTitle("Choose *.b or *.bf file");
    int option = fileChooser.showOpenDialog(frame);
    if (option == JFileChooser.APPROVE_OPTION) {
      file = fileChooser.getSelectedFile();
      try {

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
          if (!line.startsWith(">")) {
            textArea.append(line + "\n");
          }
        }

        windowFooterClass.setFileLabelText(file.getPath());
        reader.close();
        fileReader.close();

      } catch (IOException ioe) {
        System.out.print(ioe);
      }

    }
  }
}