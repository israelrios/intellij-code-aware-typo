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

import com.intellij.psi.PsiAnnotationParameterList;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiNameValuePair;
import org.jetbrains.annotations.NotNull;

/**
 * Java-specific strategy that keeps ignoring spellchecks for annotated string literals with references.
 */
public class JavaNoNamedElementSpellStrategy extends NoNamedElementSpellStrategy {

    private boolean isInsideAnnotationAttribute(PsiLiteralExpression element) {
        PsiElement parent = element.getParent();
        return parent instanceof PsiNameValuePair &&
            parent.getParent() instanceof PsiAnnotationParameterList;
    }

    @Override
    public boolean isMyContext(@NotNull PsiElement element) {
        if (element instanceof PsiLiteralExpression literalExpression) {
            return isEnabled(element) && isInsideAnnotationAttribute(literalExpression)
                   && literalExpression.getReferences().length > 0;
        }
        return super.isMyContext(element);
    }
}
