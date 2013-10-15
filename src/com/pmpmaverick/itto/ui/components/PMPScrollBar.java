package com.pmpmaverick.itto.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class PMPScrollBar extends JScrollBar{
	
	private Properties componentProperties;
	Color enabledBackground = null;
	public PMPScrollBar(int orientation) {
        super(orientation);
        
        componentProperties = new Properties();
		try {
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		UIManager.put("ScrollBar.shadow",ColorResolver.getColor(componentProperties.getProperty("SCROLLBAR_SHADOW_COLOUR")));               
		UIManager.put("ScrollBar.highlight",ColorResolver.getColor(componentProperties.getProperty("SCROLLBAR_HIGHLIGHT_COLOUR")));            
		UIManager.put("ScrollBar.darkShadow",ColorResolver.getColor(componentProperties.getProperty("SCROLLBAR_DARK_SHADOW_COLOUR")));           
		UIManager.put("ScrollBar.thumb" ,ColorResolver.getColor(componentProperties.getProperty("SCROLLBAR_THUMB_COLOUR")));                    
	    UIManager.put("ScrollBar.thumbShadow",ColorResolver.getColor(componentProperties.getProperty("SCROLLBAR_THUMB_SHADOW_COLOUR")));          
	 	UIManager.put("ScrollBar.thumbHighlight",ColorResolver.getColor(componentProperties.getProperty("SCROLLBAR_THUMB_HIGHLIGHT_COLOUR")));       

       // this.setUI(((ComponentUI)new PMPMetalScrollBarUI()));		
        enabledBackground = ColorResolver.getColor(componentProperties.getProperty("SCROLLBAR_BACKGROUND_COLOUR"));
        this.setBackground(enabledBackground);
        ((JComponent)this.getComponent(0)).setBackground(enabledBackground);
        ((JComponent)this.getComponent(1)).setBackground(enabledBackground);
    }
    
    
    
        
    public class PMPMetalScrollBarUI extends MetalScrollBarUI {
	 
	    protected void paintThumb( Graphics g, JComponent c, Rectangle thumbBounds )
        {
            if (!c.isEnabled()) {
	        return;
	    }
    	
	    Color shadowColor         = UIManager.getColor("ScrollBar.shadow");
        Color highlightColor      = UIManager.getColor("ScrollBar.highlight");
        Color darkShadowColor     = UIManager.getColor("ScrollBar.darkShadow");
        Color thumbColor          = UIManager.getColor("ScrollBar.thumb"); //V  1.2.1
        Color thumbShadow         = UIManager.getColor("ScrollBar.thumbShadow");
        Color thumbHighlightColor = UIManager.getColor("ScrollBar.thumbHighlight");

            g.translate( thumbBounds.x, thumbBounds.y );

	    if ( scrollbar.getOrientation() == JScrollBar.VERTICAL )
	    {
	        if ( !isFreeStanding ) {
	            thumbBounds.width += 2;
	        }

	        g.setColor( thumbColor );
	        g.fillRect( 0, 0, thumbBounds.width - 2, thumbBounds.height - 1 );

	        g.setColor( thumbShadow );
	        g.drawRect( 0, 0, thumbBounds.width - 2, thumbBounds.height - 1 );
    	
	        g.setColor( thumbHighlightColor );
	        g.drawLine( 1, 1, thumbBounds.width - 3, 1 );
	        g.drawLine( 1, 1, 1, thumbBounds.height - 2 );


	        if ( !isFreeStanding ) {
	            thumbBounds.width -= 2;
	        }
	    }
	    else  
	    {
	        if ( !isFreeStanding ) {
	            thumbBounds.height += 2;
	        }

	        g.setColor( thumbColor );
	        g.fillRect( 0, 0, thumbBounds.width - 1, thumbBounds.height - 2 );

	        g.setColor( thumbShadow );
	        g.drawRect( 0, 0, thumbBounds.width - 1, thumbBounds.height - 2 );

	        g.setColor( thumbHighlightColor );
	        g.drawLine( 1, 1, thumbBounds.width - 3, 1 );
	        g.drawLine( 1, 1, 1, thumbBounds.height - 3 );


	        if ( !isFreeStanding ) {
	            thumbBounds.height -= 2;
	        }
	    }

            g.translate( -thumbBounds.x, -thumbBounds.y );
        }
    }
}
