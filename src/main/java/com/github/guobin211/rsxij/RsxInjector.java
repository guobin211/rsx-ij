package com.github.guobin211.rsxij;

import com.intellij.lang.Language;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RsxInjector implements MultiHostInjector {
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement context) {
        if (context instanceof PsiLanguageInjectionHost) {
            injectLanguage(registrar, context, RsxTypes.CSS_CODE, "CSS");
            injectLanguage(registrar, context, RsxTypes.JS_CODE, "JavaScript");
            injectLanguage(registrar, context, RsxTypes.HTML_CODE, "HTML");
        }
    }

    private void injectLanguage(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement context, @NotNull IElementType elementType, @NotNull String languageID) {
        PsiElement block = findChildByType(context, elementType);
        if (block != null) {
            registrar.startInjecting(Objects.requireNonNull(Language.findLanguageByID(languageID)))
                .addPlace(null, null, (PsiLanguageInjectionHost) context, new TextRange(block.getStartOffsetInParent(), block.getTextLength()))
                .doneInjecting();
        }
    }

    private PsiElement findChildByType(@NotNull PsiElement context, @NotNull IElementType elementType) {
        for (PsiElement child : context.getChildren()) {
            if (child.getNode().getElementType() == elementType) {
                return child;
            }
        }
        return null;
    }

    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Collections.singletonList(PsiLanguageInjectionHost.class);
    }
}
