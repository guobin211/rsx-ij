package com.github.guobin211.rsxij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class RsxParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(RsxLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new RsxLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new RsxParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return RsxTokenSets.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode astNode) {
        return null;
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider fileViewProvider) {
        return new RsxFile(fileViewProvider);
    }
}
