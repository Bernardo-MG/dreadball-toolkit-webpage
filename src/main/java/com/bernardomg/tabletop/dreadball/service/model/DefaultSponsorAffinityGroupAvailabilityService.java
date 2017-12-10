
package com.bernardomg.tabletop.dreadball.service.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardomg.tabletop.dreadball.model.availability.unit.SponsorAffinityGroupAvailability;
import com.bernardomg.tabletop.dreadball.repository.availability.SponsorAffinityGroupAvailabilityRepository;

@Service("sponsorAffinityGroupAvailabilityService")
public class DefaultSponsorAffinityGroupAvailabilityService
        implements SponsorAffinityGroupAvailabilityService {

    /**
     * Affinity groups repository.
     */
    @Autowired
    private SponsorAffinityGroupAvailabilityRepository sponsorAffinityGroupAvailabilityRepository;

    /**
     * Constructs a service with the specified arguments.
     */
    public DefaultSponsorAffinityGroupAvailabilityService() {
        super();
    }

    @Override
    public final Iterable<SponsorAffinityGroupAvailability>
            getAllSponsorAffinityGroupAvailabilities() {
        final Collection<SponsorAffinityGroupAvailability> groups;

        // TODO: There may be a better way to do this
        groups = new ArrayList<>();
        for (final SponsorAffinityGroupAvailability group : getSponsorAffinityGroupAvailabilityRepository()
                .findAll()) {
            groups.add(group);
        }

        return groups;
    }

    private SponsorAffinityGroupAvailabilityRepository
            getSponsorAffinityGroupAvailabilityRepository() {
        return sponsorAffinityGroupAvailabilityRepository;
    }

}