package webcrawler;

import java.util.HashMap;
import java.util.Map;

public class PageContent {
    private final Map<String, String> pathWithContent;
    private final Map<String, String> pathWithTitle;
    private final int                 port   = 1080;
    private final String              domain = "localhost";

    public PageContent() {
        this.pathWithContent = new HashMap<>();
        this.pathWithTitle = new HashMap<>();
        init();
    }

    private void init() {
        // Links
        String exampleDomainPath = "example.com";
        String circular1Path     = "circular1";
        String circular2Path     = "circular2";
        String circular3Path     = "circular3";

        // Titles
        String exampleDomainTitle = "Example Domain";
        String circular1Title     = "circular1Title";
        String circular2Title     = "circular2Title";
        String circular3Title     = "circular3Title";

        // Contents
        String exampleDomainContent =
                """
                <!doctype html>
                <html>
                <head>
                    <title>Example Domain</title>
                
                    <meta charset="utf-8" />
                    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                    <style type="text/css">
                    body {
                        background-color: #f0f0f2;
                        margin: 0;
                        padding: 0;
                        font-family: "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
                
                    }
                    div {
                        width: 600px;
                        margin: 5em auto;
                        padding: 50px;
                        background-color: #fff;
                        border-radius: 1em;
                    }
                    a:link, a:visited {
                        color: #38488f;
                        text-decoration: none;
                    }
                    @media (max-width: 700px) {
                        body {
                            background-color: #fff;
                        }
                        div {
                            width: auto;
                            margin: 0 auto;
                            border-radius: 0;
                            padding: 1em;
                        }
                    }
                    </style>
                </head>
                
                <body>
                <div>
                    <h1>Example of Example Domain</h1>
                    <p>This domain is established to be used for illustrative examples in documents. You may use this
                    domain in examples without prior coordination or asking for permission.</p>
                    <p><a href="unavailablePage">More information...</a></p>
                </div>
                </body>
                </html>""";
        String circular1Content = """
                                  <!doctype html>
                                  <html>
                                  <head>
                                  <title>circular1Title</title>
                                  </head>
                                  <body>
                                  <a href="circular2">link1</a>
                                  </body>
                                  </html>
                                  """;

        String circular2Content = """
                                  <!doctype html>
                                  <html>
                                  <head>
                                  <title>circular2Title</title>
                                  </head>
                                  <body>
                                  <a href="circular3">link1</a>
                                  </body>
                                  </html>
                                  """;

        String circular3Content = """
                                  <!doctype html>
                                  <html>
                                  <head>
                                  <title>circular3Title</title>
                                  </head>
                                  <body>
                                  <a href="circular1">link</a>
                                  <a href="exampleDotCom">link</a>
                                  </body>
                                  </html>
                                  """;

        pathWithContent.put(exampleDomainPath, exampleDomainContent);
        pathWithTitle.put(exampleDomainPath, exampleDomainTitle);

        pathWithContent.put(circular1Path, circular1Content);
        pathWithTitle.put(circular1Path, circular1Title);

        pathWithContent.put(circular2Path, circular2Content);
        pathWithTitle.put(circular2Path, circular2Title);

        pathWithContent.put(circular3Path, circular3Content);
        pathWithTitle.put(circular3Path, circular3Title);
    }

    public Map<String, String> pathWithContent() {
        return pathWithContent;
    }

    public String getContentByPath(String path) {
        return pathWithContent.getOrDefault(path, "");
    }

    public String getTitleByPath(String path) {
        return pathWithTitle.getOrDefault(path, "");
    }

    public String getURLByPath(String path) {
        return String.format("http://%s:%d/%s", domain, port, path);
    }

    public int getPort() {
        return port;
    }

    public String getDomain() {
        return domain;
    }
}
