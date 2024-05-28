package by.aston.webservice.ticketmicroservice.config;

import by.aston.webservice.ticketmicroservice.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
//@RequiredArgsConstructor
public class KafkaConfigProducer {


   // private final Environment environment;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //    @Value("${spring.kafka.producer.key-serializer}")
//    private  String keySerializer;
//
//    @Value("${spring.kafka.producer.value-serializer}")
//    private  String valueSerializer;
//
    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private boolean idempotence;
//
//    @Value("${spring.kafka.topic.name}")
//    private String topicName;


    @Bean
    Map<String, Object> producerConfigs() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
                //.getProperty("spring.kafka.producer.bootstrap-servers"));
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG, acks);
                //environment.getProperty("spring.kafka.producer.acks"));
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, idempotence);
               // environment.getProperty("spring.kafka.producer.properties.enable.idempotence"));
        return config;
    }

    @Bean
    ProducerFactory<String, TicketDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String, TicketDto> kafkaTemplate() {
        return new KafkaTemplate<String, TicketDto>(producerFactory());
    }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("ticket-created-events-topic")
                .partitions(1)
                .replicas(1)
                .configs(Map.of("min.insync.replicas", "1"))
                .build();
    }
//    @Bean
//    public NewTopic phoneNumberUpdatesDLQTopic() {
//        return TopicBuilder.name("DLQTopicName")
//                .build();
//    }

}