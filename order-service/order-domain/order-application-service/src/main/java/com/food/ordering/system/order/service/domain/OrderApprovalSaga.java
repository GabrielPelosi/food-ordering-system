package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.event.EmptyEvent;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApprovalSaga implements SagaStep<RestaurantApprovalResponse, EmptyEvent, OrderCancelledEvent> {


    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher;



    @Override
    @Transactional
    public EmptyEvent process(RestaurantApprovalResponse restaurantApprovalResponse) {
        log.info("Approving order with id: {}", restaurantApprovalResponse.getOrderId());
        Order order = findOrder(restaurantApprovalResponse.getOrderId());
        orderDomainService.approveOrder(order);
        orderRepository.save(order);
        log.info("Order with id: {} is approved", order.getId().getValue());
        return EmptyEvent.INSTANCE;
    }

    @Override
    @Transactional
    public OrderCancelledEvent rollback(RestaurantApprovalResponse restaurantApprovalResponse) {
        log.info("Cancelling order with id: {}", restaurantApprovalResponse.getOrderId());
        Order order = findOrder(restaurantApprovalResponse.getOrderId());
        OrderCancelledEvent domainEvent =  orderDomainService.cancelOrderPayment(order, restaurantApprovalResponse.getFailureMessages(), orderCancelledPaymentRequestMessagePublisher);
        orderRepository.save(order);
        log.info("Order with id: {} is cancelling", order.getId().getValue());
        return domainEvent;
    }

    private Order findOrder(String orderId){
        Optional<Order> orderOptional =  orderRepository.findById(new OrderId(UUID.fromString(orderId)));

        if (orderOptional.isEmpty()){
            log.error("Order with id: {} could not be found!", orderId);
            throw new OrderNotFoundException("Order with id: " + orderId + " could not be found!");
        }
        return orderOptional.get();
    }


}
