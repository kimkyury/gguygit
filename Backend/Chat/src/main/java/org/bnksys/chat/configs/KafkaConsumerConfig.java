package org.bnksys.chat.configs;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.bnksys.chat.dtos.ChatMessageDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

// KafkaConsumerConfig.java (config)
@Configuration
public class KafkaConsumerConfig {

    /***
     * KafkaConsumer 인스턴스를 생성하는 팩토리
     * 입력된 groupId에 따라 다른 ConsumerFactory 생성
     * Kafka에서 수신한 메시지를 Deserialization 하여 ChatMessageDto 객체로 변환
     * @return
     */
    public ConsumerFactory<String, ChatMessageDto> consumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:10000,localhost:10001,localhost:10002");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(ChatMessageDto.class));
    }

    /***
     * KafkaListenerContainer 생성 역할
     * 메시지를 비동기로 수신하고 처리하는 데 사용 (수신된 메시지를 별도의 스레드에서 처리하도록 함)
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory("chat_group"));
        return factory;
    }

    /***
     * KafkaListenerContainer 생성 역할
     * 특정 이름의 채팅방에 대한 메시지를 수신하는 ConsumerFactory 생성
     * 메시지를 비동기로 수신하고 처리하는 데 사용 (수신된 메시지를 별도의 스레드에서 처리하도록 함)
     * @return
     */
    public ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto> kafkaListenerContainerFactory(String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(groupId));
        return factory;
    }

}
