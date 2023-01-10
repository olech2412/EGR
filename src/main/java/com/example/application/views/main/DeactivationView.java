package com.example.application.views.main;

import com.example.application.JPA.MailUser;
import com.example.application.JPA.repository.ActivationCodeRepository;
import com.example.application.JPA.repository.DeactivationCodeRepository;
import com.example.application.JPA.repository.MailUserRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Route("deactivate")
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
                layout.add(new Text("Code ist ungültig :(."));
            } else {
                layout.add(new Text("Abmeldung erfolgreich. Ich lösche alle deine persönlichen Daten :D."));
                MailUser activatedUser = mailUserRepository.findByActivationCode_Code(code);
                mailUserRepository.delete(activatedUser);
                deactivationCodeRepository.delete(deactivationCodeRepository.findByCode(code).get(0));
                logger.info("User deactivated Account succesfully: " + activatedUser.getEmail());
            }
        } catch (NullPointerException nullPointerException) {
            logger.warn("User tried to navigate to DeactivationView but there is no code");
            new RouterLink(MainView.class);
        }

    }

    @Override
    protected Component initContent() {
        layout = new VerticalLayout();
        return layout;
    }

}
