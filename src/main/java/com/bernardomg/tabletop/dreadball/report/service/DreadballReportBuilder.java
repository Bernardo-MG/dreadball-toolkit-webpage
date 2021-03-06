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

package com.bernardomg.tabletop.dreadball.report.service;

import java.io.IOException;
import java.io.OutputStream;

import com.bernardomg.tabletop.dreadball.model.team.SponsorTeam;
import com.itextpdf.text.DocumentException;

/**
 * Report builder.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public interface DreadballReportBuilder {

    /**
     * Creates a PDF report and stores it in the output.
     * 
     * @param team
     *            team for the report
     * @param output
     *            output where the report will be stored
     * 
     * @throws IOException
     *             if there was an I/O error
     * @throws DocumentException
     *             if there was an error generating the document
     */
    public void createPdf(final SponsorTeam team, final OutputStream output)
            throws IOException, DocumentException;

}
