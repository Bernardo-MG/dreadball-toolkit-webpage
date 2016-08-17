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

package com.wandrell.tabletop.dreadball.web.toolkit.service.builder;

import com.wandrell.tabletop.dreadball.model.availability.unit.SponsorAffinityGroupAvailability;
import com.wandrell.tabletop.dreadball.model.faction.Sponsor;
import com.wandrell.tabletop.dreadball.model.team.SponsorTeam;
import com.wandrell.tabletop.dreadball.model.unit.AffinityLevel;
import com.wandrell.tabletop.dreadball.model.unit.AffinityUnit;
import com.wandrell.tabletop.dreadball.model.unit.Unit;
import com.wandrell.tabletop.dreadball.web.toolkit.model.form.SponsorForm;

/**
 * Service for the DBX team builder.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public interface DbxTeamBuilderService {

    /**
     * Adds a unit to the specified team.
     * <p>
     * The unit will be acquired through its template name.
     * 
     * @param team
     *            team where the unit will be added
     * @param templateName
     *            name of the template to add
     */
    public void addUnit(final SponsorTeam team, final String templateName);

    /**
     * Returns the affinity level between a Sponsor and a unit.
     * 
     * @param sponsor
     *            Sponsor to find out the affinity level
     * @param unit
     *            unit to find out the affinity level
     * @return the affinity level between the Sponsor and the unit
     */
    public AffinityLevel getAffinityLevel(final Sponsor sponsor,
            final AffinityUnit unit);

    /**
     * Returns the initial rank for Sponsors.
     * 
     * @return the Sponsors initial rank
     */
    public Integer getInitialRank();

    /**
     * Returns the maximum number of units a Sponsor may have.
     * 
     * @return the Sponsors maximum number of units
     */
    public Integer getMaxTeamUnits();

    /**
     * Creates an Sponsor from the form data.
     * 
     * @param form
     *            sponsor form data
     * @return the Sponsor created from the form
     */
    public Sponsor getSponsor(final SponsorForm form);

    /**
     * Returns all the Sponsor affinity groups sets.
     * 
     * @return the Sponsor affinity groups sets
     */
    public Iterable<? extends SponsorAffinityGroupAvailability>
            getSponsorAffinityGroups();

    /**
     * Creates an Sponsor team from the specified Sponsor.
     * 
     * @param sponsor
     *            Sponsor for the team
     * @return a Sponsor team for the specified Sponsor
     */
    public SponsorTeam getSponsorTeam(final Sponsor sponsor);

    /**
     * Returns all the units available for the specified Sponsor team.
     * 
     * @param team
     *            Sponsor team to search the availabilities
     * @return the units available to the team
     */
    public Iterable<? extends Unit>
            getSponsorTeamAvailableUnits(final SponsorTeam team);

    /**
     * Returns the unit cost.
     * 
     * @param affinityLevel
     *            affinity level to search the cost for
     * @param unit
     *            unit to find out the cost
     * @return the cost of the unit for the affinity level
     */
    public Integer getUnitCost(final AffinityLevel affinityLevel,
            final AffinityUnit unit);

}
