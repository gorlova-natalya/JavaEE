package freemarker;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
    Long id;
    String name;
    Integer age;
    String email;
}
