package payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserResponse(@JsonProperty("id") String id, @JsonProperty("date_registration") String date_registration) {
}
