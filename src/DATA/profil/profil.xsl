<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : profil.xsl
    Created on : 25 octobre 2022, 18:10
    Author     : admin
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:tux="http://myGame/tux"
                version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>profil.xsl</title>
            </head>
            <body>
                <h1>Profil</h1><br/>
                <h2>Description du Profil</h2>
                <!-- ici on appelle template profil-->
                <xsl:apply-templates select="//tux:profil"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="tux:profil">
        <!-- ici on affiche le nom , avatar et l'anniversaire qui se situent juste après la balise profil-->
        Nom du joueur <xsl:value-of select="tux:nom/text()"/><br/>
        Avatar : <xsl:value-of select="tux:avatar/text()"/><br/>
        Date de Naissance : <xsl:value-of select="tux:anniversaire/text()"/><br/>
        <table>
            <tr>
                <th>Numéro</th>
                <th>Date</th>
                <th>Temps</th>
                <th>Mot</th>
                <th>Niveau</th>
            </tr>
         <!-- ici on appelle template partie-->
        <xsl:apply-templates select="//tux:partie"/>
        </table>
    </xsl:template>
    <xsl:template match="tux:partie">
             <tr>
                <td><xsl:value-of select="position()"/></td>
                <td><xsl:value-of select="@date"/></td>
                <td><xsl:value-of select="tux:temps/text()"/></td>
                <td><xsl:value-of select="tux:mot/text()"/></td>
                <td><xsl:value-of select="tux:mot/@niveau"/></td>
            </tr>
    </xsl:template>
    
</xsl:stylesheet>
