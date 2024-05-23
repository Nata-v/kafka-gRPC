package by.aston.webservice.emailnotification.config;

import by.aston.webservice.emailnotification.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class KafkaConfig {
    @Autowired
     Environment environment;
    // @Value("${spring.kafka.consumer.bootstrap-servers}")
//   @Value("localhost:9092, localhost:9094")
//    private String bootstrapServers;
//
//    //@Value("${spring.kafka.consumer.group-id}")
//    @Value("order-created-events")
//    private String groupId;

    @Bean
    ConsumerFactory<String, TicketDto> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment
                .getProperty("spring.kafka.consumer.bootstrap-servers"));
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        config.put(ConsumerConfig.GROUP_ID_CONFIG, environment
                .getProperty("spring.kafka.consumer.group-id"));
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, environment
//                .getProperty("spring.kafka.consumer.properties.spring.json.trusted.packages"));

        JsonDeserializer<TicketDto> deserializer =
                new JsonDeserializer<>(TicketDto.class, false);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);

    }


    @Bean
    ConcurrentKafkaListenerContainerFactory<String, TicketDto> kafkaListenerContainerFactory(
            ConsumerFactory<String, TicketDto> consumerFactory, KafkaTemplate kafkaTemplate) {

        DefaultErrorHandler errorHandler = new DefaultErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate));

        ConcurrentKafkaListenerContainerFactory<String, TicketDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

//    @Bean
//    KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
//
//    @Bean
//    ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> config = new HashMap<>();
//
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment
//                .getProperty("spring.kafka.consumer.bootstrap-servers"));
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
}