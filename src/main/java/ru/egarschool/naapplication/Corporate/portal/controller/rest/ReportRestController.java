package ru.egarschool.naapplication.Corporate.portal.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.egarschool.naapplication.Corporate.portal.dto.ReportDto;
import ru.egarschool.naapplication.Corporate.portal.service.ReportServiceImpl;

import java.util.List;


@RestController
@RequestMapping("api/reports")
@RequiredArgsConstructor
public class ReportRestController {

    private final ReportServiceImpl reportService;
    @GetMapping
    public List<ReportDto> getAllReports(){
        return reportService.findAll();
    }

    @GetMapping("/{id}")
    public ReportDto getReportById(@PathVariable Long id){
        return reportService.getById(id);
    }

    @PostMapping
    public ReportDto createReport(@RequestBody ReportDto reportDto) {
        return reportService.create(reportDto);
    }

    @PutMapping("/{id}")
    public ReportDto updateReport(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        return reportService.update(reportDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportService.delete(id);
    }
}
