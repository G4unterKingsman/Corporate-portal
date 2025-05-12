package ru.gaunter.app.corporate.portal.service.intefraces;

import ru.gaunter.app.corporate.portal.dto.ReportDto;

import java.util.List;

public interface ReportService {
    List<ReportDto> findAll();

    ReportDto getById(Long id);

    ReportDto create(ReportDto reportDto);

    ReportDto update(ReportDto reportDto, Long id);


    void delete(Long id);

}
