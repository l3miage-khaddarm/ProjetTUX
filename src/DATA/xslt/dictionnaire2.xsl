<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:tux="http://myGame/tux"
                version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>dictionnaire.xsl</title>
                
            </head>
            <body>
                <h1>Dictionnaire</h1><br/>
                <h2>les mots du dictionnaire sont:</h2>
                  <!-- on appelle la template mot  -->
                <xsl:apply-templates select="//tux:mot">
                    <!--on trie les mots en ordre alphabétique -->
                    <xsl:sort select="tux:contenu" order="ascending"/>
                </xsl:apply-templates>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="tux:mot">
        <!-- on affiche les mots dans contenu -->
        <xsl:value-of select="tux:contenu/text()"/><br/>
        <!--on affiche le niveau de chaque mot -->
        <p>le niveau du mot : <xsl:value-of select="tux:niveau/text()"/></p>
    </xsl:template>
</xsl:stylesheet>

