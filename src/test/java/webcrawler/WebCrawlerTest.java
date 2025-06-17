package webcrawler;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebCrawlerTest {
    private FrameFixture window;
    private static ClientAndServer server;
    private static int testPort = 1080;
    private String testServer = "localhost";

    @BeforeAll
    static void initAll() {
        FailOnThreadViolationRepaintManager.install();
        server = ClientAndServer.startClientAndServer(testPort);
    }

    @BeforeEach
    void init() {
        var frame = GuiActionRunner.execute(WebCrawler::new);
        assertNotNull(frame);
        window = new FrameFixture(frame);
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }

    @AfterAll
    static void tearDownAll() {
        server.stop();
    }

    @Test
    @Order(1)
    @DisplayName("Verify stage 1 components.")
    void testStage1components() {
        window.requireTitle("Web Crawler");
        var textArea = window.textBox("HtmlTextArea");
        textArea.requireVisible();
        textArea.requireDisabled();
    }

    @DisplayName("Verify stage 2 components.")
    @Order(2)
    @Test
    void testStage2Components() {
        var textField = window.textBox("UrlTextField");
        textField.requireVisible();
        textField.requireEnabled();
        var button = window.button("RunButton");
        button.requireVisible();
        button.requireEnabled();
    }

    @Test
    @Order(3)
    void testEnteringTextIntoTextField() {
        var textField = window.textBox("UrlTextField");
        assertNotNull(textField);
        textField.enterText("https://localhost:1080/test1");
        textField.requireText("https://localhost:1080/test1");
    }

    @DisplayName("Verify button gets source code")
    @Order(4)
    @Test
    void testStage2ButtonGetsHTML() {
        new MockServerClient("localhost", testPort)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/test1")
                ).respond(
                        HttpResponse.response().withStatusCode(200)
                                .withBody("Sample text")
                );
        window.textBox("UrlTextField")
              .enterText("http://localhost:1080/test1");
        window.button("RunButton")
                .click();
        window.textBox("HtmlTextArea")
              .requireText("Sample text");
    }

    @Disabled("Have to solve typing into text field - may have to make ParameterizedTest or change test framework")
    @DisplayName("Verify button gets source code")
    @Order(5)
    @Test
    void testStage2ButtonGetsHTMLSource() {
        PageContent pageContent = new PageContent();

        for (Map.Entry<String, String> entry : pageContent.pathWithContent()
                                   .entrySet()) {
            String path = entry.getKey();
            String url = pageContent.getURLByPath(path);
            String content = pageContent.getContentByPath(path);

            new MockServerClient(pageContent.getDomain(), pageContent.getPort())
                    .when(
                            request()
                                    .withMethod("GET")
                                    .withPath("/" + path)
                    )
                    .respond(
                            HttpResponse.response()
                                        .withStatusCode(200)
                                        .withBody(content)
                    );
            window.textBox("UrlTextField")
                  .enterText(url);
            window.button("RunButton")
                  .click();
            window.textBox("HtmlTextArea")
                  .requireText(content);
        }
    }
}
