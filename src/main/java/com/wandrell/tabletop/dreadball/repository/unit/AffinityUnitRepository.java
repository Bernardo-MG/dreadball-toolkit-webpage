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

package com.wandrell.tabletop.dreadball.repository.unit;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.PersistentAffinityUnit;

/**
 * Affinity units repository.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public interface AffinityUnitRepository
        extends PagingAndSortingRepository<PersistentAffinityUnit, Integer> {

    /**
     * Returns all the affinity units which does not hate any of the received
     * affinities.
     * 
     * @param affinities
     *            affinities the units should not hate
     * @param pageReq
     *            pagination request
     * @return all the units not hating any of the affinities
     */
    @Query("SELECT u FROM AffinityUnit u LEFT OUTER JOIN u.hated h WHERE h IS NULL OR h.name NOT IN :affinities")
    public Page<PersistentAffinityUnit> findAllFilteredByHatedAffinities(
            @Param("affinities") final Iterable<String> affinities,
            final Pageable pageReq);

    /**
     * Returns the affinity unit with the specified template name.
     * 
     * @param name
     *            template name to search for
     * @return the affinity unit with the specified template name
     */
    public PersistentAffinityUnit findOneByTemplateName(final String name);

    public Collection<PersistentAffinityUnit>
            findByTemplateName(final Iterable<String> names);

}