package de.uni.stuttgart.ipvs.tm.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import de.uni.stuttgart.ipvs.tm.dto.ComponentTypeCreateDTO;
import de.uni.stuttgart.ipvs.tm.form.ComponentTypeFormModelValidation;
import de.uni.stuttgart.ipvs.tm.persistence.CviRepository;
import de.uni.stuttgart.ipvs.tm.response.ResultsSet;
import de.uni.stuttgart.ipvs.tm.response.TermsMeaningResultsSet;

import java.util.Collection;

import static org.springframework.util.StringUtils.hasLength;

@Service
@Slf4j
public class CviService {

    private final CviRepository cviRepository;
    private final ComponentTypeFormModelValidation ctFMV;

    @Autowired
    public CviService(CviRepository cviRepository,
                      ComponentTypeFormModelValidation ctFMV) {
        this.cviRepository = cviRepository;
        this.ctFMV = ctFMV;
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

    public ResultsSet getAllComponentTypes() {
        var selectResults = this.cviRepository.findAllComponentTypes();

        return selectResults.isEmpty() ? ResultsSet.EMPTY : CviServiceUtils.getAllComponentTypesResultsSet(selectResults);
    }

    public String createComponentType(ComponentTypeCreateDTO componentTypeCreateDTO) {

        this.ctFMV.validate(componentTypeCreateDTO);

        var componentType = ComponentType.build(componentTypeCreateDTO);
        this.cviRepository.save(componentType);

        return componentType.getSearchId();
    }


}
