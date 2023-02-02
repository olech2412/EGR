package com.example.application.views.main;

import com.example.application.JPA.MailUser;
import com.example.application.JPA.repository.MailUserRepository;
import com.example.application.JPA.repository.VotingCodeRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Route("voting")
@PageTitle("Voting")
@AnonymousAllowed
public class VotingView extends Composite implements BeforeEnterObserver {
    Logger logger = LoggerFactory.getLogger(ActivationView.class);
    private VerticalLayout layout;
    private final VotingCodeRepository votingCodeRepository;
    private final MailUserRepository mailUserRepository;

    public VotingView(VotingCodeRepository votingCodeRepository, MailUserRepository mailUserRepository) {
        this.votingCodeRepository = votingCodeRepository;
        this.mailUserRepository = mailUserRepository;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            Map<String, List<String>> params = event.getLocation().getQueryParameters().getParameters();

            String code = params.get("code").get(0);

            if (votingCodeRepository.findByCode(code).isEmpty() || code.equals("xcfXGoXWdkWMk38")) {
                StreamResource logoStream = new StreamResource("404.gif", () -> getClass().getResourceAsStream("/static/img/404.gif"));
                Image logoImage = new Image(logoStream, "404_gif");
                layout.add(logoImage, new Text("Code ist ungültig :(. Du hast wahrscheinlich schon abgestimmt."));
                layout.setAlignItems(FlexComponent.Alignment.CENTER);
            } else {
                StreamResource logoStream = new StreamResource("vaild_code.gif", () -> getClass().getResourceAsStream("/static/img/valid_code.gif"));
                Image logoImage = new Image(logoStream, "valid_gif");
                layout.add(logoImage, new Text("Freischaltung erfolgreich :). Du bist nun im Email-Verteiler."));
                MailUser activatedUser = mailUserRepository.findByVotingCode_Code(code);
                activatedUser.setVotingCode(votingCodeRepository.findByCode("xcfXGoXWdkWMk38").get(0));
                mailUserRepository.save(activatedUser);
                votingCodeRepository.delete(votingCodeRepository.findByCode(code).get(0));
                logger.info("User activated Account successfully: " + activatedUser.getEmail());
            }
        } catch (NullPointerException nullPointerException) {
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
