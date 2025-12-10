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

import org.jetbrains.annotations.NotNull;

import com.intellij.lang.javascript.psi.JSLiteralExpression;
import com.intellij.psi.PsiElement;

public class JavascriptNoNamedElementSpellStrategy extends NoNamedElementSpellStrategy {

    @Override
    public boolean isMyContext(@NotNull PsiElement element) {
        if (element instanceof JSLiteralExpression jsle) {
            if (!isEnabled(jsle) || !jsle.isStringLiteral()) {
                return false;
            }

            // Here we should return true when the element references resolve to something. But it's expensive to run.
        }
        return super.isMyContext(element);
    }
}
