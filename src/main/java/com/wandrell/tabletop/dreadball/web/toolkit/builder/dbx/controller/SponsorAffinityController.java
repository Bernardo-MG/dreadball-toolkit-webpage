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

package com.wandrell.tabletop.dreadball.web.toolkit.builder.dbx.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wandrell.tabletop.dreadball.build.dbx.SponsorCosts;
import com.wandrell.tabletop.dreadball.model.availability.unit.SponsorAffinityGroupAvailability;
import com.wandrell.tabletop.dreadball.model.unit.DefaultAffinityGroup;
import com.wandrell.tabletop.dreadball.web.toolkit.builder.dbx.controller.bean.SponsorAffinitiesSelection;
import com.wandrell.tabletop.dreadball.web.toolkit.builder.dbx.service.SponsorAffinityGroupAvailabilityService;

/**
 * Controller for the affinity groups codex views.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@RestController
@RequestMapping("/rest/builder/affinity")
public class SponsorAffinityController {

    /**
     * Affinity groups codex service.
     */
    private final SponsorAffinityGroupAvailabilityService sponsorAffinityGroupAvailabilityService;

    private final SponsorCosts                            sponsorCosts;

    /**
     * Constructs a controller with the specified dependencies.
     * 
     * @param service
     *            affinity groups codex service
     */
    @Autowired
    public SponsorAffinityController(
            final SponsorAffinityGroupAvailabilityService service,
            final SponsorCosts costs) {
        super();

        sponsorAffinityGroupAvailabilityService = checkNotNull(service,
                "Received a null pointer as Sponsor affinity groups availabilities codex service");
        sponsorCosts = checkNotNull(costs,
                "Received a null pointer as Sponsor costs service");
    }

    /**
     * Returns the view for all the affinity units.
     * 
     * @return the view for all the affinity units
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public final Iterable<SponsorAffinityGroupAvailability>
            getAffinityGroups() {
        return getSponsorAffinityGroupAvailabilityService()
                .getAllSponsorAffinityGroupAvailabilities();
    }

    @GetMapping(path = "/selection",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public final SponsorAffinitiesSelection getSelectionResult(@RequestParam(
            name = "affinities", required = false,
            defaultValue = "") final ArrayList<DefaultAffinityGroup> affinities) {
        final Integer rankAdd;
        final Integer rank;
        final Iterable<DefaultAffinityGroup> filtered;

        rankAdd = affinities.stream()
                .filter(affinity -> affinity.getName().equals("rank"))
                .collect(Collectors.toList()).size();
        filtered = affinities.stream()
                .filter(affinity -> !affinity.getName().equals("rank"))
                .collect(Collectors.toList());

        rank = getSponsorCosts().getInitialRank() + rankAdd;

        return new SponsorAffinitiesSelection(filtered, rank);
    }

    /**
     * Returns the affinity groups service.
     * 
     * @return the affinity groups service
     */
    private final SponsorAffinityGroupAvailabilityService
            getSponsorAffinityGroupAvailabilityService() {
        return sponsorAffinityGroupAvailabilityService;
    }

    private final SponsorCosts getSponsorCosts() {
        return sponsorCosts;
    }

}