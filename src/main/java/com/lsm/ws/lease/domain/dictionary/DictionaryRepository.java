package com.lsm.ws.lease.domain.dictionary;

import com.lsm.ws.lease.domain.Language;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository {

    List<Country> getCountries(Language lang);

    Optional<Country> getCountry(String countryCode, Language lang);

    default Optional<Country> getCountry(String countryCode) {
        return getCountry(countryCode, Language.EN);
    }

    List<Region> getRegions(String countryCode, Language lang);

    Optional<Region> getRegion(String regionId, Language lang);

    List<City> getCities(String regionId, Language lang);

    Optional<City> getCity(String cityId, Language lang);
}
