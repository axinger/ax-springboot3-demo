package com.axing.demo;

import com.google.gson.LongSerializationPolicy;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class GsonConfig {

    @Value("${spring.gson.date-format}")
    private String dateFormat;

    /**
     * 自定义gson配置
     */
    @Bean
    public GsonBuilderCustomizer customizer() {
        return b -> b
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter(dateFormat))
                // The date format will be used to serialize and deserialize Date and in case the java.sql module is present, also java.sql
                // .Timestamp and java.sql.Date.
                .setDateFormat(dateFormat)
                .serializeNulls()
                .create();
    }


    public static class LocalDateAdapter extends TypeAdapter<LocalDate> {


        @Override
        public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
            if (localDate == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(localDate.toString());
            }
        }

        @Override
        public LocalDate read(final JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else {
                return LocalDate.parse(jsonReader.nextString());
            }
        }
    }

    public static class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        private final String dateFormat;

        public LocalDateTimeAdapter(String dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public void write(final JsonWriter jsonWriter, final LocalDateTime localDate) throws IOException {
            if (localDate == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(localDate.format(DateTimeFormatter.ofPattern(dateFormat)));
            }
        }

        @Override
        public LocalDateTime read(final JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else {
                return LocalDateTime.parse(jsonReader.nextString(), DateTimeFormatter.ofPattern(dateFormat));
            }
        }
    }
}
