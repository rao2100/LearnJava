package com.rao2100.producer;

import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.confluent.kafka.schemaregistry.json.JsonSchemaUtils;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.Producer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
//import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString;
//import java.time.LocalDate;

public class ProducerJsonUser {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, JsonNode> topicSchemaCache = new ConcurrentHashMap<>();
//    private final KafkaTemplate<JsonNode, JsonNode> kafkaTemplate;

    public void run() {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "odfhost1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer");
        props.put("schema.registry.url", "http://odfhost1:8081");



        Producer<String, JsonNode> producer = new KafkaProducer<String, JsonNode>(props);

//        String topic = "testjsonschema1";
        String topic = "topic-sink-json";
        String key = "testkey";

        String value = "{\"recordType\":\"DATA\",\"offerId\":10004,\"groupId\":\"G700_339347825\",\"subscriptionId\":\"G700_33934782514100045163245031310\",\"balanceId\":\"DomesticDataBalance&Ec5@1634610313;G700_339347825;1632450313\",\"offerEntitlementName\":\"MobileShareData9GB\",\"msisdn\":\"339347825\",\"imsi\":\"339347825\",\"subscriberId\":\"339347825\",\"subscriberType\":\"Consumer\",\"chargeType\":\"APPLIED\",\"recordOpeningTime\":1632450316,\"recordClosingTime\":1632450317,\"sessionId\":\"3fcce2dc-a8df-4a82-abda-4401a1af0f9a\",\"sgsnIpAddress\":null,\"mccmnc\":null,\"ggsnAddress\":null,\"ggsnChargingId\":null,\"apn\":\"broadband\",\"ratingGroup\":100,\"serviceId\":null,\"ratType\":\"NR\",\"userLocationInformation\":null,\"msTimeZone\":null,\"isRoaming\":true,\"zone\":\"US-DCA\",\"ccTime\":null,\"ccTotalOctets\":209715200,\"ccInputOctets\":null,\"ccOutputOctets\":null,\"terminationCause\":null,\"counterFinalValue\":209715200.000000000000,\"ratedCashValuePreTax\":null,\"ratedCashValuePostTax\":null,\"userEquipmentInfoType\":\"imeisv\",\"userEquipmentInfoValue\":\"Tset\",\"balanceImpactName\":\"DomesticDataBalance\",\"requestNumber\":2,\"imei\":\"imeisv-Tset\",\"otherPartyAddress\":null,\"classification\":null,\"directionType\":null,\"zoneCalled\":null,\"ccSpecificServiceUnits\":null,\"requestType\":\"UPDATE\",\"offerName\":\"MobileSharePlus9GB\",\"resultCode\":\"SUCCESS\",\"vlrId\":null,\"glId\":null,\"carrierId\":\"1\",\"roamingCountry\":\"US DCA\",\"accumulatedInBundle\":209715200,\"accumulatedOutOfBundle\":0,\"accumulatedNotApplied\":0,\"accumulatedUsageBalance\":null,\"accumulatedChargeBalance\":null,\"ratedCashValue\":null,\"sd\":null,\"sst\":1,\"_5qi\":null,\"preemptionCapability\":null,\"preemptionVulnerability\":null,\"priorityLevel\":null,\"averWindow\":null,\"maxDataBurstVol\":null,\"applicationServiceProviderId\":null,\"sponsorId\":null,\"quotaManagementIndicator\":null,\"status\":\"rated\",\"eventId\":\"46b86014-1cd6-11ec-9579-3a499b5cd9bd\",\"ratingIndicator\":1,\"timeZone\":\"Europe/Dublin\",\"bcdStartDay\":19,\"parallelImpactIndicator\":false,\"attributes\":{}}";
        JsonNode jsonNodeValue = null;
        try {
            jsonNodeValue = objectMapper.readTree(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        System.out.println(jsonNodeValue.toString());

        String schemaFilePath = "ccs-value-schema.json";
        JsonNode schema = readFileToJsonNode(schemaFilePath);
//        System.out.println(schema.toString());

        JsonNode jsonNodeValueWithSchema = getEnvelope("ccs-value-schema.json", jsonNodeValue);

//        System.out.println(jsonNodeValueWithSchema.toString());



        Instant start = Instant.now();
        for (int i = 0; i < 10000 ; i++) {
            ProducerRecord<String, JsonNode> record = new ProducerRecord<>(topic, String.valueOf(i), jsonNodeValueWithSchema);
//            try {
                producer.send(record);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        producer.flush();
        producer.close();

        System.out.println("Time elapsed: " + timeElapsed);


    }


    private JsonNode getEnvelope(final String schemaFilePath, final Object key) {
        final JsonNode schemaNode = getOrLoadSchema(schemaFilePath);
        final JsonNode payload = objectMapper.valueToTree(key);
        return JsonSchemaUtils.envelope(schemaNode, payload);
    }

    private JsonNode getOrLoadSchema(final String schemaFilePath) {
        return topicSchemaCache.computeIfAbsent(schemaFilePath, key ->
                readFileToJsonNode(schemaFilePath));
    }

    @SneakyThrows
    private JsonNode readFileToJsonNode(final String schemaFilePath) {
        return objectMapper.readTree(getFileAsIOStream(schemaFilePath));
    }

    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    private void senduser() {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "odfhost1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer");
        props.put("schema.registry.url", "http://odfhost1:8081");

        Producer<String, User> producer = new KafkaProducer<String, User>(props);

        String topic = "testjsonschema";
        String key = "testkey";
        User user = new User();
        user.firstName = "John";
        user.lastName = "Doe";
        user.age = 33;

        ProducerRecord<String, User> record
                = new ProducerRecord<String, User>(topic, key, user);

        try {
            producer.send(record).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        producer.close();

    }


}
