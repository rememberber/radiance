/*
 * Copyright (c) 2005-2019 Neon Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of Neon Kirill Grouchnikov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.neon.internal.font;

import org.pushingpixels.neon.font.FontSet;

import javax.swing.plaf.FontUIResource;
import java.awt.*;

/**
 * Font set implementation for Neon.
 *
 * @author Kirill Grouchnikov
 */
public class NeonFontSet implements FontSet {
    /**
     * The default system font set.
     */
    private FontSet systemFontSet;

    /**
     * Creates a new Neon font set.
     *
     * @param systemFontSet The default system font set.
     */
    public NeonFontSet(FontSet systemFontSet) {
        this.systemFontSet = systemFontSet;
    }

    /**
     * Returns Neon-specific font resource.
     *
     * @param systemFont The default system font.
     * @return Neon-specific font resource.
     */
    private FontUIResource getNeonFont(FontUIResource systemFont) {
        return systemFont;
    }

    /**
     * Returns Neon-specific font resource.
     *
     * @param systemFont    The default system font.
     * @param toBoldify     If <code>true</code>, the original font (the first parameter) is
     *                      boldified.
     * @param extraFontSize Extra font size in pixels.
     * @return Neon-specific font resource.
     */
    private FontUIResource getNeonFont(FontUIResource systemFont, boolean toBoldify,
            int extraFontSize) {
        boolean isOrigItalic = systemFont.isItalic();
        int newStyle = systemFont.getStyle();
        if (toBoldify) {
            if (isOrigItalic) {
                newStyle = Font.ITALIC + Font.BOLD;
            } else {
                newStyle = Font.BOLD;
            }
        }
        Font derived = systemFont.deriveFont((float) (systemFont.getSize() + extraFontSize))
                .deriveFont(newStyle);
        if (derived instanceof FontUIResource) {
            return (FontUIResource) derived;
        }
        return new FontUIResource(derived);
    }

    public FontUIResource getControlFont() {
        return this.getNeonFont(this.systemFontSet.getControlFont());
    }

    public FontUIResource getMenuFont() {
        return this.getNeonFont(this.systemFontSet.getMenuFont());
    }

    public FontUIResource getMessageFont() {
        return this.getNeonFont(this.systemFontSet.getMessageFont());
    }

    public FontUIResource getSmallFont() {
        return this.getNeonFont(this.systemFontSet.getSmallFont(), false, 1);
    }

    public FontUIResource getTitleFont() {
        return this.getNeonFont(this.systemFontSet.getTitleFont());
    }

    public FontUIResource getWindowTitleFont() {
        return this.getNeonFont(this.systemFontSet.getWindowTitleFont(), true, 1);
    }
}
