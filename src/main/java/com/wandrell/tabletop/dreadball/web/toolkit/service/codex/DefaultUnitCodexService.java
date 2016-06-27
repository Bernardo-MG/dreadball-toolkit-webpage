
package com.wandrell.tabletop.dreadball.web.toolkit.service.codex;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.dreadball.model.unit.AffinityUnit;
import com.wandrell.tabletop.dreadball.web.toolkit.repository.unit.AffinityUnitRepository;

@Service("unitCodexService")
public final class DefaultUnitCodexService implements UnitCodexService {

    private final AffinityUnitRepository unitRepo;

    @Autowired
    public DefaultUnitCodexService(
            final AffinityUnitRepository unitRepository) {
        super();

        unitRepo = checkNotNull(unitRepository,
                "Received null pointer as units repository");
    }

    @Override
    public final Iterable<? extends AffinityUnit> getAllUnits() {
        return getUnitRepository().findAll();
    }

    private final AffinityUnitRepository getUnitRepository() {
        return unitRepo;
    }

}
