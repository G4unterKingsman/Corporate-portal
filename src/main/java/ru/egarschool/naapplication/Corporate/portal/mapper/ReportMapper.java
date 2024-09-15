package ru.egarschool.naapplication.Corporate.portal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.egarschool.naapplication.Corporate.portal.dto.ReportDto;
import ru.egarschool.naapplication.Corporate.portal.entity.ReportEntity;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportDto toDto(ReportEntity byName);

    ReportEntity toEntity(ReportDto byName);

    /**
     * Обновление отчёта из DTO без поля userAccount, т.к оно задаётся вручную в сервисе
     */
    @Mapping(source = "linkedTask", target = "linkedTask.id", ignore = true)
    void toUpdateReportFromDto(ReportDto byName, @MappingTarget ReportEntity report);

}
