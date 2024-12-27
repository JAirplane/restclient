package tacos.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import tacos.restclient.domain.Order;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrdersRequestService service;

    @Autowired
    public OrderController(OrdersRequestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return service.allOrders();
    }

    @GetMapping(path = "/{orderId}")
    public Order getOrderById(@PathVariable("orderId") Long orderId) {
        return service.getOrderById(orderId);
    }

    @PostMapping(consumes = "application/json")
    public Order postOrder(@RequestBody Order tacoOrder) {
        return service.postOrder(tacoOrder);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Order putOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
        return service.putOrder(orderId, order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
        return service.patchOrder(orderId, order);
    }

    @DeleteMapping(path = "/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        service.deleteOrder(orderId);
    }
}
