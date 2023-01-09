package com.example.application.views.main;

import com.example.application.JPA.User;
import com.example.application.JPA.repository.UserRepository;
import com.example.application.components.ImprintDialog;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@PageTitle("EssensGetter-Registration")
@Route(value = "")
public class MainView extends HorizontalLayout implements BeforeEnterObserver {

    Logger logger = LoggerFactory.getLogger(MainView.class);
    private final String welcomeText = "Welcome to EssensGetter-Registration!";
    private EmailField emailField;
    private Button registerButton;
    private Checkbox accept;

    @Autowired
    private UserRepository userRepository;

    public MainView() {

        setMargin(true);

        VerticalLayout mainLayout = init();

        mainLayout.setWidth(100f, Unit.PERCENTAGE);
        mainLayout.setHeight(100f, Unit.PERCENTAGE);
        mainLayout.setAlignItems(Alignment.CENTER);
        add(mainLayout);
    }

    private VerticalLayout init() {
        emailField = new EmailField("Deine E-Mail-Adresse");
        emailField.setRequiredIndicatorVisible(true);
        emailField.setClearButtonVisible(true);
        emailField.setErrorMessage("Bitte gib eine gültige E-Mail-Adresse ein!");
        emailField.setMaxLength(255);
        emailField.setPlaceholder("email@example.de");
        emailField.setWidth(100f, Unit.PERCENTAGE);
        registerButton = new Button("Registriere dich");

        registerButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
        registerButton.setIcon(new Icon("vaadin", "envelope-open-o"));

        registerButton.addClickListener(e -> {
            logger.info("Register button clicked");
            validateInput(emailField.getValue());
        });
        registerButton.addClickShortcut(Key.ENTER);

        VerticalLayout mainLayout = new VerticalLayout(new H1(welcomeText)); // the mainlayout is the layout of the whole page
        VerticalLayout inputLayout = new VerticalLayout(emailField, createInfoText(), registerButton); // the inputlayout is the layout of the input fields
        inputLayout.setAlignItems(Alignment.CENTER);
        inputLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        inputLayout.setWidth(40f, Unit.PERCENTAGE);

        mainLayout.add(inputLayout, createFooter());
        return mainLayout;
    }

    private Component createFooter() {
        Button imprint = new Button("Impressum");
        imprint.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
        imprint.setIcon(new Icon("vaadin", "info-circle-o"));
        imprint.addClickListener(e -> {
            logger.info("Imprint button clicked");
            ImprintDialog dialog = new ImprintDialog();
            dialog.open();
        });

        Button privacy = new Button("Datenschutzerklärung");
        privacy.addClickListener(e -> {
            logger.info("Privacy button clicked");
            UI.getCurrent().navigate("datenschutzerklärung");
        });
        privacy.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
        privacy.setIcon(new Icon("vaadin", "bookmark-o"));


        VerticalLayout footer = new VerticalLayout(new HorizontalLayout(imprint, privacy), new H6("Made with ❤️ " +
                "by  christopho"), new Span("© 2023 EssensGetter-Registration"));
        footer.setAlignItems(Alignment.CENTER);
        footer.setAlignSelf(Alignment.CENTER);
        footer.setJustifyContentMode(JustifyContentMode.CENTER);
        footer.setSizeFull();
        return footer;
    }

    private VerticalLayout createInfoText() {
        Span infoText = new Span("Durch Anklicken des Buttons \"Registriere dich\" erklärst du dich damit einverstanden, " +
                "dass dir täglich der Speiseplan der Mensa in der Schönauer Straße per E-Mail zuschickt. Du kannst dich " +
                "jederzeit von diesem Newsletter abmelden.");
        infoText.getStyle().set("text-align", "center");

        accept = new Checkbox("Ich stimme zu, dass meine personenbezogenen Daten - wie in der " +
                "Datenschutzerklärung beschrieben - zur Zusendung der E-Mail verarbeitet werden. Diese Zustimmung " +
                "kann ich jederzeit mit Wirkung für die Zukunft widerrufen."); // TODO: add link to privacy policy
        accept.setRequiredIndicatorVisible(true);
        accept.getStyle().set("text-align", "center");

        VerticalLayout infoLayout = new VerticalLayout(new H3("Informationen zu deiner Registrierung: "), infoText, accept);
        infoLayout.setAlignItems(Alignment.CENTER);

        return infoLayout;
    }

    /**
     * Validates the input of the user
     *
     * @param email the email of the user
     */
    private void validateInput(String email) {
        Pattern special = Pattern.compile("[!#$%&*()=|<>?{}\\[\\]~]"); // regex for special characters
        Matcher hasSpecial = special.matcher(email); // checks if the email contains special characters
        if (!email.isEmpty() && !hasSpecial.find() && !emailField.isInvalid()) { // if the email is not empty and does not contain special characters and is valid
            if(email.length() >= 255) {
                if (accept.getValue()) {
                    Notification notification = new Notification("Deine E-Mail-Adresse wurde erfolgreich registriert!. Klicke auf den Link in deiner Bestätigungsmail", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.open();
                    createRegistratedUser(email);
                } else {
                    Notification notification = new Notification("Bitte stimme der Datenschutzerklärung zu", 3000, Notification.Position.BOTTOM_START);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.open();
                }
            } else {
                Notification notification = new Notification("Deine E-Mail-Adresse ist zu lang!", 3000, Notification.Position.BOTTOM_START);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.open();
            }
        } else {
            Notification notification = new Notification("Bitte gib eine gültige E-Mail-Adresse ein!", 3000, Notification.Position.BOTTOM_START);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
        }
    }

    /**
     * Save User in Database
     * User is not enabled because he didnt verified the email
     * @param email
     */
    private void createRegistratedUser(String email) {
        userRepository.save(new User(email, false)); // save the user in the database, not enabled because he didnt verified the email
    }

    /**
     * log an access on the website
     * IP and information about the webbrowser is logged
     * @param beforeEnterEvent
     */
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        logger.info("User accessed the main page: " + UI.getCurrent().getSession().getBrowser().getBrowserApplication() + " IP: " + UI.getCurrent().getSession().getBrowser().getAddress());
    }
}
