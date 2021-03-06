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

package com.bernardomg.tabletop.dreadball.web.toolkit.test.integration.codex.service;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bernardomg.tabletop.dreadball.codex.service.CodexService;
import com.bernardomg.tabletop.dreadball.model.player.TeamPlayer;
import com.bernardomg.tabletop.dreadball.web.toolkit.test.configuration.TestValues;
import com.google.common.collect.Iterables;

/**
 * Integration tests for {@link CodexService}.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(
        locations = { "classpath:context/test-service-context.xml" })
public class ITCodexService extends AbstractJUnit4SpringContextTests {

    /**
     * Tested service.
     */
    @Autowired
    private CodexService service;

    /**
     * Default constructor.
     */
    public ITCodexService() {
        super();
    }

    /**
     * Verifies that the players can be read.
     */
    @Test
    public final void testGetAffinityTeamPlayers_ReturnsExpected() {
        final Pageable pageable;
        final Iterable<? extends TeamPlayer> result;

        pageable = PageRequest.of(0, 10);
        result = service.getAffinityTeamPlayers(pageable);

        Assertions.assertEquals(4, Iterables.size(result));
    }

    /**
     * Verifies that the players are ordered.
     */
    @Test
    public final void testGetAffinityTeamPlayers_ReturnsOrdered() {
        final Pageable pageable;
        final Iterable<? extends TeamPlayer> result;
        final Iterator<? extends TeamPlayer> itr;

        pageable = PageRequest.of(0, 10, Direction.ASC, "templateName");
        result = service.getAffinityTeamPlayers(pageable);
        itr = result.iterator();

        Assertions.assertEquals(TestValues.PLAYER_A, itr.next().getTemplateName());
        Assertions.assertEquals(TestValues.PLAYER_B, itr.next().getTemplateName());
        Assertions.assertEquals(TestValues.PLAYER_C, itr.next().getTemplateName());
    }

    /**
     * Verifies that the players are returned inside a page.
     */
    @Test
    public final void testGetAffinityTeamPlayers_ReturnsPage() {
        final Pageable pageable;
        final Iterable<? extends TeamPlayer> result;

        pageable = PageRequest.of(0, 10);
        result = service.getAffinityTeamPlayers(pageable);

        Assertions.assertTrue(result instanceof Page);
    }

}
