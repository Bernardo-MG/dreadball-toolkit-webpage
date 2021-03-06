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

package com.bernardomg.tabletop.dreadball.rules;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bernardomg.tabletop.dreadball.model.availability.asset.SponsorAssetsCosts;

/**
 * Autowired implementation of the {@code SponsorCosts}. It loads all the data
 * from the Spring context.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Component("SponsorCosts")
public final class AutowiredSponsorCosts implements SponsorAssetsCosts {

    /**
     * Cheerleader cost.
     */
    @Value("${sponsor.asset.cheerleader.cost}")
    private Integer cheerleaderCost;

    /**
     * Coaching die cost.
     */
    @Value("${sponsor.asset.die.cost}")
    private Integer dieCost;

    /**
     * Medi-Bot cost.
     */
    @Value("${sponsor.asset.medibot.cost}")
    private Integer medibotCost;

    /**
     * Special move card cost.
     */
    @Value("${sponsor.asset.move.cost}")
    private Integer moveCost;

    /**
     * Nasty surprise cost.
     */
    @Value("${sponsor.asset.surprise.cost}")
    private Integer nastySurpriseCost;

    /**
     * Wager cost.
     */
    @Value("${sponsor.asset.wager.cost}")
    private Integer wagerCost;

    /**
     * Constructs the sponsor costs.
     */
    public AutowiredSponsorCosts() {
        super();
    }

    @Override
    public final Integer getAffinityGroupCost() {
        return 0;
    }

    @Override
    public final Integer getCheerleaderCost() {
        return cheerleaderCost;
    }

    @Override
    public final Integer getCoachingDieCost() {
        return dieCost;
    }

    @Override
    public final Integer getMediBotCost() {
        return medibotCost;
    }

    @Override
    public final Integer getNastySurpriseCardCost() {
        return nastySurpriseCost;
    }

    @Override
    public final Integer getSpecialMoveCardCost() {
        return moveCost;
    }

    @Override
    public final Integer getWagerCost() {
        return wagerCost;
    }

}
