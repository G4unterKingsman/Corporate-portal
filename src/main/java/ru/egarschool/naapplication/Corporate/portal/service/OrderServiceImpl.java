package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.OrderRepo;
import ru.egarschool.naapplication.Corporate.portal.service.impl.OrderService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final EmployeeRepo employeeRepo;

    public List<OrderEntity> findAll() {return orderRepo.findAll();}

    public OrderEntity create(OrderEntity orderEntity, Long emplId){
        if(emplId != null) {
            EmployeeEntity employeeEntity = employeeRepo.findById(emplId).get();
            orderEntity.setOrderEmpl(employeeEntity);
        }
        return orderRepo.save(orderEntity);
    }


}
