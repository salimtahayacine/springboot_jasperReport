package org.stronglover.demospringbootandjasperrepport.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stronglover.demospringbootandjasperrepport.entity.Product;
import org.stronglover.demospringbootandjasperrepport.repository.ProductRepo;
import org.stronglover.demospringbootandjasperrepport.service.ReportService;

import java.util.Arrays;
import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ProductRepo productRepository;

//    public ReportController(ReportService reportService) {
//        this.reportService = reportService;
//    }
//private List<Product> getSampleProducts() {
//    return Arrays.asList(
//            new Product(1L, "Product A", 100.0),
//            new Product(2L, "Product B", 150.0),
//            new Product(3L, "Product C", 200.0)
//    );
//}

    @GetMapping("/report/pdf")
    public void generatePdfReport(HttpServletResponse response) throws Exception {
        List<Product> products = productRepository.findAll();
        byte[] report = reportService.generatePdfReport(products, "src/main/resources/reports/sample_report.jrxml");

        //byte[] report = reportService.generatePdfReport(products, "classpath:resources/reports/sample_report.jrxml");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
        response.getOutputStream().write(report);
        response.getOutputStream().flush();
    }

    @GetMapping("/report/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception {
        List<Product> products = productRepository.findAll();
       // byte[] report = reportService.generateExcelReport(products, "classpath:reports/sample_report.jrxml");
        byte[] report = reportService.generateExcelReport(products, "src/main/resources/reports/sample_report.jrxml");

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=report.xlsx");
        response.getOutputStream().write(report);
        response.getOutputStream().flush();
    }

    @GetMapping("/report/word")
    public void generateWordReport(HttpServletResponse response) throws Exception {
        List<Product> products = productRepository.findAll();
       // byte[] report = reportService.generateWordReport(products, "classpath:reports/sample_report.jrxml");
        byte[] report = reportService.generateWordReport(products, "src/main/resources/reports/sample_report.jrxml");

        response.setContentType("application/rtf");
        response.setHeader("Content-Disposition", "attachment; filename=report.rtf");
        response.getOutputStream().write(report);
        response.getOutputStream().flush();
    }
    @GetMapping("/report/word_docx")
    public void generateWordReport_DocX(HttpServletResponse response) throws Exception {
        List<Product> products = productRepository.findAll();
        byte[] report = reportService.generateWordReport(products, "src/main/resources/reports/sample_report.jrxml");

        // Set content type for DOCX
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-Disposition", "attachment; filename=report.docx");

        // Write the report to the response output stream
        response.getOutputStream().write(report);
        response.getOutputStream().flush();
    }
}
