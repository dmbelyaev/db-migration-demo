package com.example.dbmigrator;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DBMigrationApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBMigrationApp.class);

    public static void main(String[] args) throws SQLException, LiquibaseException {
        var start = System.currentTimeMillis();

        var connection = EnvDataSourceProvider.getDataSource().getConnection();

        var database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(
                        new JdbcConnection( connection )
                );
        var liquibase = new Liquibase(
                "db/changelog/changelog-master.xml",
                new ClassLoaderResourceAccessor(),
                database
        );
        liquibase.update(new Contexts(), new LabelExpression());

        LOGGER.info(
                "Executed {} migrations in {}ms.",
                0,
                System.currentTimeMillis() - start);
    }
}
