package ru.egarschool.naapplication.Corporate.portal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.egarschool.naapplication.Corporate.portal.dto.OrderDto;
import ru.egarschool.naapplication.Corporate.portal.entity.OrderEntity;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(OrderEntity byName);

    OrderEntity toEntity(OrderDto byName);

    // @Mapping(source = "orderEmploy", target = "orderEmploy.id", ignore = true)
    void toUpdateOrderFromDto(OrderDto byName, @MappingTarget OrderEntity order);

}
