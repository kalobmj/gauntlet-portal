package com.example;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("example")
public interface GauntletPortalConfig extends Config
{
	@Alpha
	@ConfigItem(
			keyName = "outlineColor",
			name = "Outline Color",
			description = "Color of the outline",
			position = 1
	)
	default Color outlineColor() { return new Color(0, 255, 255, 200); }

	@Range(min = 1, max = 6)
	@ConfigItem(
			keyName = "strokeWidth",
			name = "Outline Width",
			description = "Line width of outline",
			position = 2
	)
	default int strokeWidth() { return 2; }

	@Alpha
	@ConfigItem(
			keyName = "fillColor",
			name = "Fill Color",
			description = "Color of the fill",
			position = 3
	)
	default Color fillColor() { return new Color(0, 255, 255, 40); }

	@Range(min = 0, max = 225)
	@ConfigItem(
			keyName = "fillColorOpacity",
			name = "Fill Color Opacity",
			description = "Sets opacity of fill color",
			position = 4
	)
	default int fillOpacity() { return 40; }
}
