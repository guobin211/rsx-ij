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
    "---"                  { yybegin(RUST); return RUST_START; }
    "<script>"             { yybegin(JS); return JS_START; }
    "<template>"           { yybegin(HTML); return HTML_START; }
    "<style>"              { yybegin(CSS); return CSS_START; }
    [ \t\r\n]+             { return WHITESPACE; }
    .                      { return BAD_CHARACTER; }
}

<RUST> {
    "---"                  { yybegin(YYINITIAL); return RUST_END; }
    [^-]+                  { return RUST_CODE; }
    .                      { return BAD_CHARACTER; }
}

<JS> {
    "</script>"            { yybegin(YYINITIAL); return JS_END; }
    [^<]+                  { return JS_CODE; }
    .                      { return BAD_CHARACTER; }
}

<HTML> {
    "</template>"          { yybegin(YYINITIAL); return HTML_END; }
    [^<]+                  { return HTML_CODE; }
    .                      { return BAD_CHARACTER; }
}

<CSS> {
    "</style>"             { yybegin(YYINITIAL); return CSS_END; }
    [^<]+                  { return CSS_CODE; }
    .                      { return BAD_CHARACTER; }
}
