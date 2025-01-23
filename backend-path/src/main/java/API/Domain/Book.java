package API.Domain;

import lombok.Data; // Utiliza Lombok para simplificar getters, setters, etc.

import java.util.List;
import java.util.Map;

@Data
public class Book {

    private int id; // Project Gutenberg ID
    private String title; // Title of the book
    private List<String> subjects; // List of subjects
    private List<Person> authors; // List of authors (Person objects)
    private List<Person> translators; // List of translators (Person objects)
    private List<String> bookshelves; // List of bookshelves
    private List<String> languages; // List of languages
    private Boolean copyright; // Boolean or null for copyright
    private String media_type; // Media type (e.g., text, audio, etc.)
    private Map<String, String> formats; // Map for formats (e.g., "text/html" to the URL)
    private int download_count; // Number of downloads
}