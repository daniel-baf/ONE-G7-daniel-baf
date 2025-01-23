package API.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListBooks {
    private int count;
    private String next;
    private String previous;

    @JsonProperty("results")
    private List<Book> books;

}
