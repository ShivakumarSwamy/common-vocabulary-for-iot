package de.uni.stuttgart.ipvs.ilv;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

public class HttpEntityFactory {

    private static HttpHeaders baseHeaders;

    private HttpEntityFactory() {
        throw new IllegalStateException(getClass().getName());
    }

    public static HttpEntity getHttpEntityGraphUpdate(String updateFormString) {

        var body = new LinkedMultiValueMap<String, String>();
        body.add("update", updateFormString);

        return new HttpEntity<>(body, baseHeaders);
    }

    public static HttpEntity getHttpEntityAskQuery(String askQueryFormString) {
        return getHttpEntityQuery(askQueryFormString, MediaTypeConstants.TEXT_BOOLEAN, true);
    }

    public static HttpEntity getHttpEntitySelectQuery(String selectQueryFormString) {
        return getHttpEntitySelectQuery(selectQueryFormString, true);
    }

    public static HttpEntity getHttpEntitySelectQuery(String selectQueryFormString, boolean infer) {
        return getHttpEntityQuery(selectQueryFormString, MediaTypeConstants.SPARQL_RESULTS_JSON, infer);
    }

    private static HttpEntity getHttpEntityQuery(String queryFormString, MediaType mediaType, boolean infer) {

        var body = new LinkedMultiValueMap<String, Object>();
        body.add("query", queryFormString);
        body.add("infer", infer);

        var httpHeaders = new HttpHeaders(baseHeaders);
        httpHeaders.setAccept(List.of(mediaType));

        return new HttpEntity<>(body, httpHeaders);
    }

    static {
        baseHeaders = new HttpHeaders();
        baseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }
}
