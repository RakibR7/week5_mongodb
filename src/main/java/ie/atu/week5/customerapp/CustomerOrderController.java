package ie.atu.week5.customerapp;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerOrderController {

    private final CustomerOrderService customerOderService;

    public CustomerOrderController(CustomerOrderService customerOderService) {
        this.customerOderService = customerOderService;
    }

    @PostMapping("/customer-with-orders")
    public ResponseEntity<CustomerOrderRequest> createCustomerWithOrders(@RequestBody @Valid CustomerOrderRequest customerOrderRequest) {
        CustomerOrderRequest savedRequested = customerOderService.CustomerWithOrders(customerOrderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequested);
    }
}
