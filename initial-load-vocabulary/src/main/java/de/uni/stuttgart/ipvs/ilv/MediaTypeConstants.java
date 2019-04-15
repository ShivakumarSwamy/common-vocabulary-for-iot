package de.uni.stuttgart.ipvs.ilv;

import org.springframework.http.MediaType;

public class MediaTypeConstants {

    public static final MediaType TEXT_BOOLEAN = new MediaType("text", "boolean");
    public static final MediaType SPARQL_RESULTS_JSON = new MediaType("application", "sparql-results+json");

    private MediaTypeConstants() {
        throw new IllegalStateException(getClass().getName());
    }


}
