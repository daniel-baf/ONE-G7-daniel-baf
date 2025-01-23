package API.Domain;

import lombok.Data;

import java.util.List;

@Data
public class ListBooks {
    private int count;
    private String next;
    private String previous;
    private List<Book> books;

}
