package com.github.guobin211.rsxij;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.github.guobin211.rsxij.RsxTypes.*;

%%

%class RsxLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%state RUST JS HTML CSS

%%

<YYINITIAL> {
    "---\n"                { yybegin(RUST); return RUST_START; }
    "<script>"             { yybegin(JS); return JS_START; }
    "<template>"           { yybegin(HTML); return HTML_START; }
    "<style>"              { yybegin(CSS); return CSS_START; }
    [\t\r]+                { return WHITESPACE; }
    .                      { return BAD_CHARACTER; }
}

<RUST> {
    "\n---"                { yybegin(YYINITIAL); return RUST_END; }
    [.]+                   { return RUST_CODE; }
}

<JS> {
    "</script>"            { yybegin(YYINITIAL); return JS_END; }
    [.]+                   { return JS_CODE; }
}

<HTML> {
    "</template>"          { yybegin(YYINITIAL); return HTML_END; }
    [.]+                   { return HTML_CODE; }
}

<CSS> {
    "</style>"             { yybegin(YYINITIAL); return CSS_END; }
    [.]+                   { return CSS_CODE; }
}
