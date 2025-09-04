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

    // need to:
    // get object when it spawns, match with portal id, then save that object
    // then we can work with that object. (graphics)

    // then we will:
    // grab portal object
    // outline it
    // fill it in (optional)
    // get kc (call kc api) (might have to update manually)
    // display kc on portal

    @Override
    public Dimension render(Graphics2D g)
    {
        // gauntlet portal object id
        int gauntletId = 36086;

        GauntletPortalConfig c = plugin.getConfig();
        ObjectComposition GauntletPortalObject = client.getObjectDefinition(gauntletId);

        // Nice crisp lines:
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // outline width
        BasicStroke stroke = new BasicStroke(c.strokeWidth());
        g.setStroke(stroke);

//        Shape hull = GauntletPortalObject.
//        client.get

        return null;

    }
}
