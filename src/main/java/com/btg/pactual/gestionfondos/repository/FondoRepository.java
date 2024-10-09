package com.btg.pactual.gestionfondos.repository;

import com.btg.pactual.gestionfondos.model.Fondo;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class FondoRepository {

    private final DynamoDbTable<Fondo> fondoTable;

    public FondoRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.fondoTable = dynamoDbEnhancedClient.table("Fondos", TableSchema.fromBean(Fondo.class));
    }

    // Método para guardar un fondo en DynamoDB
    public void save(Fondo fondo) {
        fondoTable.putItem(fondo);
    }

    // Método para buscar un fondo por su id
    public Fondo findById(String fondoId) {
        return fondoTable.getItem(r -> r.key(k -> k.partitionValue(fondoId)));
    }
}
