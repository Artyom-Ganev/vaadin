/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tests.server.component.listselect;

import java.util.Arrays;

import org.junit.Test;

import com.vaadin.tests.design.DeclarativeTestBase;
import com.vaadin.ui.ListSelect;

public class ListSelectDeclarativeTest
        extends DeclarativeTestBase<ListSelect<String>> {

    private ListSelect<String> getWithOptionsExpected() {
        ListSelect<String> ls = new ListSelect<>(null,
                Arrays.asList("Male", "Female"));
        ls.setRows(9); // 10 is default
        return ls;
    }

    private String getWithOptionsDesign() {
        return "<vaadin-list-select rows=9>\n"
                + "        <option>Male</option>\n"
                + "        <option>Female</option>\n"
                + "</vaadin-list-select>\n" + "";
    }

    @Test
    public void testReadWithOptions() {
        testRead(getWithOptionsDesign(), getWithOptionsExpected());
    }

    @Test
    public void testWriteWithOptions() {
        testWrite(stripOptionTags(getWithOptionsDesign()),
                getWithOptionsExpected());
    }

    private ListSelect<String> getBasicExpected() {
        ListSelect<String> ls = new ListSelect<>();
        ls.setCaption("Hello");
        return ls;
    }

    private String getBasicDesign() {
        return "<vaadin-list-select caption='Hello' />";
    }

    @Test
    public void testReadBasic() {
        testRead(getBasicDesign(), getBasicExpected());
    }

    @Test
    public void testWriteBasic() {
        testWrite(getBasicDesign(), getBasicExpected());
    }

}
