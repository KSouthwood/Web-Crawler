package webcrawler;


import org.assertj.swing.core.ComponentLookupScope;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebCrawlerTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;


    @Override
    protected void onSetUp() throws Exception {
        var frame = GuiActionRunner.execute(WebCrawler::new);
        window = new FrameFixture(robot(), frame);
        robot().settings().componentLookupScope(ComponentLookupScope.ALL);
    }

    @Test
    public void testStage1() {
        window.requireTitle("Web Crawler");
        var textArea = window.textBox();
        assertEquals("TextArea", textArea.target().getName());
        textArea.requireText("HTML code?");
        textArea.requireDisabled();
    }
}
