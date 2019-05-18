package de.uni.stuttgart.ipvs.tm.service;

import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.results.VariableBinding;
import de.uni.stuttgart.ipvs.tm.response.ComponentTypeItemDetails;
import de.uni.stuttgart.ipvs.tm.response.ResultsSet;
import de.uni.stuttgart.ipvs.tm.response.SearchItemDetails;
import de.uni.stuttgart.ipvs.tm.response.TermsMeaningResultsSet;

import java.util.*;

import static de.uni.stuttgart.ipvs.tm.constant.QVExprConstants.*;
import static java.util.stream.Collectors.toList;

public class CviServiceUtils {

    private CviServiceUtils() {
        throw new IllegalStateException(getClass().getName());
    }


    static TermsMeaningResultsSet getTermsMeaningResultsSet(String termsText, SelectResults selectResults) {

        var results = selectResults.getResults().getBindings().stream()
                .map(CviServiceUtils::getSearchItemDetails)
                .collect(toList());

        var exactResults = results.stream()
                .filter(searchItemDetails -> searchItemDetails.getSearchId().equals(termsText))
                .collect(toList());

        var relatedResults = results.stream()
                .filter(searchItemDetails -> !searchItemDetails.getSearchId().equals(termsText))
                .collect(toList());

        return new TermsMeaningResultsSet(exactResults, relatedResults);
    }

    static ResultsSet getAllComponentTypesResultsSet(SelectResults selectResults) {

        var results = selectResults.getResults().getBindings().stream()
                .map(CviServiceUtils::getComponentTypeItemDetails)
                .collect(toList());

        return new ResultsSet<>(results);
    }

    private static ComponentTypeItemDetails getComponentTypeItemDetails(Map<String, VariableBinding> stringVariableBindingMap) {

        var componentTypeItemDetails = new ComponentTypeItemDetails();

        var categoryLabelVB = stringVariableBindingMap.get(QV_CATEGORY_LABEL.getVariableName());
        componentTypeItemDetails.setCategory(categoryLabelVB.getValue());

        var componentLabelVB = stringVariableBindingMap.get(QV_COMPONENT_LABEL.getVariableName());
        componentTypeItemDetails.setComponent(componentLabelVB.getValue());

        componentTypeItemDetails.of(getSearchItemDetails(stringVariableBindingMap));
        return componentTypeItemDetails;
    }

    private static SearchItemDetails getSearchItemDetails(Map<String, VariableBinding> stringVariableBindingMap) {

        var searchItemDetails = new SearchItemDetails();

        var searchIdVB = stringVariableBindingMap.get(QV_SEARCH_ID.getVariableName());
        searchItemDetails.setSearchId(searchIdVB.getValue());

        var labelVB = stringVariableBindingMap.get(QV_LABEL.getVariableName());
        searchItemDetails.setLabel(labelVB.getValue());

        var commentVB = stringVariableBindingMap.get(QV_COMMENT.getVariableName());
        searchItemDetails.setComment(commentVB.getValue());

        return searchItemDetails;
    }

    static Collection<String> termsTextToTerms(String termsText) {
        String[] terms = termsText.trim().toLowerCase().split("\\s+");

        List<String> finalTerms = new ArrayList<>(Arrays.asList(terms));
        finalTerms.add(requiredTermsTextFormat(termsText));

        return new HashSet<>(finalTerms);
    }

    static String requiredTermsTextFormat(String termsText) {
        return termsText.trim().toLowerCase().replaceAll("\\s+", "-");
    }


}
