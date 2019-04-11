lexer grammar XmlLexer;


OPEN_COMMENT                    :   '<!--'                                      -> pushMode(MCOMMENT);
CLOSE                           :   '</'                                        -> pushMode(MCLOSE_TAG);
OPEN_CDATA                      :   '<![CDATA['                                 -> pushMode(MCDATA);
OPEN                            :   '<'                                         -> pushMode(INSIDE);
TEXT                            :   ~[<&]*;        // match any 16 bit char other than < and &

mode INSIDE;
OPEN_XMLDECL                    :   '?xml'                                      -> pushMode(MTAG);
TAG_NAME                        :   Name                                        -> pushMode(MTAG);

mode MCDATA;
CLOSE_CDATA                     :   ']]>'                                       -> popMode;
CDATA_CHARS                     :   (~']'+? | (']'+? (~']' ~']' ~'>')?));

mode MCLOSE_TAG;
CLOSE_TAG_NAME                  :   Name;
CLOSE_TAG_WS                    :   ' '+                                        -> skip;
CLOSE_TAG_CLOSE_BRACE           :   '>'                                         -> popMode;


mode MTAG;
TAG_WS                          :   (' '|'\t'|'\r'? '\n')+;
CLOSE_TAG_BRACE                 :   '>'                                         -> popMode, popMode;
CLOSE_TAG_SLASH_BRACE           :   '/>'                                        -> popMode, popMode;
CLOSE_TAG_SPECIAL               :   '?>'                                        -> popMode, popMode;
ATTRIBUTE_NAME                  :   Name;
EQUALS                          :   '=' ;
ATTRIBUTE_VALUE_BRACE0          :   AttributeBrace0                                  -> pushMode(MATTRIBUTE_VALUE0);
ATTRIBUTE_VALUE_BRACE1          :   AttributeBrace1                                  -> pushMode(MATTRIBUTE_VALUE1);

mode MATTRIBUTE_VALUE0;
ATTRIBUTE_VALUE0                 :   ~[<"]*;
ATTRIBUTE_VALUE_BRACE01          :   AttributeBrace0                              -> popMode;

mode MATTRIBUTE_VALUE1;
ATTRIBUTE_VALUE1                 :   ~[<']*;
ATTRIBUTE_VALUE_BRACE11          :   AttributeBrace1                              -> popMode;

mode MCOMMENT;
CLOSE_COMMENT                   :   '-->'                                       -> popMode;
COMMENT_CHARS                   :   (~'-'* | ('-'+? (~'-' ~'-' ~'>')?));

fragment
HEXDIGIT                        :   [a-fA-F0-9] ;

fragment
DIGIT                           :   [0-9] ;

fragment
NameChar                        :   NameStartChar
                                |   '-' | '_' | '.' | DIGIT
                                |   '\u00B7'
                                |   '\u0300'..'\u036F'
                                |   '\u203F'..'\u2040'
                                ;

fragment
NameStartChar                   :   [:a-zA-Z]
                                |   '\u2070'..'\u218F'
                                |   '\u2C00'..'\u2FEF'
                                |   '\u3001'..'\uD7FF'
                                |   '\uF900'..'\uFDCF'
                                |   '\uFDF0'..'\uFFFD'
                                ;

fragment
Name                            :   NameStartChar NameChar* ;

fragment
AttributeBrace0                  :   '"';
AttributeBrace1                  :   '\'';