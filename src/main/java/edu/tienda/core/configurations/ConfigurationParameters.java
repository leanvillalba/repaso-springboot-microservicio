package edu.tienda.core.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration /*
Esta anotación es de suma importancia ya que su comportamiento es muy parecido
a la anotación @Service pero con la diferencia de que estos tipos de beans se
inicializan también por Spring de manera prematura pero con distinto propósito.
Los beans anotados con @Configuration, como lo denota la palabra, son clases generadas
con el propósito de servir como configuración global de la aplicación.
Es decir, en estas clases deberá radicar código fuente que tenga como finalidad
ejecutar alguna configuración inicial del sistema como es en este caso.
*/
@ConfigurationProperties(prefix = "app")
public class ConfigurationParameters {
    private String name;
    private String country;
    private String author;
    private String language;

}
