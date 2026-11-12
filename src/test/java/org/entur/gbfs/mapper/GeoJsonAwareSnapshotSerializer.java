package org.entur.gbfs.mapper;

import au.com.origin.snapshots.jackson.serializers.v1.DeterministicJacksonSnapshotSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.geojson.LngLatAlt;

import java.io.IOException;

/**
 * The snapshot library serializes with Jackson 2, but geojson-jackson 3.0 only ships
 * Jackson 3 (de)serializers for {@link LngLatAlt}. Without this, Jackson 2 writes
 * coordinates as beans instead of the GeoJSON position array. This restores the GeoJSON
 * position format (longitude, latitude, optional altitude and additional elements).
 */
public class GeoJsonAwareSnapshotSerializer extends DeterministicJacksonSnapshotSerializer {

    @Override
    public void configure(ObjectMapper objectMapper) {
        super.configure(objectMapper);
        SimpleModule module = new SimpleModule();
        module.addSerializer(LngLatAlt.class, new LngLatAltSerializer());
        objectMapper.registerModule(module);
    }

    private static class LngLatAltSerializer extends StdSerializer<LngLatAlt> {

        LngLatAltSerializer() {
            super(LngLatAlt.class);
        }

        @Override
        public void serialize(LngLatAlt value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeStartArray();
            gen.writeNumber(value.getLongitude());
            gen.writeNumber(value.getLatitude());
            if (value.hasAltitude()) {
                gen.writeNumber(value.getAltitude());
                if (value.hasAdditionalElements()) {
                    for (double element : value.getAdditionalElements()) {
                        gen.writeNumber(element);
                    }
                }
            }
            gen.writeEndArray();
        }
    }
}
