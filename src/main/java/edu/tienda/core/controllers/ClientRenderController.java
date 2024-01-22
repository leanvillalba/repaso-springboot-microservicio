package edu.tienda.core.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRenderController {

    @GetMapping(value = "/clients-html", produces = MediaType.TEXT_HTML_VALUE)
    /* El atributo “produces” dentro de la anotación @GetMapping indica que la devolución
    de este servicio será entregada en formato HTML*/
    public String getClientAsHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("  <div><h1>Client</h1>");
        sb.append("    <ul>");
        sb.append("      <li>Nombre: Leandro Villalba</li>");
        sb.append("      <li>UserName: LAV</li>");
        sb.append("    </ul>");
        sb.append("  </div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();

    }

    // Media tipo XML
    @GetMapping(value = "/clients-xml", produces = MediaType.TEXT_XML_VALUE)
    public String getClientAsXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("  <client>");
        sb.append("    <nombre>Nombre: Leandro Villalba</nombre>");
        sb.append("    <username>UserName: LAV</username>");
        sb.append("  </client>");
        sb.append("</xml>");
        return sb.toString();

    }
}
