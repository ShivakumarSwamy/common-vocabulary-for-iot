package de.uni.stuttgart.ipvs.tm.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import de.uni.stuttgart.ipvs.tm.dto.HardwareTypeCreateDTO;
import de.uni.stuttgart.ipvs.tm.form.HardwareTypeFormModelValidation;
import de.uni.stuttgart.ipvs.tm.persistence.CviRepository;
import de.uni.stuttgart.ipvs.tm.response.ResultsSet;
import de.uni.stuttgart.ipvs.tm.response.TermsMeaningResultsSet;

import java.util.Collection;

import static org.springframework.util.StringUtils.hasLength;

@Service
@Slf4j
public class CviService {

    private final CviRepository cviRepository;
    private final HardwareTypeFormModelValidation htFMV;

    @Autowired
    public CviService(CviRepository cviRepository,
                      HardwareTypeFormModelValidation htFMV) {
        this.cviRepository = cviRepository;
        this.htFMV = htFMV;
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

    public ResultsSet getAllHardwareTypes() {
        var selectResults = this.cviRepository.findAllHardwareTypes();

        return selectResults.isEmpty() ? ResultsSet.EMPTY : CviServiceUtils.getAllHardwareTypesResultsSet(selectResults);
    }

    public String createHardwareType(HardwareTypeCreateDTO hardwareTypeCreateDTO) {

        this.htFMV.validate(hardwareTypeCreateDTO);

        var hardwareType = HardwareType.build(hardwareTypeCreateDTO);
        this.cviRepository.save(hardwareType);

        return hardwareType.getSearchId();
    }


}
