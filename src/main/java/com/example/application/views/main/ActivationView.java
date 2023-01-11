package com.example.application.views.main;

import com.example.application.JPA.MailUser;
import com.example.application.JPA.repository.ActivationCodeRepository;
import com.example.application.JPA.repository.MailUserRepository;
import com.example.application.JPA.repository.UsersRepository;
import com.example.application.email.Mailer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.charts.model.Navigator;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

@Route("activate")
@PageTitle("Aktivierung")
@AnonymousAllowed
public class ActivationView extends Composite implements BeforeEnterObserver {
    private VerticalLayout layout;
    Logger logger = LoggerFactory.getLogger(ActivationView.class);
    private ActivationCodeRepository activationCodeRepository;
    private MailUserRepository mailUserRepository;

    public ActivationView(ActivationCodeRepository activationCodeRepository, MailUserRepository mailUserRepository) {
        this.activationCodeRepository = activationCodeRepository;
        this.mailUserRepository = mailUserRepository;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            Map<String, List<String>> params = event.getLocation().getQueryParameters().getParameters();

            String code = params.get("code").get(0);

            if (activationCodeRepository.findByCode(code).isEmpty() || code.equals("387UxMzB12")) {
                StreamResource logoStream = new StreamResource("invaild_code.gif", () -> getClass().getResourceAsStream("/static/img/invalid_code.gif"));
                Image logoImage = new Image(logoStream, "invalid_gif");
                layout.add(logoImage, new Text("Code ist ungültig :(."));
            } else {
                StreamResource logoStream = new StreamResource("vaild_code.gif", () -> getClass().getResourceAsStream("/static/img/valid_code.gif"));
                Image logoImage = new Image(logoStream, "valid_gif");
                layout.add(logoImage, new Text("Freischaltung erfolgreich :). Du bist nun im Email-Verteiler."));
                MailUser activatedUser = mailUserRepository.findByActivationCode_Code(code);
                activatedUser.setActivationCode(activationCodeRepository.findByCode("387UxMzB12").get(0));
                activatedUser.setEnabled(true);
                mailUserRepository.save(activatedUser);
                activationCodeRepository.delete(activationCodeRepository.findByCode(code).get(0));
                logger.info("User activated Account successfully: " + activatedUser.getEmail());
            }
        }catch (NullPointerException nullPointerException){
            logger.warn("User tried to navigate to ActivationView but there is no code");
            UI.getCurrent().navigate("login");
        }

    }

    @Override
    protected Component initContent() {
        layout = new VerticalLayout();
        return layout;
    }

}