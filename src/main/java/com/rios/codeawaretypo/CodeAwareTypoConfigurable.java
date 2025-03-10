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

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CodeAwareTypoConfigurable implements Configurable {

    private final Project project;
    private CodeAwareTypoSettingsPanel settingsPanel;

    public CodeAwareTypoConfigurable(Project project) {
        this.project = project;
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Code-Aware Typo";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsPanel = new CodeAwareTypoSettingsPanel(project);
        return settingsPanel.getPanel();
    }

    @Override
    public boolean isModified() {
        CodeAwareTypoProjectSettings settings = CodeAwareTypoProjectSettings.getInstance(project);
        return settingsPanel.isEnabled() != settings.isEnabled();
    }

    @Override
    public void apply() {
        CodeAwareTypoProjectSettings settings = CodeAwareTypoProjectSettings.getInstance(project);
        settings.setEnabled(settingsPanel.isEnabled());
    }

    @Override
    public void reset() {
        CodeAwareTypoProjectSettings settings = CodeAwareTypoProjectSettings.getInstance(project);
        settingsPanel.setEnabled(settings.isEnabled());
    }

    @Override
    public void disposeUIResources() {
        settingsPanel = null;
    }
}