parser grammar XmlParser;

options { tokenVocab=XmlLexer; }

document                    :   prolog? misc*? element misc*;

tagWs                       :   TAG_WS;

prolog                      :   '<' OPEN_XMLDECL (tagWs attribute)* tagWs* CLOSE_TAG_SPECIAL;

content                     :   chardata*?
                                ((element | cdata | comment) chardata?)*? ;

cdataStart                  :   OPEN_CDATA;

cdataStop                   :   CLOSE_CDATA;

cdata                       :   cdataStart CDATA_CHARS* cdataStop;

tagname                     :   TAG_NAME | CLOSE_TAG_NAME;

emptytag                    :   '<' tagname tagWs* (attribute tagWs*)* CLOSE_TAG_SLASH_BRACE;

opentag                     :   '<' tagname tagWs* (attribute tagWs*)* CLOSE_TAG_BRACE;

closetag                    :   '</' tagname CLOSE_TAG_CLOSE_BRACE;

element                     :   emptytag | opentag content closetag;

chardata                    :   TEXT;

misc                        :   comment
                            |   TEXT;

commentStart                :   OPEN_COMMENT;

commentStop                 :   CLOSE_COMMENT;

comment                     :   commentStart COMMENT_CHARS* commentStop;

attributeName               :   ATTRIBUTE_NAME;

attributeValue              :   ATTRIBUTE_VALUE0 | ATTRIBUTE_VALUE1;

attributeBrace              :   ATTRIBUTE_VALUE_BRACE0 | ATTRIBUTE_VALUE_BRACE1 | ATTRIBUTE_VALUE_BRACE01 | ATTRIBUTE_VALUE_BRACE11 ;

attribute                   :   attributeName TAG_WS* '=' TAG_WS* attributeBrace attributeValue? attributeBrace;
