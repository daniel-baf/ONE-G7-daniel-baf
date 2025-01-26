package app.API.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

import org.fusesource.jansi.Ansi;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Project Gutenberg ID
    private String title; // Title of the book

    @ElementCollection
    private List<String> subjects; // List of subjects

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Person> authors; // List of authors

    @ManyToMany(cascade = CascadeType.ALL) // Use ALL for cascading all operations
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Person> translators; // List of translators (also authors)

    @ElementCollection
    private List<String> bookshelves; // List of bookshelves

    @ElementCollection
    private List<String> languages; // List of languages

    private Boolean copyright; // Boolean or null for copyright
    private String media_type; // Media type (e.g., text, audio, etc.)

    @ElementCollection
    private Map<String, String> formats; // Map for formats (e.g., "text/html" to the URL)
    private int download_count; // Number of downloads

    @ElementCollection
    private List<String> summaries; // List of summaries

    @Override
    public String toString() {
        String separator = "----------------------------------------------------------\n";
        String result = Ansi.ansi().fg(Ansi.Color.CYAN).a(separator).reset().toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("id: ").reset().a(id).a("\t\t").fg(Ansi.Color.GREEN).a("author: ")
                .reset().a(authors).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("title: ").reset().a(title).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("subjects: ").reset().a(subjects).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("translators: ").reset().a(translators).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("bookshelves: ").reset().a(bookshelves).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("languages: ").reset().a(languages).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("copyright: ").reset().a(copyright).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("media_type: ").reset().a(media_type).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("formats: ").reset().a(formats).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("download_count: ").reset().a(download_count).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.GREEN).a("summaries: ").reset().a(summaries).a("\n").toString();
        result += Ansi.ansi().fg(Ansi.Color.CYAN).a(separator).reset().toString();
        return result;
    }
}
