
package com.wandrell.tabletop.dreadball.build.dbx.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.dreadball.build.dbx.assembler.AffinitiesSelectionAssembler;
import com.wandrell.tabletop.dreadball.build.dbx.assembler.SponsorTeamSelectionAssembler;
import com.wandrell.tabletop.dreadball.build.dbx.model.SponsorAffinities;
import com.wandrell.tabletop.dreadball.build.dbx.model.SponsorTeamAssets;
import com.wandrell.tabletop.dreadball.build.dbx.model.SponsorTeamSelection;
import com.wandrell.tabletop.dreadball.model.unit.AffinityUnit;
import com.wandrell.tabletop.dreadball.repository.unit.AffinityUnitRepository;

@Service
public final class DefaultSponsorBuilderAssemblerService
        implements SponsorBuilderAssemblerService {

    private final AffinitiesSelectionAssembler  affAssembler;

    /**
     * Affinity units repository.
     */
    private final AffinityUnitRepository        affinityUnitRepository;

    private final SponsorTeamSelectionAssembler teamAssembler;

    @Autowired
    public DefaultSponsorBuilderAssemblerService(
            final SponsorTeamSelectionAssembler sponsorTeamAssembler,
            final AffinitiesSelectionAssembler affinitiesAssembler,
            final AffinityUnitRepository unitsRepository) {
        super();

        teamAssembler = checkNotNull(sponsorTeamAssembler,
                "Received a null pointer as team selection assembler");
        affAssembler = checkNotNull(affinitiesAssembler,
                "Received a null pointer as affinities selection assembler");
        affinityUnitRepository = checkNotNull(unitsRepository,
                "Received a null pointer as units repository");
    }

    @Override
    public final SponsorAffinities
            assembleSponsorAffinities(final Collection<String> affinities) {
        final Integer rank;
        final Iterable<String> valid;
        // TODO: Validate

        rank = getRank(affinities);
        // TODO: Ensure these are existing affinities
        valid = getValidAffinities(affinities);

        return getAffinitiesSelectionAssembler().assemble(valid, rank);
    }

    @Override
    public final SponsorTeamSelection assembleSponsorTeamSelection(
            final Collection<String> affinities, final Collection<String> units,
            final SponsorTeamAssets assets, final Integer baseRank) {
        final Collection<? extends AffinityUnit> affUnits;
        // TODO: Validate

        affUnits = getUnits(units);

        return getSponsorTeamSelectionAssembler().assemble(affinities, affUnits,
                assets, baseRank);
    }

    private final AffinitiesSelectionAssembler
            getAffinitiesSelectionAssembler() {
        return affAssembler;
    }

    private final AffinityUnitRepository getAffinityUnitRepository() {
        return affinityUnitRepository;
    }

    private final Integer getRank(final Collection<String> affinities) {
        final Integer rank;

        // TODO: This doesn't look like a good solution
        rank = affinities.stream()
                .filter(affinity -> affinity.equals("rank_increase"))
                .collect(Collectors.toList()).size();

        return rank;
    }

    private final SponsorTeamSelectionAssembler
            getSponsorTeamSelectionAssembler() {
        return teamAssembler;
    }

    private final Collection<? extends AffinityUnit>
            getUnits(final Collection<String> unitNames) {
        final Collection<? extends AffinityUnit> read;
        final Collection<AffinityUnit> units;
        final Map<String, ? extends AffinityUnit> readMap;

        if (unitNames.isEmpty()) {
            read = Collections.emptyList();
        } else {
            read = getAffinityUnitRepository().findByTemplateNameIn(unitNames);
        }

        readMap = read.stream().collect(Collectors
                .toMap(AffinityUnit::getTemplateName, Function.identity()));

        units = new ArrayList<>();
        for (final String name : unitNames) {
            units.add(readMap.get(name));
        }

        return units;
    }

    private final Iterable<String>
            getValidAffinities(final Collection<String> affinities) {
        return affinities.stream()
                .filter(affinity -> !affinity.equals("rank_increase"))
                .collect(Collectors.toList());
    }

}
