/*
 * Copyright (c) 2025  Israel Rios
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.rios.codeawaretypo;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

@Service(Service.Level.PROJECT)
@State(
    name = "com.rios.CodeAwareTypo",
    storages = @Storage("CodeAwareTypo.xml"))
public final class CodeAwareTypoProjectSettings
    implements PersistentStateComponent<CodeAwareTypoProjectSettings.MyState> {

    private final MyState state = new MyState();

    public static CodeAwareTypoProjectSettings getInstance(Project project) {
        return project.getService(CodeAwareTypoProjectSettings.class);
    }

    @Override
    public MyState getState() {
        return state;
    }

    public boolean isEnabled() {
        return state.enabled;
    }

    public void setEnabled(boolean enabled) {
        state.enabled = enabled;
    }

    @Override
    public void loadState(@NotNull MyState state) {
        XmlSerializerUtil.copyBean(state, this.state);
    }

    public static class MyState {
        public boolean enabled = true; // Default: enabled
    }
}