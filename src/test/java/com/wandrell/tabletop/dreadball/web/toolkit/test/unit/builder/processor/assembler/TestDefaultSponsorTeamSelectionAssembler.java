
package com.wandrell.tabletop.dreadball.web.toolkit.test.unit.builder.processor.assembler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Iterables;
import com.wandrell.tabletop.dreadball.build.dbx.assembler.DefaultSponsorTeamSelectionAssembler;
import com.wandrell.tabletop.dreadball.build.dbx.assembler.SponsorTeamSelectionAssembler;
import com.wandrell.tabletop.dreadball.build.dbx.model.SponsorTeamSelection;
import com.wandrell.tabletop.dreadball.model.faction.Sponsor;
import com.wandrell.tabletop.dreadball.model.team.SponsorTeam;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.Unit;

public final class TestDefaultSponsorTeamSelectionAssembler {

    public TestDefaultSponsorTeamSelectionAssembler() {
        super();
    }

    @Test
    public final void testAssemble_Affinities_ReturnsExpectedAffinities() {
        final SponsorTeamSelectionAssembler assembler;
        final SponsorTeamSelection selection;
        final SponsorTeam team;
        final Sponsor sponsor;
        final AffinityGroup aff1;
        final AffinityGroup aff2;
        final AffinityGroup aff3;

        assembler = new DefaultSponsorTeamSelectionAssembler();

        aff1 = Mockito.mock(AffinityGroup.class);
        Mockito.when(aff1.getName()).thenReturn("aff1");
        aff2 = Mockito.mock(AffinityGroup.class);
        Mockito.when(aff2.getName()).thenReturn("aff2");
        aff3 = Mockito.mock(AffinityGroup.class);
        Mockito.when(aff3.getName()).thenReturn("aff3");

        sponsor = Mockito.mock(Sponsor.class);
        Mockito.when(sponsor.getRank()).thenReturn(0);
        Mockito.when(sponsor.getAffinityGroups())
                .thenReturn(Arrays.asList(aff1, aff2, aff3));

        team = Mockito.mock(SponsorTeam.class);
        Mockito.when(team.getSponsor()).thenReturn(sponsor);
        Mockito.when(team.getPlayers()).thenReturn(Maps.newHashMap());
        Mockito.when(team.getValoration()).thenReturn(0);
        Mockito.when(team.getRankCost()).thenReturn(0);

        selection = assembler.assemble(team);

        Assert.assertEquals(Iterables.size(selection.getAffinities()), 3);
    }

    @Test
    public final void testAssemble_NoAffinities_NoReturnedAffinities() {
        final SponsorTeamSelectionAssembler assembler;
        final SponsorTeamSelection selection;
        final SponsorTeam team;
        final Sponsor sponsor;

        assembler = new DefaultSponsorTeamSelectionAssembler();

        sponsor = Mockito.mock(Sponsor.class);
        Mockito.when(sponsor.getRank()).thenReturn(0);
        Mockito.when(sponsor.getAffinityGroups())
                .thenReturn(Collections.emptyList());

        team = Mockito.mock(SponsorTeam.class);
        Mockito.when(team.getSponsor()).thenReturn(sponsor);
        Mockito.when(team.getPlayers()).thenReturn(Maps.newHashMap());
        Mockito.when(team.getValoration()).thenReturn(0);
        Mockito.when(team.getRankCost()).thenReturn(0);

        selection = assembler.assemble(team);

        Assert.assertEquals(Iterables.size(selection.getAffinities()), 0);
    }

    @Test
    public final void testAssemble_NoUnits_NoReturnedUnits() {
        final SponsorTeamSelectionAssembler assembler;
        final SponsorTeamSelection selection;
        final SponsorTeam team;
        final Sponsor sponsor;

        assembler = new DefaultSponsorTeamSelectionAssembler();

        sponsor = Mockito.mock(Sponsor.class);
        Mockito.when(sponsor.getRank()).thenReturn(0);
        Mockito.when(sponsor.getAffinityGroups())
                .thenReturn(Collections.emptyList());

        team = Mockito.mock(SponsorTeam.class);
        Mockito.when(team.getSponsor()).thenReturn(sponsor);
        Mockito.when(team.getPlayers()).thenReturn(Maps.newHashMap());
        Mockito.when(team.getValoration()).thenReturn(0);
        Mockito.when(team.getRankCost()).thenReturn(0);

        selection = assembler.assemble(team);

        Assert.assertEquals(Iterables.size(selection.getUnits()), 0);
    }

    @Test
    public final void
            testAssemble_RepeatedAffinities_ReturnsExpectedAffinities() {
        final SponsorTeamSelectionAssembler assembler;
        final SponsorTeamSelection selection;
        final SponsorTeam team;
        final Sponsor sponsor;
        final AffinityGroup aff1;
        final AffinityGroup aff2;
        final AffinityGroup aff3;

        assembler = new DefaultSponsorTeamSelectionAssembler();

        aff1 = Mockito.mock(AffinityGroup.class);
        Mockito.when(aff1.getName()).thenReturn("aff1");
        aff2 = Mockito.mock(AffinityGroup.class);
        Mockito.when(aff2.getName()).thenReturn("aff2");
        aff3 = Mockito.mock(AffinityGroup.class);
        Mockito.when(aff3.getName()).thenReturn("aff1");

        sponsor = Mockito.mock(Sponsor.class);
        Mockito.when(sponsor.getRank()).thenReturn(0);
        Mockito.when(sponsor.getAffinityGroups())
                .thenReturn(Arrays.asList(aff1, aff2, aff3));

        team = Mockito.mock(SponsorTeam.class);
        Mockito.when(team.getSponsor()).thenReturn(sponsor);
        Mockito.when(team.getPlayers()).thenReturn(Maps.newHashMap());
        Mockito.when(team.getValoration()).thenReturn(0);
        Mockito.when(team.getRankCost()).thenReturn(0);

        selection = assembler.assemble(team);

        Assert.assertEquals(Iterables.size(selection.getAffinities()), 2);
    }

    @Test
    public final void testAssemble_RepeatedUnits_ReturnsExpectedUnits() {
        final SponsorTeamSelectionAssembler assembler;
        final SponsorTeamSelection selection;
        final SponsorTeam team;
        final Sponsor sponsor;
        final Unit unit1;
        final Unit unit2;
        final Unit unit3;
        final Map<Integer, Unit> units;

        assembler = new DefaultSponsorTeamSelectionAssembler();

        sponsor = Mockito.mock(Sponsor.class);
        Mockito.when(sponsor.getRank()).thenReturn(0);
        Mockito.when(sponsor.getAffinityGroups())
                .thenReturn(Collections.emptyList());

        unit1 = Mockito.mock(Unit.class);
        Mockito.when(unit1.getName()).thenReturn("unit1");
        unit2 = Mockito.mock(Unit.class);
        Mockito.when(unit2.getName()).thenReturn("unit2");
        unit3 = Mockito.mock(Unit.class);
        Mockito.when(unit3.getName()).thenReturn("unit1");

        units = new HashMap<>();
        units.put(1, unit1);
        units.put(2, unit2);
        units.put(3, unit3);

        team = Mockito.mock(SponsorTeam.class);
        Mockito.when(team.getSponsor()).thenReturn(sponsor);
        Mockito.when(team.getPlayers()).thenReturn(units);
        Mockito.when(team.getValoration()).thenReturn(0);
        Mockito.when(team.getRankCost()).thenReturn(0);

        selection = assembler.assemble(team);

        Assert.assertEquals(Iterables.size(selection.getUnits()), 3);
    }

    @Test
    public final void testAssemble_Units_ReturnsExpectedUnits() {
        final SponsorTeamSelectionAssembler assembler;
        final SponsorTeamSelection selection;
        final SponsorTeam team;
        final Sponsor sponsor;
        final Unit unit1;
        final Unit unit2;
        final Unit unit3;
        final Map<Integer, Unit> units;

        assembler = new DefaultSponsorTeamSelectionAssembler();

        sponsor = Mockito.mock(Sponsor.class);
        Mockito.when(sponsor.getRank()).thenReturn(0);
        Mockito.when(sponsor.getAffinityGroups())
                .thenReturn(Collections.emptyList());

        unit1 = Mockito.mock(Unit.class);
        Mockito.when(unit1.getName()).thenReturn("unit1");
        unit2 = Mockito.mock(Unit.class);
        Mockito.when(unit2.getName()).thenReturn("unit2");
        unit3 = Mockito.mock(Unit.class);
        Mockito.when(unit3.getName()).thenReturn("unit3");

        units = new HashMap<>();
        units.put(1, unit1);
        units.put(2, unit2);
        units.put(3, unit3);

        team = Mockito.mock(SponsorTeam.class);
        Mockito.when(team.getSponsor()).thenReturn(sponsor);
        Mockito.when(team.getPlayers()).thenReturn(units);
        Mockito.when(team.getValoration()).thenReturn(0);
        Mockito.when(team.getRankCost()).thenReturn(0);

        selection = assembler.assemble(team);

        Assert.assertEquals(Iterables.size(selection.getUnits()), 3);
    }

}
