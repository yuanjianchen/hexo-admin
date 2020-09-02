package tech.stack.hexo;

import com.sun.javafx.application.LauncherImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author chenjianyuan
 */
@EnableFeignClients
@EnableJpaAuditing
@SpringBootApplication
public class HexoAdminApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        new Thread(() -> {
            SpringApplication.run(HexoAdminApplication.class, args);
            App.flag = false;
        }).start();
//        LauncherImpl.launchApplication(App.class, AppPreloader.class, args);

    }

}
