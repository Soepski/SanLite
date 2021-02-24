import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("fz")
@Implements("MilliClock")
public class MilliClock extends Clock {
	@ObfuscatedName("ew")
	@ObfuscatedGetter(
		intValue = -1909140371
	)
	static int field2032;
	@ObfuscatedName("gr")
	@ObfuscatedSignature(
		signature = "Leq;"
	)
	@Export("scene")
	static Scene scene;
	@ObfuscatedName("n")
	long[] field2029;
	@ObfuscatedName("v")
	@ObfuscatedGetter(
		intValue = 1115691505
	)
	int field2027;
	@ObfuscatedName("d")
	@ObfuscatedGetter(
		intValue = -1924917613
	)
	int field2026;
	@ObfuscatedName("c")
	@ObfuscatedGetter(
		longValue = 4130614396061915879L
	)
	long field2028;
	@ObfuscatedName("y")
	@ObfuscatedGetter(
		intValue = -1837898463
	)
	int field2030;
	@ObfuscatedName("h")
	@ObfuscatedGetter(
		intValue = 730128979
	)
	int field2031;

	public MilliClock() {
		this.field2029 = new long[10];
		this.field2027 = 256;
		this.field2026 = 1;
		this.field2030 = 0;
		this.field2028 = Tiles.currentTimeMillis();

		for (int var1 = 0; var1 < 10; ++var1) {
			this.field2029[var1] = this.field2028;
		}

	}

	@ObfuscatedName("n")
	@ObfuscatedSignature(
		signature = "(B)V",
		garbageValue = "46"
	)
	@Export("mark")
	public void mark() {
		for (int var1 = 0; var1 < 10; ++var1) {
			this.field2029[var1] = 0L;
		}

	}

	@ObfuscatedName("v")
	@ObfuscatedSignature(
		signature = "(III)I",
		garbageValue = "1861957348"
	)
	@Export("wait")
	public int wait(int var1, int var2) {
		int var3 = this.field2027;
		int var4 = this.field2026;
		this.field2027 = 300;
		this.field2026 = 1;
		this.field2028 = Tiles.currentTimeMillis();
		if (this.field2029[this.field2031] == 0L) {
			this.field2027 = var3;
			this.field2026 = var4;
		} else if (this.field2028 > this.field2029[this.field2031]) {
			this.field2027 = (int)((long)(var1 * 2560) / (this.field2028 - this.field2029[this.field2031]));
		}

		if (this.field2027 < 25) {
			this.field2027 = 25;
		}

		if (this.field2027 > 256) {
			this.field2027 = 256;
			this.field2026 = (int)((long)var1 - (this.field2028 - this.field2029[this.field2031]) / 10L);
		}

		if (this.field2026 > var1) {
			this.field2026 = var1;
		}

		this.field2029[this.field2031] = this.field2028;
		this.field2031 = (this.field2031 + 1) % 10;
		int var5;
		if (this.field2026 > 1) {
			for (var5 = 0; var5 < 10; ++var5) {
				if (this.field2029[var5] != 0L) {
					this.field2029[var5] += (long)this.field2026;
				}
			}
		}

		if (this.field2026 < var2) {
			this.field2026 = var2;
		}

		ApproximateRouteStrategy.method1311((long)this.field2026);

		for (var5 = 0; this.field2030 < 256; this.field2030 += this.field2027) {
			++var5;
		}

		this.field2030 &= 255;
		return var5;
	}

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		signature = "(Lig;Ljava/lang/String;Ljava/lang/String;I)[Lly;",
		garbageValue = "1085285286"
	)
	public static IndexedSprite[] method3664(AbstractArchive var0, String var1, String var2) {
		int var3 = var0.getGroupId(var1);
		int var4 = var0.getFileId(var3, var2);
		return BoundaryObject.method3420(var0, var3, var4);
	}

	@ObfuscatedName("y")
	@ObfuscatedSignature(
		signature = "(IB)I",
		garbageValue = "9"
	)
	@Export("Messages_getHistorySize")
	static int Messages_getHistorySize(int var0) {
		ChatChannel var1 = (ChatChannel)Messages.Messages_channels.get(var0);
		return var1 == null ? 0 : var1.size();
	}

	@ObfuscatedName("b")
	@ObfuscatedSignature(
		signature = "(Lkl;Lkl;Lkl;I)V",
		garbageValue = "737300507"
	)
	@Export("drawTitle")
	static void drawTitle(Font var0, Font var1, Font var2) {
		Login.xPadding = (GrandExchangeOfferTotalQuantityComparator.canvasWidth - 765) / 2;
		Login.loginBoxX = Login.xPadding + 202;
		GraphicsObject.loginBoxCenter = Login.loginBoxX + 180;
		byte var3;
		int var4;
		int var6;
		int var7;
		int var9;
		int var30;
		int var32;
		if (Login.worldSelectOpen) {
			if (WorldMapID.worldSelectBackSprites == null) {
				WorldMapID.worldSelectBackSprites = WorldMapID.method648(ModeWhere.archive8, "sl_back", "");
			}

			if (WorldMapManager.worldSelectFlagSprites == null) {
				WorldMapManager.worldSelectFlagSprites = method3664(ModeWhere.archive8, "sl_flags", "");
			}

			if (DevicePcmPlayerProvider.worldSelectArrows == null) {
				DevicePcmPlayerProvider.worldSelectArrows = method3664(ModeWhere.archive8, "sl_arrows", "");
			}

			if (Occluder.worldSelectStars == null) {
				Occluder.worldSelectStars = method3664(ModeWhere.archive8, "sl_stars", "");
			}

			if (class7.worldSelectLeftSprite == null) {
				class7.worldSelectLeftSprite = ChatChannel.SpriteBuffer_getIndexedSpriteByName(ModeWhere.archive8, "leftarrow", "");
			}

			if (class2.worldSelectRightSprite == null) {
				class2.worldSelectRightSprite = ChatChannel.SpriteBuffer_getIndexedSpriteByName(ModeWhere.archive8, "rightarrow", "");
			}

			Rasterizer2D.Rasterizer2D_fillRectangle(Login.xPadding, 23, 765, 480, 0);
			Rasterizer2D.Rasterizer2D_fillRectangleGradient(Login.xPadding, 0, 125, 23, 12425273, 9135624);
			Rasterizer2D.Rasterizer2D_fillRectangleGradient(Login.xPadding + 125, 0, 640, 23, 5197647, 2697513);
			var0.drawCentered("Select a world", Login.xPadding + 62, 15, 0, -1);
			if (Occluder.worldSelectStars != null) {
				Occluder.worldSelectStars[1].drawAt(Login.xPadding + 140, 1);
				var1.draw("Members only world", Login.xPadding + 152, 10, 16777215, -1);
				Occluder.worldSelectStars[0].drawAt(Login.xPadding + 140, 12);
				var1.draw("Free world", Login.xPadding + 152, 21, 16777215, -1);
			}

			if (DevicePcmPlayerProvider.worldSelectArrows != null) {
				var30 = Login.xPadding + 280;
				if (World.World_sortOption1[0] == 0 && World.World_sortOption2[0] == 0) {
					DevicePcmPlayerProvider.worldSelectArrows[2].drawAt(var30, 4);
				} else {
					DevicePcmPlayerProvider.worldSelectArrows[0].drawAt(var30, 4);
				}

				if (World.World_sortOption1[0] == 0 && World.World_sortOption2[0] == 1) {
					DevicePcmPlayerProvider.worldSelectArrows[3].drawAt(var30 + 15, 4);
				} else {
					DevicePcmPlayerProvider.worldSelectArrows[1].drawAt(var30 + 15, 4);
				}

				var0.draw("World", var30 + 32, 17, 16777215, -1);
				var4 = Login.xPadding + 390;
				if (World.World_sortOption1[0] == 1 && World.World_sortOption2[0] == 0) {
					DevicePcmPlayerProvider.worldSelectArrows[2].drawAt(var4, 4);
				} else {
					DevicePcmPlayerProvider.worldSelectArrows[0].drawAt(var4, 4);
				}

				if (World.World_sortOption1[0] == 1 && World.World_sortOption2[0] == 1) {
					DevicePcmPlayerProvider.worldSelectArrows[3].drawAt(var4 + 15, 4);
				} else {
					DevicePcmPlayerProvider.worldSelectArrows[1].drawAt(var4 + 15, 4);
				}

				var0.draw("Players", var4 + 32, 17, 16777215, -1);
				var32 = Login.xPadding + 500;
				if (World.World_sortOption1[0] == 2 && World.World_sortOption2[0] == 0) {
					DevicePcmPlayerProvider.worldSelectArrows[2].drawAt(var32, 4);
				} else {
					DevicePcmPlayerProvider.worldSelectArrows[0].drawAt(var32, 4);
				}

				if (World.World_sortOption1[0] == 2 && World.World_sortOption2[0] == 1) {
					DevicePcmPlayerProvider.worldSelectArrows[3].drawAt(var32 + 15, 4);
				} else {
					DevicePcmPlayerProvider.worldSelectArrows[1].drawAt(var32 + 15, 4);
				}

				var0.draw("Location", var32 + 32, 17, 16777215, -1);
				var6 = Login.xPadding + 610;
				if (World.World_sortOption1[0] == 3 && World.World_sortOption2[0] == 0) {
					DevicePcmPlayerProvider.worldSelectArrows[2].drawAt(var6, 4);
				} else {
					DevicePcmPlayerProvider.worldSelectArrows[0].drawAt(var6, 4);
				}

				if (World.World_sortOption1[0] == 3 && World.World_sortOption2[0] == 1) {
					DevicePcmPlayerProvider.worldSelectArrows[3].drawAt(var6 + 15, 4);
				} else {
					DevicePcmPlayerProvider.worldSelectArrows[1].drawAt(var6 + 15, 4);
				}

				var0.draw("Type", var6 + 32, 17, 16777215, -1);
			}

			Rasterizer2D.Rasterizer2D_fillRectangle(Login.xPadding + 708, 4, 50, 16, 0);
			var1.drawCentered("Cancel", Login.xPadding + 708 + 25, 16, 16777215, -1);
			Login.hoveredWorldIndex = -1;
			if (WorldMapID.worldSelectBackSprites != null) {
				var3 = 88;
				byte var37 = 19;
				var32 = 765 / (var3 + 1) - 1;
				var6 = 480 / (var37 + 1);

				int var35;
				do {
					var7 = var6;
					var35 = var32;
					if (var6 * (var32 - 1) >= World.World_count) {
						--var32;
					}

					if (var32 * (var6 - 1) >= World.World_count) {
						--var6;
					}

					if (var32 * (var6 - 1) >= World.World_count) {
						--var6;
					}
				} while(var7 != var6 || var35 != var32);

				var7 = (765 - var32 * var3) / (var32 + 1);
				if (var7 > 5) {
					var7 = 5;
				}

				var35 = (480 - var6 * var37) / (var6 + 1);
				if (var35 > 5) {
					var35 = 5;
				}

				var9 = (765 - var32 * var3 - var7 * (var32 - 1)) / 2;
				int var38 = (480 - var6 * var37 - var35 * (var6 - 1)) / 2;
				int var11 = (var6 + World.World_count - 1) / var6;
				Login.worldSelectPagesCount = var11 - var32;
				if (class7.worldSelectLeftSprite != null && Login.worldSelectPage > 0) {
					class7.worldSelectLeftSprite.drawAt(8, class25.canvasHeight / 2 - class7.worldSelectLeftSprite.subHeight / 2);
				}

				if (class2.worldSelectRightSprite != null && Login.worldSelectPage < Login.worldSelectPagesCount) {
					class2.worldSelectRightSprite.drawAt(GrandExchangeOfferTotalQuantityComparator.canvasWidth - class2.worldSelectRightSprite.subWidth - 8, class25.canvasHeight / 2 - class2.worldSelectRightSprite.subHeight / 2);
				}

				int var12 = var38 + 23;
				int var13 = var9 + Login.xPadding;
				int var14 = 0;
				boolean var15 = false;
				int var16 = Login.worldSelectPage;

				int var17;
				for (var17 = var16 * var6; var17 < World.World_count && var16 - Login.worldSelectPage < var32; ++var17) {
					World var26 = World.World_worlds[var17];
					boolean var19 = true;
					String var20 = Integer.toString(var26.population);
					if (var26.population == -1) {
						var20 = "OFF";
						var19 = false;
					} else if (var26.population > 1980) {
						var20 = "FULL";
						var19 = false;
					}

					int var22 = 0;
					byte var21;
					if (var26.isBeta()) {
						if (var26.isMembersOnly()) {
							var21 = 7;
						} else {
							var21 = 6;
						}
					} else if (var26.isDeadman()) {
						var22 = 16711680;
						if (var26.isMembersOnly()) {
							var21 = 5;
						} else {
							var21 = 4;
						}
					} else if (var26.method1883()) {
						if (var26.isMembersOnly()) {
							var21 = 9;
						} else {
							var21 = 8;
						}
					} else if (var26.isPvp()) {
						if (var26.isMembersOnly()) {
							var21 = 3;
						} else {
							var21 = 2;
						}
					} else if (var26.isMembersOnly()) {
						var21 = 1;
					} else {
						var21 = 0;
					}

					if (MouseHandler.MouseHandler_x >= var13 && MouseHandler.MouseHandler_y >= var12 && MouseHandler.MouseHandler_x < var13 + var3 && MouseHandler.MouseHandler_y < var37 + var12 && var19) {
						Login.hoveredWorldIndex = var17;
						WorldMapID.worldSelectBackSprites[var21].drawTransOverlayAt(var13, var12, 128, 16777215);
						var15 = true;
					} else {
						WorldMapID.worldSelectBackSprites[var21].drawAt(var13, var12);
					}

					if (WorldMapManager.worldSelectFlagSprites != null) {
						WorldMapManager.worldSelectFlagSprites[(var26.isMembersOnly() ? 8 : 0) + var26.location].drawAt(var13 + 29, var12);
					}

					var0.drawCentered(Integer.toString(var26.id), var13 + 15, var37 / 2 + var12 + 5, var22, -1);
					var1.drawCentered(var20, var13 + 60, var37 / 2 + var12 + 5, 268435455, -1);
					var12 = var12 + var37 + var35;
					++var14;
					if (var14 >= var6) {
						var12 = var38 + 23;
						var13 = var13 + var3 + var7;
						var14 = 0;
						++var16;
					}
				}

				if (var15) {
					var17 = var1.stringWidth(World.World_worlds[Login.hoveredWorldIndex].activity) + 6;
					int var18 = var1.ascent + 8;
					int var39 = MouseHandler.MouseHandler_y + 25;
					if (var39 + var18 > 480) {
						var39 = MouseHandler.MouseHandler_y - 25 - var18;
					}

					Rasterizer2D.Rasterizer2D_fillRectangle(MouseHandler.MouseHandler_x - var17 / 2, var39, var17, var18, 16777120);
					Rasterizer2D.Rasterizer2D_drawRectangle(MouseHandler.MouseHandler_x - var17 / 2, var39, var17, var18, 0);
					var1.drawCentered(World.World_worlds[Login.hoveredWorldIndex].activity, MouseHandler.MouseHandler_x, var39 + var1.ascent + 4, 0, -1);
				}
			}

			FileSystem.rasterProvider.drawFull(0, 0);
		} else {
			Login.leftTitleSprite.drawAt(Login.xPadding, 0);
			Login.rightTitleSprite.drawAt(Login.xPadding + 382, 0);
			Login.logoSprite.drawAt(Login.xPadding + 382 - Login.logoSprite.subWidth / 2, 18);
			if (Client.gameState == 0 || Client.gameState == 5) {
				var3 = 20;
				var0.drawCentered("RuneScape is loading - please wait...", Login.loginBoxX + 180, 245 - var3, 16777215, -1);
				var4 = 253 - var3;
				Rasterizer2D.Rasterizer2D_drawRectangle(Login.loginBoxX + 180 - 152, var4, 304, 34, 9179409);
				Rasterizer2D.Rasterizer2D_drawRectangle(Login.loginBoxX + 180 - 151, var4 + 1, 302, 32, 0);
				Rasterizer2D.Rasterizer2D_fillRectangle(Login.loginBoxX + 180 - 150, var4 + 2, Login.Login_loadingPercent * 3, 30, 9179409);
				Rasterizer2D.Rasterizer2D_fillRectangle(Login.Login_loadingPercent * 3 + (Login.loginBoxX + 180 - 150), var4 + 2, 300 - Login.Login_loadingPercent * 3, 30, 0);
				var0.drawCentered(Login.Login_loadingText, Login.loginBoxX + 180, 276 - var3, 16777215, -1);
			}

			String var23;
			String var24;
			short var29;
			short var31;
			if (Client.gameState == 20) {
				GrandExchangeOfferNameComparator.titleboxSprite.drawAt(Login.loginBoxX + 180 - GrandExchangeOfferNameComparator.titleboxSprite.subWidth / 2, 271 - GrandExchangeOfferNameComparator.titleboxSprite.subHeight / 2);
				var29 = 201;
				var0.drawCentered(Login.Login_response1, Login.loginBoxX + 180, var29, 16776960, 0);
				var30 = var29 + 15;
				var0.drawCentered(Login.Login_response2, Login.loginBoxX + 180, var30, 16776960, 0);
				var30 += 15;
				var0.drawCentered(Login.Login_response3, Login.loginBoxX + 180, var30, 16776960, 0);
				var30 += 15;
				var30 += 7;
				if (Login.loginIndex != 4) {
					var0.draw("Login: ", Login.loginBoxX + 180 - 110, var30, 16777215, 0);
					var31 = 200;

					for (var23 = WorldMapID.method645(); var0.stringWidth(var23) > var31; var23 = var23.substring(0, var23.length() - 1)) {
					}

					var0.draw(AbstractFont.escapeBrackets(var23), Login.loginBoxX + 180 - 70, var30, 16777215, 0);
					var30 += 15;

					for (var24 = HitSplatDefinition.method4768(Login.Login_password); var0.stringWidth(var24) > var31; var24 = var24.substring(1)) {
					}

					var0.draw("Password: " + var24, Login.loginBoxX + 180 - 108, var30, 16777215, 0);
					var30 += 15;
				}
			}

			if (Client.gameState == 10 || Client.gameState == 11) {
				GrandExchangeOfferNameComparator.titleboxSprite.drawAt(Login.loginBoxX, 171);
				short var5;
				if (Login.loginIndex == 0) {
					var29 = 251;
					var0.drawCentered("Welcome to RuneScape", Login.loginBoxX + 180, var29, 16776960, 0);
					var30 = var29 + 30;
					var4 = Login.loginBoxX + 180 - 80;
					var5 = 291;
					InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
					var0.drawLines("New User", var4 - 73, var5 - 20, 144, 40, 16777215, 0, 1, 1, 0);
					var4 = Login.loginBoxX + 180 + 80;
					InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
					var0.drawLines("Existing User", var4 - 73, var5 - 20, 144, 40, 16777215, 0, 1, 1, 0);
				} else if (Login.loginIndex == 1) {
					var0.drawCentered(Login.Login_response0, Login.loginBoxX + 180, 201, 16776960, 0);
					var29 = 236;
					var0.drawCentered(Login.Login_response1, Login.loginBoxX + 180, var29, 16777215, 0);
					var30 = var29 + 15;
					var0.drawCentered(Login.Login_response2, Login.loginBoxX + 180, var30, 16777215, 0);
					var30 += 15;
					var0.drawCentered(Login.Login_response3, Login.loginBoxX + 180, var30, 16777215, 0);
					var30 += 15;
					var4 = Login.loginBoxX + 180 - 80;
					var5 = 321;
					InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
					var0.drawCentered("Continue", var4, var5 + 5, 16777215, 0);
					var4 = Login.loginBoxX + 180 + 80;
					InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
					var0.drawCentered("Cancel", var4, var5 + 5, 16777215, 0);
				} else if (Login.loginIndex == 2) {
					var29 = 201;
					var0.drawCentered(Login.Login_response1, GraphicsObject.loginBoxCenter, var29, 16776960, 0);
					var30 = var29 + 15;
					var0.drawCentered(Login.Login_response2, GraphicsObject.loginBoxCenter, var30, 16776960, 0);
					var30 += 15;
					var0.drawCentered(Login.Login_response3, GraphicsObject.loginBoxCenter, var30, 16776960, 0);
					var30 += 15;
					var30 += 7;
					var0.draw("Login: ", GraphicsObject.loginBoxCenter - 110, var30, 16777215, 0);
					var31 = 200;

					for (var23 = WorldMapID.method645(); var0.stringWidth(var23) > var31; var23 = var23.substring(1)) {
					}

					var0.draw(AbstractFont.escapeBrackets(var23) + (Login.currentLoginField == 0 & Client.cycle % 40 < 20 ? FileSystem.colorStartTag(16776960) + "|" : ""), GraphicsObject.loginBoxCenter - 70, var30, 16777215, 0);
					var30 += 15;

					for (var24 = HitSplatDefinition.method4768(Login.Login_password); var0.stringWidth(var24) > var31; var24 = var24.substring(1)) {
					}

					var0.draw("Password: " + var24 + (Login.currentLoginField == 1 & Client.cycle % 40 < 20 ? FileSystem.colorStartTag(16776960) + "|" : ""), GraphicsObject.loginBoxCenter - 108, var30, 16777215, 0);
					var30 += 15;
					var29 = 277;
					var7 = GraphicsObject.loginBoxCenter + -117;
					IndexedSprite var27 = UrlRequest.method3476(Client.Login_isUsernameRemembered, Login.field1222);
					var27.drawAt(var7, var29);
					var7 = var7 + var27.subWidth + 5;
					var1.draw("Remember username", var7, var29 + 13, 16776960, 0);
					var7 = GraphicsObject.loginBoxCenter + 24;
					var27 = UrlRequest.method3476(WorldMapSectionType.clientPreferences.hideUsername, Login.field1216);
					var27.drawAt(var7, var29);
					var7 = var7 + var27.subWidth + 5;
					var1.draw("Hide username", var7, var29 + 13, 16776960, 0);
					var30 = var29 + 15;
					var9 = GraphicsObject.loginBoxCenter - 80;
					short var10 = 321;
					InterfaceParent.titlebuttonSprite.drawAt(var9 - 73, var10 - 20);
					var0.drawCentered("Login", var9, var10 + 5, 16777215, 0);
					var9 = GraphicsObject.loginBoxCenter + 80;
					InterfaceParent.titlebuttonSprite.drawAt(var9 - 73, var10 - 20);
					var0.drawCentered("Cancel", var9, var10 + 5, 16777215, 0);
					var29 = 357;
					switch(Login.field1207) {
					case 2:
						DevicePcmPlayerProvider.field433 = "Having trouble logging in?";
						break;
					default:
						DevicePcmPlayerProvider.field433 = "Can't login? Click here.";
					}

					AttackOption.field1190 = new Bounds(GraphicsObject.loginBoxCenter, var29, var1.stringWidth(DevicePcmPlayerProvider.field433), 11);
					KeyHandler.field409 = new Bounds(GraphicsObject.loginBoxCenter, var29, var1.stringWidth("Still having trouble logging in?"), 11);
					var1.drawCentered(DevicePcmPlayerProvider.field433, GraphicsObject.loginBoxCenter, var29, 16777215, 0);
				} else if (Login.loginIndex == 3) {
					var29 = 201;
					var0.drawCentered("Invalid credentials.", Login.loginBoxX + 180, var29, 16776960, 0);
					var30 = var29 + 20;
					var1.drawCentered("For accounts created after 24th November 2010, please use your", Login.loginBoxX + 180, var30, 16776960, 0);
					var30 += 15;
					var1.drawCentered("email address to login. Otherwise please login with your username.", Login.loginBoxX + 180, var30, 16776960, 0);
					var30 += 15;
					var4 = Login.loginBoxX + 180;
					var5 = 276;
					InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
					var2.drawCentered("Try again", var4, var5 + 5, 16777215, 0);
					var4 = Login.loginBoxX + 180;
					var5 = 326;
					InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
					var2.drawCentered("Forgotten password?", var4, var5 + 5, 16777215, 0);
				} else {
					short var8;
					if (Login.loginIndex == 4) {
						var0.drawCentered("Authenticator", Login.loginBoxX + 180, 201, 16776960, 0);
						var29 = 236;
						var0.drawCentered(Login.Login_response1, Login.loginBoxX + 180, var29, 16777215, 0);
						var30 = var29 + 15;
						var0.drawCentered(Login.Login_response2, Login.loginBoxX + 180, var30, 16777215, 0);
						var30 += 15;
						var0.drawCentered(Login.Login_response3, Login.loginBoxX + 180, var30, 16777215, 0);
						var30 += 15;
						var0.draw("PIN: " + HitSplatDefinition.method4768(class1.otp) + (Client.cycle % 40 < 20 ? FileSystem.colorStartTag(16776960) + "|" : ""), Login.loginBoxX + 180 - 108, var30, 16777215, 0);
						var30 -= 8;
						var0.draw("Trust this computer", Login.loginBoxX + 180 - 9, var30, 16776960, 0);
						var30 += 15;
						var0.draw("for 30 days: ", Login.loginBoxX + 180 - 9, var30, 16776960, 0);
						var4 = Login.loginBoxX + 180 - 9 + var0.stringWidth("for 30 days: ") + 15;
						var32 = var30 - var0.ascent;
						IndexedSprite var40;
						if (Login.field1217) {
							var40 = Login.options_buttons_2Sprite;
						} else {
							var40 = class69.options_buttons_0Sprite;
						}

						var40.drawAt(var4, var32);
						var30 += 15;
						var7 = Login.loginBoxX + 180 - 80;
						var8 = 321;
						InterfaceParent.titlebuttonSprite.drawAt(var7 - 73, var8 - 20);
						var0.drawCentered("Continue", var7, var8 + 5, 16777215, 0);
						var7 = Login.loginBoxX + 180 + 80;
						InterfaceParent.titlebuttonSprite.drawAt(var7 - 73, var8 - 20);
						var0.drawCentered("Cancel", var7, var8 + 5, 16777215, 0);
						var1.drawCentered("<u=ff>Can't Log In?</u>", Login.loginBoxX + 180, var8 + 36, 255, 0);
					} else if (Login.loginIndex == 5) {
						var0.drawCentered("Forgotten your password?", Login.loginBoxX + 180, 201, 16776960, 0);
						var29 = 221;
						var2.drawCentered(Login.Login_response1, Login.loginBoxX + 180, var29, 16776960, 0);
						var30 = var29 + 15;
						var2.drawCentered(Login.Login_response2, Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var2.drawCentered(Login.Login_response3, Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var30 += 14;
						var0.draw("Username/email: ", Login.loginBoxX + 180 - 145, var30, 16777215, 0);
						var31 = 174;

						for (var23 = WorldMapID.method645(); var0.stringWidth(var23) > var31; var23 = var23.substring(1)) {
						}

						var0.draw(AbstractFont.escapeBrackets(var23) + (Client.cycle % 40 < 20 ? FileSystem.colorStartTag(16776960) + "|" : ""), Login.loginBoxX + 180 - 34, var30, 16777215, 0);
						var30 += 15;
						var6 = Login.loginBoxX + 180 - 80;
						short var36 = 321;
						InterfaceParent.titlebuttonSprite.drawAt(var6 - 73, var36 - 20);
						var0.drawCentered("Recover", var6, var36 + 5, 16777215, 0);
						var6 = Login.loginBoxX + 180 + 80;
						InterfaceParent.titlebuttonSprite.drawAt(var6 - 73, var36 - 20);
						var0.drawCentered("Back", var6, var36 + 5, 16777215, 0);
						var36 = 356;
						var1.drawCentered("Still having trouble logging in?", GraphicsObject.loginBoxCenter, var36, 268435455, 0);
					} else if (Login.loginIndex == 6) {
						var29 = 201;
						var0.drawCentered(Login.Login_response1, Login.loginBoxX + 180, var29, 16776960, 0);
						var30 = var29 + 15;
						var0.drawCentered(Login.Login_response2, Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var0.drawCentered(Login.Login_response3, Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var4 = Login.loginBoxX + 180;
						var5 = 321;
						InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
						var0.drawCentered("Back", var4, var5 + 5, 16777215, 0);
					} else if (Login.loginIndex == 7) {
						var29 = 216;
						var0.drawCentered("Your date of birth isn't set.", Login.loginBoxX + 180, var29, 16776960, 0);
						var30 = var29 + 15;
						var2.drawCentered("Please verify your account status by", Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var2.drawCentered("setting your date of birth.", Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var4 = Login.loginBoxX + 180 - 80;
						var5 = 321;
						InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
						var0.drawCentered("Set Date of Birth", var4, var5 + 5, 16777215, 0);
						var4 = Login.loginBoxX + 180 + 80;
						InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
						var0.drawCentered("Back", var4, var5 + 5, 16777215, 0);
					} else if (Login.loginIndex == 8) {
						var29 = 216;
						var0.drawCentered("Sorry, but your account is not eligible to play.", Login.loginBoxX + 180, var29, 16776960, 0);
						var30 = var29 + 15;
						var2.drawCentered("For more information, please take a look at", Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var2.drawCentered("our privacy policy.", Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var4 = Login.loginBoxX + 180 - 80;
						var5 = 321;
						InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
						var0.drawCentered("Privacy Policy", var4, var5 + 5, 16777215, 0);
						var4 = Login.loginBoxX + 180 + 80;
						InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
						var0.drawCentered("Back", var4, var5 + 5, 16777215, 0);
					} else if (Login.loginIndex == 12) {
						var29 = 201;
						String var28 = "";
						var23 = "";
						var24 = "";
						switch(Login.field1206) {
						case 0:
							var28 = "Your account has been disabled.";
							var23 = Strings.field2841;
							var24 = "";
							break;
						case 1:
							var28 = "Account locked as we suspect it has been stolen.";
							var23 = Strings.field2918;
							var24 = "";
							break;
						default:
							GrandExchangeOfferOwnWorldComparator.Login_promptCredentials(false);
						}

						var0.drawCentered(var28, Login.loginBoxX + 180, var29, 16776960, 0);
						var30 = var29 + 15;
						var2.drawCentered(var23, Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var2.drawCentered(var24, Login.loginBoxX + 180, var30, 16776960, 0);
						var30 += 15;
						var7 = Login.loginBoxX + 180;
						var8 = 276;
						InterfaceParent.titlebuttonSprite.drawAt(var7 - 73, var8 - 20);
						var0.drawCentered("Support Page", var7, var8 + 5, 16777215, 0);
						var7 = Login.loginBoxX + 180;
						var8 = 326;
						InterfaceParent.titlebuttonSprite.drawAt(var7 - 73, var8 - 20);
						var0.drawCentered("Back", var7, var8 + 5, 16777215, 0);
					} else if (Login.loginIndex == 24) {
						var29 = 221;
						var0.drawCentered(Login.Login_response1, Login.loginBoxX + 180, var29, 16777215, 0);
						var30 = var29 + 15;
						var0.drawCentered(Login.Login_response2, Login.loginBoxX + 180, var30, 16777215, 0);
						var30 += 15;
						var0.drawCentered(Login.Login_response3, Login.loginBoxX + 180, var30, 16777215, 0);
						var30 += 15;
						var4 = Login.loginBoxX + 180;
						var5 = 301;
						InterfaceParent.titlebuttonSprite.drawAt(var4 - 73, var5 - 20);
						var0.drawCentered("Ok", var4, var5 + 5, 16777215, 0);
					}
				}
			}

			if (Client.gameState >= 10) {
				int[] var25 = new int[4];
				Rasterizer2D.Rasterizer2D_getClipArray(var25);
				Rasterizer2D.Rasterizer2D_setClip(Login.xPadding, 0, Login.xPadding + 765, class25.canvasHeight);
				class9.loginScreenRunesAnimation.draw(Login.xPadding - 22, Client.cycle);
				class9.loginScreenRunesAnimation.draw(Login.xPadding + 22 + 765 - 128, Client.cycle);
				Rasterizer2D.Rasterizer2D_setClipArray(var25);
			}

			Login.title_muteSprite[WorldMapSectionType.clientPreferences.titleMusicDisabled ? 1 : 0].drawAt(Login.xPadding + 765 - 40, 463);
			if (Client.gameState > 5 && Language.Language_EN == NPCComposition.clientLanguage) {
				if (World.field1039 != null) {
					var30 = Login.xPadding + 5;
					var31 = 463;
					byte var34 = 100;
					byte var33 = 35;
					World.field1039.drawAt(var30, var31);
					var0.drawCentered("World" + " " + Client.worldId, var34 / 2 + var30, var33 / 2 + var31 - 2, 16777215, 0);
					if (UserComparator9.World_request != null) {
						var1.drawCentered("Loading...", var34 / 2 + var30, var33 / 2 + var31 + 12, 16777215, 0);
					} else {
						var1.drawCentered("Click to switch", var34 / 2 + var30, var33 / 2 + var31 + 12, 16777215, 0);
					}
				} else {
					World.field1039 = ChatChannel.SpriteBuffer_getIndexedSpriteByName(ModeWhere.archive8, "sl_button", "");
				}
			}

		}
	}
}
