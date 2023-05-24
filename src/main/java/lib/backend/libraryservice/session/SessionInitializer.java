package lib.backend.libraryservice.session;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import jakarta.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Component
public class SessionInitializer implements ApplicationListener<ContextRefreshedEvent>, ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 세션 초기화 작업 수행
        clearSessionAttributes();
    }

    private void clearSessionAttributes() {
        Enumeration<String> attributeNames = servletContext.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            servletContext.removeAttribute(attributeName);
        }
    }
}
