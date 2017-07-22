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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wandrell.tabletop.dreadball.model.unit.DefaultAffinityGroup;
import com.wandrell.tabletop.dreadball.web.toolkit.builder.dbx.controller.bean.SponsorAffinitiesSelection;
import com.wandrell.tabletop.dreadball.web.toolkit.builder.dbx.controller.bean.SponsorTeamAssets;
import com.wandrell.tabletop.dreadball.web.toolkit.builder.dbx.service.SponsorBuilderService;

/**
 * Controller for the affinity groups codex views.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@RestController
@RequestMapping("/rest/builder/validation")
public class SponsorValidationController {

    private final SponsorBuilderService builderService;

    /**
     * Constructs a controller with the specified dependencies.
     * 
     */
    @Autowired
    public SponsorValidationController(
            final SponsorBuilderService sponsorBuilderService) {
        super();

        builderService = checkNotNull(sponsorBuilderService,
                "Received a null pointer as sponsor builder service");
    }

    @GetMapping(path = "/affinities",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public final SponsorAffinitiesSelection getAffinitiesSelectionResult(
            final ArrayList<DefaultAffinityGroup> affinities) {
        return getSponsorBuilderService()
                .getAffinitiesSelectionResult(affinities);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public final SponsorAffinitiesSelection getSelectionResult(
            final ArrayList<DefaultAffinityGroup> affinities,
            final SponsorTeamAssets assets, final Integer baseRank) {
        return getSponsorBuilderService().getSelectionResult(affinities, assets,
                baseRank);
    }

    private final SponsorBuilderService getSponsorBuilderService() {
        return builderService;
    }

}
