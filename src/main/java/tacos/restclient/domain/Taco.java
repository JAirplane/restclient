package tacos.restclient.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private Long id;

    private String name;

    private Date createdAt = new Date();

    private List<Ingredient> ingredients;
}
