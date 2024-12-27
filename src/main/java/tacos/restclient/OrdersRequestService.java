package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tacos.restclient.domain.Ingredient;
import tacos.restclient.domain.Order;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@Service
@Slf4j
public class OrdersRequestService {
    @Value("${rest.baseurl}")
    private String baseUrl;

    @Value("${rest.api-url-part}")
    private String apiUrlPart;

    @Value("${rest.orders-endpoint}")
    private String ordersEndpoint;

    private final RestClient restClient;

    @Autowired
    public OrdersRequestService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Order> allOrders() {
        return restClient.get()
                .uri(baseUrl + apiUrlPart + ordersEndpoint)
                .accept(APPLICATION_JSON)
                .attributes(clientRegistrationId("taco-admin-client"))
                .retrieve()
                .body(new ParameterizedTypeReference<List<Order>>() {});
    }

    public Order getOrderById(Long id) {
        return restClient.get()
                .uri(baseUrl + apiUrlPart + ordersEndpoint + "/{id}", id)
                .attributes(clientRegistrationId("taco-admin-client"))
                .retrieve()
                .body(Order.class);
    }

    public Order postOrder(Order order) {
        return restClient.post()
                .uri(baseUrl + apiUrlPart + ordersEndpoint)
                .contentType(APPLICATION_JSON)
                .attributes(clientRegistrationId("taco-admin-client"))
                .body(order)
                .retrieve()
                .body(Order.class);
    }

    public Order putOrder(Long orderId, Order order) {
        return restClient.put()
                .uri(baseUrl + apiUrlPart + ordersEndpoint + "/{id}", orderId)
                .contentType(APPLICATION_JSON)
                .attributes(clientRegistrationId("taco-admin-client"))
                .body(order)
                .retrieve()
                .body(Order.class);
    }

    public Order patchOrder(Long orderId, Order order) {
        return restClient.patch()
                .uri(baseUrl + apiUrlPart + ordersEndpoint + "/{id}", orderId)
                .contentType(APPLICATION_JSON)
                .attributes(clientRegistrationId("taco-admin-client"))
                .body(order)
                .retrieve()
                .body(Order.class);
    }

    public void deleteOrder(Long orderId) {
        restClient.delete()
                .uri(baseUrl + apiUrlPart + ordersEndpoint + "/{id}", orderId)
                .attributes(clientRegistrationId("taco-admin-client"))
                .retrieve()
                .toBodilessEntity();
    }
}
