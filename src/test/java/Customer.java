import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable{
    private long id;
    private String firstname;
    private String lastname;
    private String turnover;
    private String email;
    private String country;
}
