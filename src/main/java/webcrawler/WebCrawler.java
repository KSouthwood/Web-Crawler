package webcrawler;

import javax.swing.*;
import java.awt.*;

public class WebCrawler extends JFrame {
    public WebCrawler() throws HeadlessException {
        super("Web Crawler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        this.add(getTextArea());
    }

    private JTextArea getTextArea() {
        var textArea = new JTextArea();
        textArea.setName("TextArea");
        textArea.setText("HTML code?");
        textArea.setEnabled(false);
        return textArea;
    }
}
