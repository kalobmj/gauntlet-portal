package com.example;

import javax.inject.Inject;
import java.awt.*;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.GameObject;
import net.runelite.api.Client;
import net.runelite.api.ObjectComposition;
import net.runelite.client.ui.overlay.*;

@Slf4j
public class GauntletPortalOverlay extends Overlay
{

    private final Client client;
    private final GauntletPortalConfig config;
    private final GauntletPortalPlugin plugin;

    @Inject
    private GauntletPortalOverlay(Client client,
                                  GauntletPortalConfig config,
                                  GauntletPortalPlugin plugin)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.HIGH);
        this.client = client;
        this.config = config;
        this.plugin = plugin;
    }

    // then we will:
    // grab portal object
    // outline it
    // fill it in (optional)
    // get kc (call kc api) (might have to update manually)
    // display kc on portal

    @Override
    public Dimension render(Graphics2D g)
    {
        GauntletPortalConfig c = plugin.getConfig();
        Set<GameObject> objs = plugin.getGauntletObjects();

        if (objs.isEmpty()) { return null; }

        // Nice crisp lines:
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // outline width
        BasicStroke stroke = new BasicStroke(c.strokeWidth());
        g.setStroke(stroke);

        for (GameObject obj : objs)
        {
            if (obj == null || obj.getConvexHull() == null)
                continue;

            Shape hull = obj.getConvexHull();

            // Outline
            g.setColor(c.outlineColor());
            g.draw(hull);

            // have config variables for : portal color, chest color
            // both colors will have opacity settings
            // have config variables for fill colors
            //  will have opacity settings
        }
        return null;
    }
}
