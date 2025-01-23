package API.Controller;

import API.Domain.ListBooks;
import API.Domain.SearchParameter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class APIController {

    private static final String BASE_URL = "https://gutendex.com/books";
    private final APIConsumer apiConsumer;

    public APIController() {
        this.apiConsumer = new APIConsumer();
    }

    /**
     * Retrieves a list of books from the API based on the specified page number.
     *
     * @param page the page number to fetch books from
     * @return a {@code ListBooks} object containing book data, including a list of books, the total count,
     *         and pagination information (next and previous page links)
     * @throws RuntimeException if an error occurs while fetching the books from the API
     */
    public ListBooks listBooks(int page) {
        String url = BASE_URL + "?page=" + page;
        try {
            return apiConsumer.fetchBooks(url);
        } catch (Exception e) {
            throw new RuntimeException("Error al listar libros: " + e.getMessage());
        }
    }

    /**
     * Searches for books using their unique identifiers by making an API request.
     *
     * @param ids an array of unique book identifiers to search for
     * @return a {@code ListBooks} object containing details of the matching books
     * @throws RuntimeException if an error occurs while fetching the books from the API
     */
    public ListBooks searchBooksById(int... ids) {
        String idList = Arrays.stream(ids)
                              .mapToObj(String::valueOf)
                              .collect(Collectors.joining(","));
        String url = BASE_URL + "?ids=" + idList;
        try {
            return apiConsumer.fetchBooks(url);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar libros por ID: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of books from the API based on the provided search filters, sorting, and pagination.
     *
     * @param filters a map of search parameters and their corresponding values to filter the books,
     *                where the key is a {@link SearchParameter} and the value is the filter string.
     * @param sort    the sorting order for the books (e.g., by title, author, etc.).
     * @param page    the page number for pagination.
     * @return a {@code ListBooks} object containing the filtered books, the count of books,
     *         and pagination-related data (next and previous page links).
     * @throws RuntimeException if an error occurs while constructing or sending the API request.
     */
    public ListBooks filterBooks(Map<SearchParameter, String> filters, String sort, int page) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?");
        // Add filters dynamically
        if (filters != null && !filters.isEmpty()) {
            filters.forEach((key, value) -> {
                if (value != null && !value.isEmpty()) {
                    urlBuilder.append(key.getValue()).append("=").append(value).append("&");
                }
            });
        }
        // Add sorting if specified
        if (sort != null && !sort.isEmpty()) {
            urlBuilder.append("sort=").append(sort).append("&");
        }
        // Add page parameter
        urlBuilder.append("page=").append(page);
        try {
            return apiConsumer.fetchBooks(urlBuilder.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error in dynamic filterBooks: " + e.getMessage());
        }
    }

}
