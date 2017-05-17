package checkers.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by david on 2017-05-16.
 */
public interface Json {


    default String asJson() {
        try {
            return new ObjectMapper()
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
