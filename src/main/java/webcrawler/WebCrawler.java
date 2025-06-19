package webcrawler;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler extends JFrame {
    private JTextField textField;
    private JTextArea  textArea;
    private JLabel title;

    public WebCrawler() {
        setTitle("Web Crawler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(500, 500);
        setSize(dimension);
        setPreferredSize(dimension);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        buildURLPanel();
        addLabelForTitle();
        addTextArea();
    }

    private void buildURLPanel() {
        var urlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        urlPanel.setSize(500, 75);
        configureURLTextField();
        urlPanel.add(textField);
        urlPanel.add(configureButton());
        this.add(urlPanel);
    }

    private void addTextArea() {
        textArea = new JTextArea(12, 25);
        textArea.setName("HtmlTextArea");
        textArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
        textArea.setVisible(true);
        textArea.setEnabled(false);
        this.add(textArea);
    }

    private void configureURLTextField() {
        textField = new JTextField();
        textField.setName("UrlTextField");
        textField.setColumns(30);
        textField.setEnabled(true);
        textField.setVisible(true);
    }

    private JButton configureButton() {
        var button = new JButton();
        button.setName("RunButton");
        button.setText("Download");
        button.setSize(70,50);
        button.setVisible(true);
        button.setEnabled(true);
        button.addActionListener(ignored -> downloadHtml());
        return button;
    }

    private void addLabelForTitle() {
        var panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setSize(500, 50);

        var heading = new JLabel();
        heading.setText("Title:");
        heading.setVisible(true);

        title = new JLabel();
        title.setName("TitleLabel");
        title.setText("");
        title.setVisible(true);

        panel.add(heading);
        panel.add(title);
        this.add(panel);
    }

    void downloadHtml() {
        String html = getHtml(getTextField());
        setTextArea(html);
        title.setText(getTitleFromHtml(html));
    }

    void setTextArea(String html) {
        textArea.setText(html);
    }

    String getTextField() {
        return textField.getText();
    }

    String getHtml(String url) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            URI website = URI.create(url);
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(website)
                                             .GET()
                                             .build();

            HttpResponse<String> response = client.send(request,
                                                        HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Error: " + response.statusCode();
            }
        } catch (Exception e) {
            return "Error: " + e.getLocalizedMessage();
        }
    }

    String getTitleFromHtml(String html) {
        Pattern titleRegex = Pattern.compile("(<title>)(.*)(</title>)", Pattern.CASE_INSENSITIVE);
        Matcher titleMatcher = titleRegex.matcher(html);
        if (titleMatcher.find()) {
            return titleMatcher.group(2);
        }
        return "<No title found>"; // if the HTML code has no <title></title> block
    }
}
