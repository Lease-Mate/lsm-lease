package com.lsm.ws.lease.context.dictionary;

import com.lsm.ws.lease.configuration.exception.dictionary.NoSuchCountryException;
import com.lsm.ws.lease.configuration.exception.dictionary.NoSuchRegionException;
import com.lsm.ws.lease.domain.Language;
import com.lsm.ws.lease.domain.dictionary.City;
import com.lsm.ws.lease.domain.dictionary.Country;
import com.lsm.ws.lease.domain.dictionary.DictionaryRepository;
import com.lsm.ws.lease.domain.dictionary.Region;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Dictionary")
@RestController
@RequestMapping("/v1/api/lease/dictionary")
public class DictionaryEndpoint {

    private static final String COUNTRIES_SUMMARY = "Get supported countries";
    private static final String COUNTRIES_DESC = "returns supported countries";
    private static final String REGIONS_SUMMARY = "Get supported regions";
    private static final String REGIONS_DESC = "returns supported regions";
    private static final String CITIES_SUMMARY = "Get supported cities";
    private static final String CITIES_DESC = "returns supported cities";

    private final DictionaryRepository dictionaryRepository;

    public DictionaryEndpoint(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Operation(summary = COUNTRIES_SUMMARY, description = COUNTRIES_DESC)
    @GetMapping("/countries")
    public List<Country> countries(@RequestHeader @NotNull Language lang) {
        return dictionaryRepository.getCountries(lang);
    }

    @Operation(summary = "Get country name", description = "returns name for specified country")
    @GetMapping("/countries/{countryCode}/name")
    public ResponseEntity<Country> countryName(@RequestHeader @NotNull Language lang, @PathVariable String countryCode) {
        return ResponseEntity.of(dictionaryRepository.getCountry(countryCode, lang));
    }

    @Operation(summary = REGIONS_SUMMARY, description = REGIONS_DESC)
    @GetMapping("/countries/{countryCode}/regions")
    public List<Region> regions(@PathVariable @NotEmpty String countryCode,
                                @RequestHeader @NotNull Language lang) {

        dictionaryRepository.getCountry(countryCode)
                            .orElseThrow(NoSuchCountryException::new);

        return dictionaryRepository.getRegions(countryCode, lang);
    }

    @Operation(summary = CITIES_SUMMARY, description = CITIES_DESC)
    @GetMapping("/regions/{regionId}/cities")
    public List<City> cities(@PathVariable @NotEmpty String regionId,
                             @RequestHeader @NotNull Language lang) {

        dictionaryRepository.getRegion(regionId, lang)
                            .orElseThrow(NoSuchRegionException::new);

        return dictionaryRepository.getCities(regionId, lang);
    }

    @Operation(summary = "Get region name", description = "returns name for specified region")
    @GetMapping("/regions/{regionId}/name")
    public ResponseEntity<Region> regionName(@RequestHeader @NotNull Language lang, @PathVariable String regionId) {
        return ResponseEntity.of(dictionaryRepository.getRegion(regionId, lang));
    }

    @Operation(summary = "Get city name", description = "returns name for specified city")
    @GetMapping("/cities/{cityId}/name")
    public ResponseEntity<City> cityName(@RequestHeader @NotNull Language lang, @PathVariable String cityId) {
        return ResponseEntity.of(dictionaryRepository.getCity(cityId, lang));
    }
}
