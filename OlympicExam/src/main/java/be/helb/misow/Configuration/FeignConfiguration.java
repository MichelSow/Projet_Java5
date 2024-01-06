package be.helb.misow.Configuration;

import be.helb.misow.client.DataAccessSponsor;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuration pour Feign.
 * Utilisée pour configurer les clients Feign pour interagir avec des API externes.
 */
@Configuration
public class FeignConfiguration {

    /**
     * Configure et fournit un bean DataAccessSponsor.
     * Ce bean est utilisé pour communiquer avec l'API externe Olympic 2.
     *
     * @return Le client Feign configuré pour accéder aux endpoints de l'API Olympic 2.
     */
    @Bean
    public DataAccessSponsor dataAccessSponsor() {
        return Feign.builder()
                .decoder(new JacksonDecoder()) // Utilise Jackson pour la désérialisation des réponses JSON.
                .target(DataAccessSponsor.class, "http://localhost:8081"); // Spécifie l'URL de base de l'API Olympic 2.
    }

}
