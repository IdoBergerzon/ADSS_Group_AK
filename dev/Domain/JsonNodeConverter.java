package Domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonNodeConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static <T> JsonNode toJsonNode(T object) {
        return objectMapper.convertValue(object, JsonNode.class);
    }

    public static <T> T fromJsonNode(JsonNode jsonNode, Class<T> clazz) {
        if (jsonNode.isNull()) {
            return null;
        }
        return objectMapper.convertValue(jsonNode, clazz);
    }

}
