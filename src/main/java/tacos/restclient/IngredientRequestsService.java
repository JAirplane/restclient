package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestClient;
import tacos.restclient.domain.Ingredient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;


@Service
@Slf4j
public class IngredientRequestsService {

    @Value("${rest.baseurl}")
    private String baseUrl;

    @Value("${rest.api-url-part}")
    private String apiUrlPart;

    @Value("${rest.ingredients-endpoint}")
    private String ingredientsEndpoint;

    private final RestClient restClient;

    @Autowired
    public  IngredientRequestsService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restClient.get()
                .uri(baseUrl + apiUrlPart + ingredientsEndpoint + "/{ingredientId}", ingredientId)
                .attributes(clientRegistrationId("taco-admin-client"))
                .retrieve()
                .body(Ingredient.class);
    }

    public List<Ingredient> getAllIngredients() {
        return restClient.get()
                .uri(baseUrl + apiUrlPart + ingredientsEndpoint)
                .attributes(clientRegistrationId("taco-admin-client"))
                .retrieve()
                .body(new ParameterizedTypeReference<List<Ingredient>>() {});
    }

    public Ingredient addNewIngredient(Ingredient newIngredient) {
        ResponseEntity<Ingredient> response = restClient.post()
                .uri(baseUrl + apiUrlPart + ingredientsEndpoint)
                .attributes(clientRegistrationId("taco-admin-client"))
                .body(newIngredient)
                .retrieve()
                .toEntity(Ingredient.class);
        log.info("Response status: {}", response.getStatusCode().value());
        return response.getBody();
    }

    public void deleteIngredient(String ingredientId) {
        ResponseEntity<Void> response = restClient.delete()
                .uri(baseUrl + apiUrlPart + ingredientsEndpoint + "/{ingredientId}", ingredientId)
                .attributes(clientRegistrationId("taco-admin-client"))
                .retrieve()
                .toBodilessEntity();
        log.info("Response status: {}", response.getStatusCode().value());
    }
}
