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

    protected boolean hasReferences(PsiLiteralValue element) {
        if (!isEnabled(element)) {
            return false;
        }

        for (PsiReference reference : element.getReferences()) {
            if (reference instanceof PsiPolyVariantReference polyVariantReference) {
                for (ResolveResult resolveResult : polyVariantReference.multiResolve(false)) {
                    PsiElement target = resolveResult.getElement();
                    if (target != null && !element.isEquivalentTo(target)) {
                        return true;
                    }
                }
            }

            PsiElement target = reference.resolve();
            if (target != null && !element.isEquivalentTo(target)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    @Override
    public Tokenizer<?> getTokenizer(PsiElement element) {
        return EMPTY_TOKENIZER;
    }

    @Override
    public boolean isMyContext(@NotNull PsiElement element) {
        if (element instanceof PsiNamedElement psiNamedElement) {
            return isEnabled(psiNamedElement);
        }
        return false;
    }

    protected static boolean isEnabled(@NotNull PsiElement element) {
        return CodeAwareTypoProjectSettings.getInstance(element.getProject()).isEnabled();
    }
}
