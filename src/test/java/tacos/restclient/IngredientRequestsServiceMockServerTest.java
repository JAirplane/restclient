package tacos.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import tacos.restclient.domain.Ingredient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(IngredientRequestsService.class)
class IngredientRequestsServiceMockServerTest {

    @Autowired
    MockRestServiceServer server;

    @Autowired
    IngredientRequestsService ingredientRequestsService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getIngredientByIdTest() throws JsonProcessingException {
        Ingredient expectedIngredient = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);

        server.expect(requestTo("http://localhost:8080/api/ingredients/1"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(expectedIngredient), MediaType.APPLICATION_JSON));

        Ingredient actualIngredient = ingredientRequestsService.getIngredientById("1");
        assertThat(actualIngredient.getId()).isEqualTo("FLTO");
        assertThat(actualIngredient.getName()).isEqualTo("Flour Tortilla");
        assertThat(actualIngredient.getType()).isEqualTo(Ingredient.Type.WRAP);
    }
}