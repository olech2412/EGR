package com.example.application.views.main;

import com.example.application.JPA.MailUser;
import com.example.application.JPA.repository.ActivationCodeRepository;
import com.example.application.JPA.repository.MailUserRepository;
import com.example.application.JPA.repository.UsersRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Map;

@Route("activate")
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
                layout.add(new Text("Code ist ungültig :(."));
            } else {
                layout.add(new Text("Freischaltung erfolgreich :)."));
                MailUser activatedUser = mailUserRepository.findByActivationCode_Code(code);
                activatedUser.setActivationCode(activationCodeRepository.findByCode("387UxMzB12").get(0));
                activatedUser.setEnabled(true);
                mailUserRepository.save(activatedUser);
                activationCodeRepository.delete(activationCodeRepository.findByCode(code).get(0));
                logger.info("User activated Account succesfully: " + activatedUser.getEmail());
            }
        }catch (NullPointerException nullPointerException){
            logger.warn("User tried to navigate to ActivationView but there is no code");
            new RouterLink(MainView.class);
        }

    }

    @Override
    protected Component initContent() {
        layout = new VerticalLayout();
        return layout;
    }

}