package de.uni.stuttgart.ipvs.tm.service;

import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.results.VariableBinding;
import de.uni.stuttgart.ipvs.tm.response.HardwareTypeItemDetails;
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

    static ResultsSet getAllHardwareTypesResultsSet(SelectResults selectResults) {

        var results = selectResults.getResults().getBindings().stream()
                .map(CviServiceUtils::geHardwareTypeItemDetails)
                .collect(toList());

        return new ResultsSet<>(results);
    }

    private static HardwareTypeItemDetails geHardwareTypeItemDetails(Map<String, VariableBinding> stringVariableBindingMap) {

        var hardwareTypeItemDetails = new HardwareTypeItemDetails();

        var categoryLabelVB = stringVariableBindingMap.get(QV_CATEGORY_LABEL.getVariableName());
        hardwareTypeItemDetails.setCategory(categoryLabelVB.getValue());

        var hardwareComponentLabelVB = stringVariableBindingMap.get(QV_HARDWARE_COMPONENT_LABEL.getVariableName());
        hardwareTypeItemDetails.setHardwareComponent(hardwareComponentLabelVB.getValue());

        hardwareTypeItemDetails.of(getSearchItemDetails(stringVariableBindingMap));
        return hardwareTypeItemDetails;
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
        String[] terms = termsText.toLowerCase().split("\\s+");

        List<String> finalTerms = new ArrayList<>(Arrays.asList(terms));
        finalTerms.add(requiredTermsTextFormat(termsText));

        return new HashSet<>(finalTerms);
    }

    static String requiredTermsTextFormat(String termsText) {
        return termsText.toLowerCase().replaceAll("\\s+", "-");
    }


}
