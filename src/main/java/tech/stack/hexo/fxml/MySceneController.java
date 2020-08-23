package tech.stack.hexo.fxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import org.codefx.libfx.control.webview.WebViews;

import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MySceneController implements Initializable {

    private String mInitUrl = "http://localhost:8080";

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webView.getEngine().load(mInitUrl);

        WebViews.addHyperlinkListener(webView, event -> {
            URL url = event.getURL();
            System.out.println(url);
            Desktop d = Desktop.getDesktop();
            try {
                if (null != url && url.getPath().contains("/post")) {
                    d.browse(event.getURL().toURI());
                }
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
            return true;
        }, HyperlinkEvent.EventType.ACTIVATED);
    }

}
