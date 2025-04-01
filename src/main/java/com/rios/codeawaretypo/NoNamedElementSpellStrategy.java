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

import com.intellij.psi.*;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import org.jetbrains.annotations.NotNull;

public class NoNamedElementSpellStrategy extends SpellcheckingStrategy {

    private boolean isInsideAnnotationAttribute(PsiLiteralExpression element) {
        PsiElement parent = element.getParent();
        return parent instanceof PsiNameValuePair &&
            parent.getParent() instanceof PsiAnnotationParameterList;
    }

    protected boolean hasReferences(PsiLiteralValue element) {
        if (!isEnabled(element)) {
            return false;
        }

        return element.getReferences().length > 0;
    }

    @NotNull
    @Override
    public Tokenizer<?> getTokenizer(PsiElement element) {
        return EMPTY_TOKENIZER;
    }

    @Override
    public boolean isMyContext(@NotNull PsiElement element) {
        return switch (element) {
        case PsiNamedElement psiNamedElement -> isEnabled(psiNamedElement);
        case PsiLiteralExpression literalExpression -> {

            // Check if we're inside an annotation attribute
            if (isInsideAnnotationAttribute(literalExpression)) {
                // Check if the string has a resolvable reference
                yield hasReferences(literalExpression);
            }
            yield false;
        }
        default -> false;
        };
    }

    protected static boolean isEnabled(@NotNull PsiElement element) {
        return CodeAwareTypoProjectSettings.getInstance(element.getProject()).isEnabled();
    }
}