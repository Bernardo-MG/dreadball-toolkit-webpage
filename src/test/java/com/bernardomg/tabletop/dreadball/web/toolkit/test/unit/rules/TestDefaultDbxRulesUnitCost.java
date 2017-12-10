/**
 * Copyright 2016 the original author or authors
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

package com.bernardomg.tabletop.dreadball.web.toolkit.test.unit.rules;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bernardomg.tabletop.dreadball.build.dbx.rules.SponsorConstraints;
import com.bernardomg.tabletop.dreadball.model.unit.AffinityLevel;
import com.bernardomg.tabletop.dreadball.model.unit.AffinityUnit;
import com.bernardomg.tabletop.dreadball.rules.DbxRules;
import com.bernardomg.tabletop.dreadball.rules.DefaultDbxRules;

/**
 * Unit tests for {@link SponsorConstraints} testing the {@code getUnitCost}
 * method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class TestDefaultDbxRulesUnitCost {

    /**
     * Service being tested.
     */
    private final DbxRules dbxTeamBuilderService = new DefaultDbxRules();

    /**
     * Default constructor.
     */
    public TestDefaultDbxRulesUnitCost() {
        super();
    }

    /**
     * Tests that when the affinity is 'ally' then the cost is the ally cost.
     */
    @Test
    public final void testUnitCost_Ally_AllyCost() {
        final AffinityUnit unit;
        final Integer cost;

        // Mocks unit
        unit = Mockito.mock(AffinityUnit.class);

        // Mocks unit costs
        Mockito.when(unit.getAllyCost()).thenReturn(1);
        Mockito.when(unit.getFriendCost()).thenReturn(2);
        Mockito.when(unit.getStrangerCost()).thenReturn(3);

        cost = dbxTeamBuilderService.getUnitCost(AffinityLevel.ALLY, unit);

        Assert.assertEquals((Integer) 1, cost);
    }

    /**
     * Tests that when the affinity is 'friend' then the cost is the friend
     * cost.
     */
    @Test
    public final void testUnitCost_Friend_FriendCost() {
        final AffinityUnit unit;
        final Integer cost;

        // Mocks unit
        unit = Mockito.mock(AffinityUnit.class);

        // Mocks unit costs
        Mockito.when(unit.getAllyCost()).thenReturn(1);
        Mockito.when(unit.getFriendCost()).thenReturn(2);
        Mockito.when(unit.getStrangerCost()).thenReturn(3);

        cost = dbxTeamBuilderService.getUnitCost(AffinityLevel.FRIEND, unit);

        Assert.assertEquals((Integer) 2, cost);
    }

    /**
     * Tests that when the affinity is 'stranger' then the cost is the stranger
     * cost.
     */
    @Test
    public final void testUnitCost_Stranger_StrangerCost() {
        final AffinityUnit unit;
        final Integer cost;

        // Mocks unit
        unit = Mockito.mock(AffinityUnit.class);

        // Mocks unit costs
        Mockito.when(unit.getAllyCost()).thenReturn(1);
        Mockito.when(unit.getFriendCost()).thenReturn(2);
        Mockito.when(unit.getStrangerCost()).thenReturn(3);

        cost = dbxTeamBuilderService.getUnitCost(AffinityLevel.STRANGER, unit);

        Assert.assertEquals((Integer) 3, cost);
    }

}