package ru.egarschool.naapplication.Corporate.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;


@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Long> {

}
