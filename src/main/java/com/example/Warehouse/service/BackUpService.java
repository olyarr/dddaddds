package com.example.Warehouse.service;

import com.smattme.MysqlExportService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class BackUpService {
    public void back() throws SQLException, IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.setProperty(MysqlExportService.DB_NAME, "new_warehouse");
        properties.setProperty(MysqlExportService.DB_USERNAME, "root");
        properties.setProperty(MysqlExportService.DB_PASSWORD, "root");
        properties.setProperty(MysqlExportService.TEMP_DIR, "C:\\dump_database");
        properties.setProperty(MysqlExportService.PRESERVE_GENERATED_SQL_FILE, "true");

        MysqlExportService mysqlExportService = new MysqlExportService(properties);
        mysqlExportService.export();
    }
}
