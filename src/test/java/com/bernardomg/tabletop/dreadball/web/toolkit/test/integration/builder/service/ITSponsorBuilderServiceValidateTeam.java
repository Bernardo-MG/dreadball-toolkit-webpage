/**
 * Copyright 2018 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.bernardomg.tabletop.dreadball.web.toolkit.test.integration.builder.service;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bernardomg.tabletop.dreadball.build.service.SponsorBuilderService;
import com.bernardomg.tabletop.dreadball.model.DefaultSponsorTeamSelection;
import com.bernardomg.tabletop.dreadball.model.team.SponsorTeam;
import com.bernardomg.tabletop.dreadball.web.toolkit.test.configuration.TestValues;
import com.google.common.collect.Iterables;

/**
 * Integration tests for {@link SponsorBuilderService}.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(
        locations = { "classpath:context/test-service-context.xml" })
public class ITSponsorBuilderServiceValidateTeam
        extends AbstractJUnit4SpringContextTests {

    /**
     * Tested service.
     */
    @Autowired
    private SponsorBuilderService service;

    /**
     * Default constructor.
     */
    public ITSponsorBuilderServiceValidateTeam() {
        super();
    }

    /**
     * Verifies that valid affinities are returned.
     */
    @Test
    public final void testValidate_Affinities_ReturnsExpectedAffinities() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setAffinities(
                Arrays.asList("affinity_1", "affinity_2", "affinity_3"));

        result = service.validateTeam(selection);

        Assertions.assertEquals(3,
                Iterables.size(result.getSponsor().getAffinityGroups()));
    }

    /**
     * Verifies when there is no data an empty current rank is returned.
     */
    @Test
    public final void testValidate_Empty_EmptyCurrentRank() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        result = service.validateTeam(selection);

        Assertions.assertEquals(new Integer(0), result.getCurrentRank());
    }

    /**
     * Verifies when there is no data an empty rank is returned.
     */
    @Test
    public final void testValidate_Empty_EmptyRankCost() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        result = service.validateTeam(selection);

        Assertions.assertEquals(new Integer(0), result.getRankCost());
    }

    /**
     * Verifies when there is no data an empty valoration is returned.
     */
    @Test
    public final void testValidate_Empty_EmptyValoration() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        result = service.validateTeam(selection);

        Assertions.assertEquals(new Integer(0), result.getTotalCost());
    }

    /**
     * Verifies when there is data the expected rank is returned.
     */
    @Test
    public final void testValidate_Filled_ExpectedCurrentRank() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setBaseRank(10);
        selection.setTeamPlayers(Arrays.asList(TestValues.PLAYER_A));
        selection.setAffinities(Arrays.asList("affinity_1"));
        selection.setSpecialMoveCards(1);
        selection.setCheerleaders(1);

        result = service.validateTeam(selection);

        Assertions.assertEquals(new Integer(8), result.getCurrentRank());
    }

    /**
     * Verifies when there is data the expected rank is returned.
     */
    @Test
    public final void testValidate_Filled_ExpectedRankCost() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setBaseRank(10);
        selection.setTeamPlayers(Arrays.asList(TestValues.PLAYER_A));
        selection.setAffinities(Arrays.asList("affinity_1"));
        selection.setSpecialMoveCards(1);
        selection.setCheerleaders(1);

        result = service.validateTeam(selection);

        Assertions.assertEquals(new Integer(2), result.getRankCost());
    }

    /**
     * Verifies when there is data the expected rank is returned.
     */
    @Test
    public final void testValidate_Filled_ExpectedValoration() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setBaseRank(10);
        selection.setTeamPlayers(Arrays.asList(TestValues.PLAYER_A));
        selection.setAffinities(Arrays.asList("affinity_1"));
        selection.setSpecialMoveCards(1);
        selection.setCheerleaders(1);

        result = service.validateTeam(selection);

        Assertions.assertEquals(new Integer(16), result.getTotalCost());
    }

    /**
     * Verifies that when there are no affinities then none is returned.
     */
    @Test
    public final void testValidate_NoAffinities_NoReturnedAffinities() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        result = service.validateTeam(selection);

        Assertions.assertEquals(0,
                Iterables.size(result.getSponsor().getAffinityGroups()));
    }

    /**
     * Verifies that when there are no players then none is returned.
     */
    @Test
    public final void testValidate_NoTeamPlayers_NoReturnedTeamPlayers() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        result = service.validateTeam(selection);

        Assertions.assertEquals(0, result.getPlayers().size());
    }

    /**
     * Verifies that when there are repeated affinities the returned ones do not
     * include repeated affinities.
     */
    @Test
    public final void
            testValidate_RepeatedAffinities_ReturnsExpectedAffinities() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setAffinities(
                Arrays.asList("affinity_1", "affinity_2", "affinity_1"));

        result = service.validateTeam(selection);

        Assertions.assertEquals(2,
                Iterables.size(result.getSponsor().getAffinityGroups()));
    }

    /**
     * Verifies that when there are repeated players the returned ones do not
     * include repeated players.
     */
    @Test
    public final void
            testValidate_RepeatedTeamPlayers_ReturnsExpectedTeamPlayers() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setTeamPlayers(Arrays.asList(TestValues.PLAYER_A,
                TestValues.PLAYER_C, TestValues.PLAYER_A));

        result = service.validateTeam(selection);

        Assertions.assertEquals(3, result.getPlayers().size());
    }

    /**
     * Verifies that the base rank is returned.
     */
    @Test
    public final void testValidate_ReturnsBaseRank() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;
        final Integer baseRank;

        selection = new DefaultSponsorTeamSelection();

        baseRank = 10;

        selection.setBaseRank(baseRank);

        result = service.validateTeam(selection);

        Assertions.assertEquals(baseRank, result.getBaseRank());
    }

    /**
     * Verifies that when there are affinities the correct player cost is
     * returned.
     */
    @Test
    public final void
            testValidate_TeamPlayers_Affinities_ReturnsExpectedCost() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setTeamPlayers(Arrays.asList(TestValues.PLAYER_A));
        selection.setAffinities(Arrays.asList("affinity_1"));

        result = service.validateTeam(selection);

        Assertions.assertEquals(new Integer(15),
                result.getPlayers().get(1).getCost());
    }

    /**
     * Verifies that when there are no affinities the correct player cost is
     * returned.
     */
    @Test
    public final void
            testValidate_TeamPlayers_NoAffinities_ReturnsExpectedCost() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setTeamPlayers(Arrays.asList(TestValues.PLAYER_A));

        result = service.validateTeam(selection);

        Assertions.assertEquals(new Integer(23),
                result.getPlayers().get(1).getCost());
    }

    /**
     * Verifies that valid players are returned.
     */
    @Test
    public final void testValidate_TeamPlayers_ReturnsExpectedTeamPlayers() {
        final DefaultSponsorTeamSelection selection;
        final SponsorTeam result;

        selection = new DefaultSponsorTeamSelection();

        selection.setTeamPlayers(Arrays.asList(TestValues.PLAYER_A,
                TestValues.PLAYER_C, TestValues.PLAYER_B));

        result = service.validateTeam(selection);

        Assertions.assertEquals(3, result.getPlayers().size());
    }

}
