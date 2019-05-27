package de.uni.stuttgart.ipvs.em.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import de.uni.stuttgart.ipvs.em.dto.ComponentTypeCreateDto;
import de.uni.stuttgart.ipvs.em.form.ComponentTypeFormModelValidation;
import de.uni.stuttgart.ipvs.em.persistence.CviRepository;
import de.uni.stuttgart.ipvs.em.response.ResultsSet;
import de.uni.stuttgart.ipvs.em.response.TermsMeaningResultsSet;

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

    // CREATE
    public String createComponentType_roleManagerAdmin(ComponentTypeCreateDto componentTypeCreateDto) {
        this.ctFMV.validate(componentTypeCreateDto);

        var componentType = ComponentType.build(componentTypeCreateDto);
        this.cviRepository.save(componentType);

        return componentType.getSearchId();
    }

    // READ
    public ResultsSet getAllComponentTypes_roleManagerAdmin() {
        var selectResults = this.cviRepository.findAllComponentTypes();

        return selectResults.isEmpty() ? ResultsSet.EMPTY : CviServiceUtils.getAllComponentTypesResultsSet(selectResults);
    }

    // READ
    public TermsMeaningResultsSet searchMeaningOfTermsText_allRoles(String termsText) {
        return !hasLength(termsText) ?
                TermsMeaningResultsSet.EMPTY :
                searchMeaningOfTerms(
                        CviServiceUtils.requiredTermsTextFormat(termsText),
                        CviServiceUtils.termsTextToTerms(termsText)
                );
    }

    // READ
    private TermsMeaningResultsSet searchMeaningOfTerms(String termsText, Collection<String> terms) {
        var selectResults = this.cviRepository.searchMeaningOfTerms(terms);

        return selectResults.isEmpty() ?
                TermsMeaningResultsSet.EMPTY :
                CviServiceUtils.getTermsMeaningResultsSet(termsText, selectResults);
    }

}
