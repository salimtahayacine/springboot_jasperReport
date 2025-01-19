package org.stronglover.demospringbootandjasperrepport.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Service;
import org.stronglover.demospringbootandjasperrepport.entity.Product;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generatePdfReport(List<Product> products, String reportTemplate) throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        return outputStream.toByteArray();
    }
    public byte[] generateExcelReport(List<Product> products, String reportTemplate) throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       // JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        exporter.exportReport();
        return outputStream.toByteArray();
    }

//    public byte[] generateWordReport(List<Product> products, String reportTemplate) throws JRException {
//        JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate);
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
////        JRRtfExporter exporter = new JRRtfExporter();
////        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
////        exporter.setExporterOutput((WriterExporterOutput) new SimpleOutputStreamExporterOutput(outputStream));
////
////        exporter.exportReport();
//        JRRtfExporter exporter = new JRRtfExporter();
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput((WriterExporterOutput) new SimpleOutputStreamExporterOutput(outputStream));
//        SimpleRtfExporterConfiguration configuration = new SimpleRtfExporterConfiguration();
//        exporter.setConfiguration(configuration);
//        exporter.exportReport();
//        return outputStream.toByteArray();
//    }
public byte[] generateWordReport(List<Product> products, String reportTemplate) throws JRException {
    JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate);
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // For RTF export
    // JRRtfExporter exporter = new JRRtfExporter();
    // exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    // exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
    // SimpleRtfExporterConfiguration configuration = new SimpleRtfExporterConfiguration();
    // exporter.setConfiguration(configuration);
    // exporter.exportReport();

    // For DOCX export (if you want a Word document)
    JRDocxExporter exporter = new JRDocxExporter();
    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
    SimpleDocxExporterConfiguration configuration = new SimpleDocxExporterConfiguration();
    exporter.setConfiguration(configuration);
    exporter.exportReport();

    return outputStream.toByteArray();
}

}
