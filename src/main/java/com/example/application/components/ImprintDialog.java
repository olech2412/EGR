package com.example.application.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ImprintDialog extends Dialog {

    public ImprintDialog() {
        add(new H2("Impressum"));
        add(new VerticalLayout(
                new H3("Angaben gemäß § 5 TMG"),
                new Span("Ole Einar Christoph"),
                new Span("Mannheimer Straße 5-7"),
                new Span("04209 Leipzig"),
                new Text(" "),
                new H3("Kontakt"),
                new Span("Telefon: 015734489797"),
                new Span("E-Mail: olechristoph2412@gmail.com")
        ));
    }
}
