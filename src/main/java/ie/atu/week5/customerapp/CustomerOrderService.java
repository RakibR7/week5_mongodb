package ie.atu.week5.customerapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {
    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    public CustomerOrderService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public CustomerOrderRequest CustomerWithOrders(CustomerOrderRequest customerOrderRequest){
        Optional<Customer> customer = customerRepository.findByEmail(customerOrderRequest.getCustomer().getEmail());


        if(customer.isEmpty()){
            customerRepository.save(customerOrderRequest.getCustomer());
            customer = Optional.ofNullable(customerOrderRequest.getCustomer());
        }

        ArrayList<Order> savedOrders = new ArrayList<>();

        for(Order order : customerOrderRequest.getOrders()){
            order.setCustomerId(customer.get().getId());
            savedOrders.add(orderRepository.save(order));
            orderRepository.save(order);
        }



        return new CustomerOrderRequest(customer.get(), savedOrders);
    }
}
