package de.uni.stuttgart.ipvs.tm.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import de.uni.stuttgart.ipvs.tm.persistence.CviRepository;
import de.uni.stuttgart.ipvs.tm.response.TermsMeaningResultsSet;

import java.util.Collection;

import static org.springframework.util.StringUtils.hasLength;

@Service
@Slf4j
public class CviService {

    private final CviRepository cviRepository;

    @Autowired
    public CviService(CviRepository cviRepository) {
        this.cviRepository = cviRepository;
    }

    public TermsMeaningResultsSet searchMeaningOfTermsText(String termsText) {
        return !hasLength(termsText) ?
                TermsMeaningResultsSet.EMPTY :
                searchMeaningOfTerms(
                        CviServiceUtils.requiredTermsTextFormat(termsText),
                        CviServiceUtils.termsTextToTerms(termsText)
                );
    }

    private TermsMeaningResultsSet searchMeaningOfTerms(String termsText, Collection<String> terms) {
        var selectResults = this.cviRepository.searchMeaningOfTerms(terms);

        return selectResults.isEmpty() ?
                TermsMeaningResultsSet.EMPTY :
                CviServiceUtils.getTermsMeaningResultsSet(termsText, selectResults);
    }


}
