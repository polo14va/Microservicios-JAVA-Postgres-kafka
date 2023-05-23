package edu.uoc.epcsd.notification.services;

import edu.uoc.epcsd.notification.kafka.ProductMessage;
import edu.uoc.epcsd.notification.rest.dtos.GetUserResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Log4j2
@Component
public class NotificationService {

    @Value("${userService.getUsersToAlert.url}")
    private String userServiceUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void notifyProductAvailable(ProductMessage productMessage) {

        // TODO: Use RestTemplate with the above userServiceUrl to query the User microservice in order to get the users that have an alert for the specified product (the date specified in the parameter may be the actual date: LocalDate.now()).
        //  Then simulate the email notification for the alerted users by logging a line with INFO level for each user saying "Sending an email to user " + the user fullNam
        String updatedUserServiceUrl = userServiceUrl.replace("{productId}", String.valueOf(productMessage.getProductId()))
                .replace("{availableOnDate}", LocalDate.now().toString());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(updatedUserServiceUrl);

        String uriString = builder.toUriString();
        log.info("uriString:" + uriString);

        WebClient webClient = webClientBuilder.build();
        Mono<GetUserResponse[]> response = webClient.get()
                .uri(uriString)
                .retrieve()
                .bodyToMono(GetUserResponse[].class);

        GetUserResponse[] users = response.block();


        if (users != null) {
            for (GetUserResponse user : users) {
                log.warn("Sending an email to user " + user.getFullName());
            }
        } else {
            log.error("Failed to get users from User Service.");
        }

    }
}
