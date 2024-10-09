package com.btg.pactual.gestionfondos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.regions.Region;

@Configuration
public class DynamoDBConfig {
@Bean
public DynamoDbClient dynamoDbClient() {
    return DynamoDbClient.builder()
            .region(Region.US_EAST_1)
            .build();
}
}
