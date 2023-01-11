package com.example.application.views.main;

import com.example.application.JPA.MailUser;
import com.example.application.JPA.repository.ActivationCodeRepository;
import com.example.application.JPA.repository.DeactivationCodeRepository;
import com.example.application.JPA.repository.MailUserRepository;
import com.example.application.email.Mailer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

@Route("deactivate")
@PageTitle("Deaktivierung")
@AnonymousAllowed
public class DeactivationView extends Composite implements BeforeEnterObserver {
    private VerticalLayout layout;
    Logger logger = LoggerFactory.getLogger(DeactivationView.class);
    private DeactivationCodeRepository deactivationCodeRepository;
    private MailUserRepository mailUserRepository;
    public DeactivationView(DeactivationCodeRepository deactivationCodeRepository, MailUserRepository mailUserRepository) {
        this.deactivationCodeRepository = deactivationCodeRepository;
        this.mailUserRepository = mailUserRepository;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            Map<String, List<String>> params = event.getLocation().getQueryParameters().getParameters();

            String code = params.get("code").get(0);

            if (deactivationCodeRepository.findByCode(code).isEmpty()) {
                StreamResource logoStream = new StreamResource("invaild_code.gif", () -> getClass().getResourceAsStream("/static/img/invalid_code.gif"));
                Image logoImage = new Image(logoStream, "invalid_gif");
                layout.add(logoImage, new Text("Code ist ungültig :(."));
            } else {
                StreamResource logoStream = new StreamResource("vaild_code.gif", () -> getClass().getResourceAsStream("/static/img/valid_code.gif"));
                Image logoImage = new Image(logoStream, "valid_gif");
                layout.add(logoImage, new Text("Abmeldung erfolgreich. Ich lösche alle deine persönlichen Daten :D."));
                MailUser activatedUser = mailUserRepository.findByDeactivationCode_Code(code);
                mailUserRepository.delete(activatedUser);
                deactivationCodeRepository.delete(activatedUser.getDeactivationCode());
                logger.info("User deactivated Account successfully: " + activatedUser.getEmail());
                Mailer mailer = new Mailer();
                mailer.sendDeactivationEmail(activatedUser.getFirstname(), activatedUser.getEmail());
                logger.info("User deactivated Account successfully");
            }
        } catch (NullPointerException nullPointerException) {
            logger.warn("User tried to navigate to DeactivationView but there is no code");
            UI.getCurrent().navigate("login");
        } catch (MessagingException e) {
            logger.error("Error while sending deactivation email");
            throw new RuntimeException(e);
        }

    }

    @Override
    protected Component initContent() {
        layout = new VerticalLayout();
        return layout;
    }

}
