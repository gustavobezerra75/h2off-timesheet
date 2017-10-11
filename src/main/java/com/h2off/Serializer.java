package com.h2off;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.h2off.exceptions.ServiceException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author phil
 */
public class Serializer {

    private static Logger log = LoggerFactory.getLogger(Serializer.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";


    static Gson STANDARD = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .setDateFormat(DATE_FORMAT)
            .disableHtmlEscaping()
            .registerTypeAdapterFactory(new LowercaseEnumTypeAdapterFactory())
            .registerTypeAdapter(Ref.class, new RefAdapter())
            .registerTypeAdapter(Key.class, new KeyAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(DateTime.class, new DateTimeAdapter())
            .create();


    private static Gson serializer() {
        return STANDARD;
    }


    static String toJson(Object object) {
        return serializer().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return serializer().fromJson(json, type);
        } catch (Exception e) {
            throw new ServiceException("invalid json data: " + e.getMessage(), e);
        }
    }


    public static class LowercaseEnumTypeAdapterFactory implements TypeAdapterFactory {

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (!rawType.isEnum()) {
                return null;
            }

            final Map<String, T> lowercaseToConstant = new HashMap<String, T>();
            for (T constant : rawType.getEnumConstants()) {
                lowercaseToConstant.put(toLowercase(constant), constant);
            }

            return new TypeAdapter<T>() {
                public void write(JsonWriter out, T value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                    } else {
                        out.value(toLowercase(value));
                    }
                }

                public T read(JsonReader reader) throws IOException {
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        return null;
                    } else {
                        return lowercaseToConstant.get(reader.nextString());
                    }
                }
            };
        }

        private String toLowercase(Object o) {
            return o.toString().toLowerCase();
        }
    }


    public static class RefAdapter implements JsonSerializer<Ref>, JsonDeserializer<Ref> {

        @Override
        public JsonElement serialize(Ref ref, Type type, JsonSerializationContext jsc) {

            if (ref == null) {
                return JsonNull.INSTANCE;
            }


            if (!ref.isLoaded()) {
                log.warn("Serialising a Ref that wasn't already loaded. If you see lots of these it's a bad thing");
            }

            Object o = ref.get();
            return jsc.serialize(o);

        }

        @Override
        public Ref deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

            log.debug("element {}", je);
            log.debug("type {}", type);

            return null;
        }

    }


    public static class KeyAdapter implements JsonDeserializer<Key>, JsonSerializer<Key> {


        @Override
        public JsonElement serialize(Key key, Type type, JsonSerializationContext jsc) {

            if (key == null) {
                return JsonNull.INSTANCE;
            }

            if (key.getId() > 0) {
                return new JsonPrimitive(key.getId());
            }

            return new JsonPrimitive(key.getName());

        }

        @Override
        public Key deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            return null;
        }

    }


    public static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        private static final DateTimeFormatter FORMATTER = ISODateTimeFormat.date();

        @Override
        public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext jsc) {
            if (date == null) {
                return JsonNull.INSTANCE;
            }

            return new JsonPrimitive(date.toString(FORMATTER));

        }

        @Override
        public LocalDate deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

            if (!je.isJsonNull()) {
                String s = je.getAsString();
                try {
                    return LocalDate.parse(s, FORMATTER);
                } catch (Exception e) {
                    throw new JsonParseException("invalid date format - should be 'yyyy-MM-dd'");
                }

            }

            return null;

        }


    }

    private static final DateTimeZone PACIFIC_ZONE = DateTimeZone.forID("Pacific/Auckland");

    private static DateTimeFormatter DT_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm Z").withZone(PACIFIC_ZONE);

    public static class DateTimeAdapter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

        private static final DateTimeFormatter FORMATTER = ISODateTimeFormat.dateTime().withZone(DateTimeZone.forID("Pacific/Auckland"));

        @Override
        public JsonElement serialize(DateTime date, Type type, JsonSerializationContext jsc) {
            if (date == null) {
                return JsonNull.INSTANCE;
            }

            return new JsonPrimitive(date.toString(FORMATTER));

        }

        @Override
        public DateTime deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {

            if (!je.isJsonNull()) {
                String s = je.getAsString();
                try {
                    return DateTime.parse(s, DT_FORMATTER);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new JsonParseException("invalid datetime format - should be 'yyyy-MM-dd'T'HH:mm:ssZ'");
                }

            }

            return null;

        }


    }


}
