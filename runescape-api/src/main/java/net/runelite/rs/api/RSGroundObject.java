package net.runelite.rs.api;

import net.runelite.api.GroundObject;
import net.runelite.mapping.Import;

public interface RSGroundObject extends GroundObject
{
	@Import("tag")
	@Override
	long getHash();

	@Import("x")
	int getX();

	@Import("y")
	int getY();

	@Import("renderable")
	@Override
	RSRenderable getRenderable();

	@Import("flags")
	@Override
	int getConfig();

	void setPlane(int plane);
}
