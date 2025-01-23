package API.Domain;

import lombok.Getter;

@Getter
public enum SearchParameter {
    AUTHOR("author"),
    TITLE("title"),
    TOPIC("topic"),
    RELEASE_DATE("release_date"),
    LANGUAGE("languages");

    private final String value;

    SearchParameter(String value) {
        this.value = value;
    }

}