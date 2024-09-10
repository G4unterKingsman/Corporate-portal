package ru.egarschool.naapplication.Corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.egarschool.naapplication.Corporate.portal.dto.ReportDto;
import ru.egarschool.naapplication.Corporate.portal.entity.EmployeeEntity;
import ru.egarschool.naapplication.Corporate.portal.entity.ReportEntity;
import ru.egarschool.naapplication.Corporate.portal.exception.ReportNotFoundException;
import ru.egarschool.naapplication.Corporate.portal.mapper.ReportMapper;
import ru.egarschool.naapplication.Corporate.portal.repository.EmployeeRepo;
import ru.egarschool.naapplication.Corporate.portal.repository.ReportRepo;
import ru.egarschool.naapplication.Corporate.portal.service.intefraces.ReportService;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepo reportRepo;
    private final ReportMapper reportMapper;
    private final EmployeeRepo employeeRepo;
    private final SecurityService securityService;

    public List<ReportDto> findAll() {
        List<ReportEntity> reports = reportRepo.findAll();
        return reports.stream()
                .map(reportMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReportDto getById(Long id){
        ReportEntity report =  reportRepo.findById(id).orElseThrow(
                () ->  new ReportNotFoundException("Отчёт с идентификатором " + id + " не найден"));
        return reportMapper.toDto(report);
    }


    public ReportDto create(ReportDto reportDto){
        String username = securityService.getCurrentUsername();
        EmployeeEntity employee = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        reportDto.setReportEmploy(employee);

        ReportEntity report = reportMapper.toEntity(reportDto);
        reportRepo.save(report);
        return reportDto;
    }

    public ReportDto update(ReportDto reportDto, Long id) {
        String username = securityService.getCurrentUsername();
        EmployeeEntity employee = employeeRepo.findEmployeeEntityByUserAccount_Username(username).orElseThrow(
                () ->  new UsernameNotFoundException("Сотрудник с username " + username + " не найден"));
        reportDto.setReportEmploy(employee);

        ReportEntity report = reportRepo.findById(id).orElseThrow(
                () ->  new ReportNotFoundException("Отчёт с идентификатором " + id + " не найден"));
        report.setReportEmploy(employee);
        reportMapper.toUpdateReportFromDto(reportDto,report);

        reportRepo.save(report);
        return reportDto;
    }

    @Override
    public void delete(Long id) {
        reportRepo.deleteById(id);
    }


    public String getOwnerUsername(Long id) {
        ReportEntity report = reportRepo.findById(id).orElseThrow(
                () ->  new ReportNotFoundException("Отчёт с идентификатором " + id + " не найден"));
        return report.getReportEmploy().getUserAccount().getUsername();
    }
}
