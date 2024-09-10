package ru.egarschool.naapplication.Corporate.portal.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.egarschool.naapplication.Corporate.portal.dto.ReportDto;
import ru.egarschool.naapplication.Corporate.portal.service.ReportServiceImpl;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReportControllerTest {
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;

    @Mock
    private ReportServiceImpl reportService;

    @InjectMocks
    private ReportController reportController;

    @Test
    void TestGetAllReports() {
        when(reportService.findAll()).thenReturn(Arrays.asList(new ReportDto(), new ReportDto()));

        String view = reportController.getAllReports(model);

        assertEquals("all_reports", view);
        verify(reportService).findAll();
        verify(model).addAttribute(eq("reports"), anyList());
    }

    @Test
    void TestGetAddReportForm() {
        String view = reportController.getAddReportForm(model);

        assertEquals("add_report", view);
        verify(model).addAttribute(eq("report"), any(ReportDto.class));
    }

    @Test
    void TestCreateReport_valid() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = reportController.create(new ReportDto(), bindingResult);

        assertEquals("redirect:/all_reports", view);
        verify(reportService).create(any(ReportDto.class));
    }

    @Test
    void TestCreateReport_inValid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = reportController.create(new ReportDto(), bindingResult);

        assertEquals("add_report", view);
        verify(reportService, never()).create(any(ReportDto.class));
    }

    @Test
    void TestGetInfoReport() {
        ReportDto reportDto = new ReportDto();
        when(reportService.getById(1L)).thenReturn(reportDto);

        String view = reportController.getInfoReport(model, 1L);

        assertEquals("show_report", view);

        verify(reportService).getById(1L);
        verify(model).addAttribute(eq("report"), any(ReportDto.class));
    }

    @Test
    void TestGetUpdateForm() {
        ReportDto reportDto = new ReportDto();
        when(reportService.getById(1L)).thenReturn(reportDto);

        String view = reportController.getEditForm(model, 1L);

        assertEquals("edit_report", view);
        verify(reportService).getById(1L);
        verify(model).addAttribute("reportDto", reportDto);
    }

    @Test
    void testUpdate_Valid() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = reportController.update(new ReportDto(), bindingResult, 1L, model);

        assertEquals("redirect:/all_reports/1", view);
        verify(reportService).update(any(ReportDto.class), eq(1L));
    }

    @Test
    void testUpdate_inValid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = reportController.update(new ReportDto(), bindingResult, 1L, model);

        assertEquals("edit_report", view);
        verify(reportService, never()).update(any(ReportDto.class), eq(1L));
    }

    @Test
    void TestDeleteReport() {
        String view = reportController.deleteReport(1L);

        assertEquals("redirect:/all_reports", view);
        verify(reportService).delete(1L);
    }
}