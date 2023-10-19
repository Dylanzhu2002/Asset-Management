package persistence;

import org.json.JSONObject;

// Use JsonSerializationDemo as the template
public interface Writable {
    JSONObject toJson();
}
