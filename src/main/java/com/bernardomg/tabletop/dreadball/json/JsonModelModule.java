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

package com.bernardomg.tabletop.dreadball.json;

import org.springframework.stereotype.Component;

import com.bernardomg.tabletop.dreadball.model.availability.unit.SponsorAffinityGroupAvailability;
import com.bernardomg.tabletop.dreadball.model.faction.Sponsor;
import com.bernardomg.tabletop.dreadball.model.json.availability.unit.SponsorAffinityGroupAvailabilityMixIn;
import com.bernardomg.tabletop.dreadball.model.json.faction.SponsorMixIn;
import com.bernardomg.tabletop.dreadball.model.json.team.SponsorTeamMixIn;
import com.bernardomg.tabletop.dreadball.model.json.unit.AffinityGroupMixIn;
import com.bernardomg.tabletop.dreadball.model.json.unit.AffinityUnitMixIn;
import com.bernardomg.tabletop.dreadball.model.json.unit.UnitMixIn;
import com.bernardomg.tabletop.dreadball.model.json.unit.stats.AbilityMixIn;
import com.bernardomg.tabletop.dreadball.model.json.unit.stats.AttributesMixIn;
import com.bernardomg.tabletop.dreadball.model.team.SponsorTeam;
import com.bernardomg.tabletop.dreadball.model.unit.AffinityGroup;
import com.bernardomg.tabletop.dreadball.model.unit.AffinityUnit;
import com.bernardomg.tabletop.dreadball.model.unit.Unit;
import com.bernardomg.tabletop.dreadball.model.unit.stats.Ability;
import com.bernardomg.tabletop.dreadball.model.unit.stats.Attributes;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Contains all the Jackson configuration needed to set up a JSON mapper for
 * Dreadball.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Component
public final class JsonModelModule extends SimpleModule {

    /**
     * Serialization id.
     */
    private static final long serialVersionUID = 1405374344133040810L;

    /**
     * Constructs the module with the specified parameters.
     */
    public JsonModelModule() {
        super();
    }

    @Override
    public final void setupModule(final SetupContext context) {
        super.setupModule(context);

        setupMixIns(context);
    }

    /**
     * Sets the mix-ins into the received context.
     * 
     * @param context
     *            the context where the mix-ins will be set
     */
    private final void setupMixIns(final SetupContext context) {
        // Factions
        context.setMixInAnnotations(Sponsor.class, SponsorMixIn.class);

        // Stats
        context.setMixInAnnotations(Ability.class, AbilityMixIn.class);
        context.setMixInAnnotations(Attributes.class, AttributesMixIn.class);
        context.setMixInAnnotations(AffinityGroup.class,
                AffinityGroupMixIn.class);

        // Teams
        context.setMixInAnnotations(SponsorTeam.class, SponsorTeamMixIn.class);

        // Units
        context.setMixInAnnotations(Unit.class, UnitMixIn.class);
        context.setMixInAnnotations(AffinityUnit.class,
                AffinityUnitMixIn.class);

        // Availabilities
        context.setMixInAnnotations(SponsorAffinityGroupAvailability.class,
                SponsorAffinityGroupAvailabilityMixIn.class);
    }

}