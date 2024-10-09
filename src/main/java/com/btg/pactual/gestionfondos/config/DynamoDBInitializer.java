package com.btg.pactual.gestionfondos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;

@Service
public class DynamoDBInitializer {

    private final DynamoDbClient dynamoDbClient;

    @Autowired
    public DynamoDBInitializer(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
        createTables();
    }

    private void createTables() {
        createTableIfNotExists("Fondos");
        createTableIfNotExists("Clientes");
        createTableIfNotExists("Suscripcion");
        createTableIfNotExists("Transaccion");
    }

    private void createTableIfNotExists(String tableName) {
        ListTablesResponse listTablesResponse = dynamoDbClient.listTables();
        List<String> tableNames = listTablesResponse.tableNames();

        if (!tableNames.contains(tableName)) {
            CreateTableRequest createTableRequest = CreateTableRequest.builder()
                    .tableName(tableName) // Nombre de la tabla
                    .keySchema(KeySchemaElement.builder()
                            .attributeName("id") // Nombre de la clave primaria
                            .keyType(KeyType.HASH) // Tipo de clave: HASH
                            .build())
                    .attributeDefinitions(AttributeDefinition.builder()
                            .attributeName("id")
                            .attributeType("S") // Tipo de dato de la clave primaria
                            .build())
                    .provisionedThroughput(ProvisionedThroughput.builder()
                            .readCapacityUnits(5L) // Unidades de lectura
                            .writeCapacityUnits(5L) // Unidades de escritura
                            .build())
                    .build();

            dynamoDbClient.createTable(createTableRequest);
            System.out.println("Tabla '" + tableName + "' creada.");
        } else {
            System.out.println("Tabla '" + tableName + "' ya existe.");
        }
    }
}
