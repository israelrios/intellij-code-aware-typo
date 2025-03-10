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

import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;

public class CodeAwareTypoSettingsPanel {

    private final JPanel mainPanel;
    private final JCheckBox enabledCheckBox;

    public CodeAwareTypoSettingsPanel(Project project) {
        mainPanel = new JPanel(new BorderLayout());
        enabledCheckBox = new JCheckBox("Disable typo inspection for referencable code elements");

        CodeAwareTypoProjectSettings settings = CodeAwareTypoProjectSettings.getInstance(project);
        enabledCheckBox.setSelected(settings.isEnabled());

        mainPanel.add(enabledCheckBox, BorderLayout.NORTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public boolean isEnabled() {
        return enabledCheckBox.isSelected();
    }

    public void setEnabled(boolean enabled) {
        enabledCheckBox.setSelected(enabled);
    }
}