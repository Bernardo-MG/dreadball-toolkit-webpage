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

/**
 * Autowired implementation of the {@code SponsorDefaults}. It loads all the
 * data from the Spring context.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Component("SponsorDefaults")
public final class AutowiredSponsorDefaults implements SponsorDefaults {

    /**
     * Initial rank.
     */
    @Value("${sponsor.rank.initial}")
    private Integer initialRank;

    /**
     * Constructs the sponsor defaults.
     */
    public AutowiredSponsorDefaults() {
        super();
    }

    @Override
    public final Integer getInitialRank() {
        return initialRank;
    }

}
