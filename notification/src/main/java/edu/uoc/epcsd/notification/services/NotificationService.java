package edu.uoc.epcsd.notification.services;

import edu.uoc.epcsd.notification.kafka.ProductMessage;
import edu.uoc.epcsd.notification.rest.dtos.GetUserResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@Log4j2
@Component
public class NotificationService {

    @Value("${userService.getUsersToAlert.url}")
    private String userServiceUrl;

   // @Autowired
   // private UserService userService;

    public void notifyProductAvailable(ProductMessage productMessage) {

        // TODO: Use RestTemplate with the above userServiceUrl to query the User microservice in order to get the users that have an alert for the specified product (the date specified in the parameter may be the actual date: LocalDate.now()).
        //  Then simulate the email notification for the alerted users by logging a line with INFO level for each user saying "Sending an email to user " + the user fullName

   /*     try {
            RestTemplate restTemplate = new RestTemplate();

            String expandedUrl = UriComponentsBuilder.fromUriString(userServiceUrl)
                    .buildAndExpand(productMessage.getProductId(), LocalDate.now().toString())
                    .toUriString();
            ResponseEntity<GetUserResponse[]> response = restTemplate.getForEntity(expandedUrl, GetUserResponse[].class);

            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
                for (GetUserResponse user : userService.getMockUsers()){
                    log.info("LOG "+user.getFullName() + "Notification");
                }
            }else {
                log.error("Error getting user Alert" + response.getStatusCode());
            }
        } catch (Exception e){
            log.error("Error notifyProductAvailable: ", e);
        }*/
    }
}
