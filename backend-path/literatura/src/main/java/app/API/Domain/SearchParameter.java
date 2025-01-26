package app.API.Domain;

import lombok.Getter;

@Getter
public enum SearchParameter {
    AUTHOR("author"),
    TITLE("title"),
    TOPIC("topic"),
    RELEASE_DATE("release_date"),
    LANGUAGE("languages"),
    AUTHOR_YEAR_START("author_year_start"),
    AUTHOR_YEAR_END("author_year_end"),
    COPYRIGHT("copyright"),
    IDS("ids"),
    MIME_TYPE("mime_type"),
    SEARCH("search"),
    SORT("sort");

    private final String value;

    SearchParameter(String value) {
        this.value = value;
    }
}
