
package com.wandrell.tabletop.dreadball.build.dbx.assembler;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wandrell.tabletop.dreadball.build.dbx.model.DefaultSponsorTeamSelection;
import com.wandrell.tabletop.dreadball.build.dbx.model.SponsorTeamAssets;
import com.wandrell.tabletop.dreadball.build.dbx.model.SponsorTeamSelection;
import com.wandrell.tabletop.dreadball.build.dbx.model.TeamPlayer;
import com.wandrell.tabletop.dreadball.build.dbx.rules.SponsorCosts;
import com.wandrell.tabletop.dreadball.model.faction.DefaultSponsor;
import com.wandrell.tabletop.dreadball.model.faction.Sponsor;
import com.wandrell.tabletop.dreadball.model.team.DefaultSponsorTeam;
import com.wandrell.tabletop.dreadball.model.team.SponsorTeam;
import com.wandrell.tabletop.dreadball.model.team.calculator.CostCalculator;
import com.wandrell.tabletop.dreadball.model.team.calculator.DefaultRankCostCalculator;
import com.wandrell.tabletop.dreadball.model.team.calculator.SponsorTeamValorationCalculator;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.AffinityLevel;
import com.wandrell.tabletop.dreadball.model.unit.AffinityUnit;
import com.wandrell.tabletop.dreadball.model.unit.DefaultUnit;
import com.wandrell.tabletop.dreadball.model.unit.Unit;
import com.wandrell.tabletop.dreadball.rules.DbxRules;

@Service
public class DefaultSponsorTeamSelectionAssembler
        implements SponsorTeamSelectionAssembler {

    private final SponsorCosts costs;

    /**
     * DBX rules.
     */
    private final DbxRules     dbxRules;

    private final SponsorCosts rankCosts;

    @Autowired
    public DefaultSponsorTeamSelectionAssembler(
            @Qualifier("SponsorCosts") final SponsorCosts sponsorCosts,
            @Qualifier("SponsorRankCosts") final SponsorCosts sponsorRankCosts,
            final DbxRules rules) {
        super();

        costs = checkNotNull(sponsorCosts,
                "Received a null pointer as Sponsor costs");
        rankCosts = checkNotNull(sponsorRankCosts,
                "Received a null pointer as Sponsor rank costs");
        dbxRules = checkNotNull(rules, "Received a null pointer as DBX rules");
    }

    @Override
    public final SponsorTeamSelection assemble(
            final Iterable<AffinityGroup> affinities,
            final Iterable<AffinityUnit> units, final SponsorTeamAssets assets,
            final Integer baseRank) {
        final Integer rank;
        final Integer assetRankCost;
        final Integer teamValue;
        final SponsorTeam sponsorTeam;
        final Collection<TeamPlayer> acceptedUnits;
        final Collection<String> affNames;

        checkNotNull(affinities, "Received a null pointer as affinities");
        checkNotNull(units, "Received a null pointer as units");
        checkNotNull(assets, "Received a null pointer as assets");
        checkNotNull(baseRank, "Received a null pointer as base rank");

        affNames = StreamSupport.stream(affinities.spliterator(), false)
                .map(aff -> aff.getName()).collect(Collectors.toList());

        sponsorTeam = getSponsorTeam(assets, units, affinities);

        teamValue = sponsorTeam.getValoration();
        assetRankCost = sponsorTeam.getRankCost();

        // TODO: Receive the rank increase instead of the base rank
        rank = baseRank - assetRankCost;

        acceptedUnits = sponsorTeam.getPlayers().entrySet().stream()
                .map(unit -> new TeamPlayer(unit.getKey(),
                        unit.getValue().getTemplateName()))
                .collect(Collectors.toList());

        return new DefaultSponsorTeamSelection(affNames, acceptedUnits, rank,
                baseRank, teamValue);
    }

    /**
     * Returns the DBX rules.
     * 
     * @return the DBX rules
     */
    private final DbxRules getDbxRules() {
        return dbxRules;
    }

    private final CostCalculator<SponsorTeam> getRankCostCalculator() {
        return new DefaultRankCostCalculator(getSponsorRankCosts().getDieCost(),
                getSponsorRankCosts().getSabotageCost(),
                getSponsorRankCosts().getSpecialMoveCost(),
                getSponsorRankCosts().getCheerleaderCost(),
                getSponsorRankCosts().getWagerCost(),
                getSponsorRankCosts().getMediBotCost());
    }

    private final SponsorCosts getSponsorCosts() {
        return costs;
    }

    private final SponsorCosts getSponsorRankCosts() {
        return rankCosts;
    }

    private final SponsorTeam getSponsorTeam(final SponsorTeamAssets assets,
            final Iterable<AffinityUnit> units,
            final Iterable<AffinityGroup> affinities) {
        final Sponsor sponsor;
        final SponsorTeam sponsorTeam;
        Unit unitSetUp;
        Integer cost;
        AffinityLevel affinityLevel;  // Affinity level relationship

        sponsor = new DefaultSponsor();

        sponsorTeam = new DefaultSponsorTeam(sponsor,
                getTeamValorationCalculator(), getRankCostCalculator());

        sponsorTeam.getSponsor()
                .setAffinityGroups(Lists.newArrayList(affinities));

        for (final AffinityUnit unit : units) {
            affinityLevel = getDbxRules().getAffinityLevel(unit, affinities);
            cost = getDbxRules().getUnitCost(affinityLevel, unit);
            unitSetUp = new DefaultUnit(unit.getTemplateName(), cost,
                    unit.getRole(), unit.getAttributes(), unit.getAbilities(),
                    unit.getMvp(), unit.getGiant());
            sponsorTeam.addPlayer(unitSetUp);
        }

        sponsorTeam.setCheerleaders(assets.getCheerleaders());
        sponsorTeam.setCoachingDice(assets.getCoachingDice());
        sponsorTeam.setMediBots(assets.getMediBots());
        sponsorTeam.setSpecialMoveCards(assets.getSpecialMoveCards());
        sponsorTeam.setSabotageCards(assets.getNastySurpriseCards());
        sponsorTeam.setWagers(assets.getWagers());

        return sponsorTeam;
    }

    private final CostCalculator<SponsorTeam> getTeamValorationCalculator() {
        return new SponsorTeamValorationCalculator(
                getSponsorCosts().getDieCost(),
                getSponsorCosts().getSabotageCost(),
                getSponsorCosts().getSpecialMoveCost(),
                getSponsorCosts().getCheerleaderCost(),
                getSponsorCosts().getWagerCost(),
                getSponsorCosts().getMediBotCost());
    }

}
