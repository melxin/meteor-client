import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ho")
@Implements("ObjTypeCustomisation")
public class ObjTypeCustomisation {
	@ObfuscatedName("ef")
	@Export("mouseCam")
	static boolean mouseCam;
	@ObfuscatedName("jw")
	@Export("regionLandArchiveIds")
	static int[] regionLandArchiveIds;
	@ObfuscatedName("ao")
	@Export("recol")
	public short[] recol;
	@ObfuscatedName("at")
	@Export("retex")
	public short[] retex;

	public ObjTypeCustomisation(int var1) {
		ItemComposition var2 = InvDefinition.ItemDefinition_get(var1);
		if (var2.hasRecolor()) {
			this.recol = new short[var2.recolorTo.length];
			System.arraycopy(var2.recolorTo, 0, this.recol, 0, this.recol.length);
		}

		if (var2.hasRetexture()) {
			this.retex = new short[var2.retextureTo.length];
			System.arraycopy(var2.retextureTo, 0, this.retex, 0, this.retex.length);
		}

	}

	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "(II)Lhx;",
		garbageValue = "1562515394"
	)
	@Export("getParamDefinition")
	public static ParamComposition getParamDefinition(int var0) {
		ParamComposition var1 = (ParamComposition)ParamComposition.ParamDefinition_cached.get((long)var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = ParamComposition.ParamDefinition_archive.takeFile(11, var0);
			var1 = new ParamComposition();
			if (var2 != null) {
				var1.decode(new Buffer(var2));
			}

			var1.postDecode();
			ParamComposition.ParamDefinition_cached.put(var1, (long)var0);
			return var1;
		}
	}

	@ObfuscatedName("bz")
	@ObfuscatedSignature(
		descriptor = "(ILdh;ZI)I",
		garbageValue = "-1153015817"
	)
	static int method935(int var0, Script var1, boolean var2) {
		if (var0 == 6809) {
			int var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
			ObjectComposition var4 = WorldMapElement.getObjectDefinition(var3);
			Interpreter.Interpreter_stringStack[++class137.Interpreter_stringStackSize - 1] = var4 != null ? var4.name : "";
			return 1;
		} else {
			return 2;
		}
	}

	@ObfuscatedName("ot")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;I)Ljava/lang/String;",
		garbageValue = "-1860309613"
	)
	static String method936(String var0) {
		PlayerType[] var1 = MidiPcmStream.PlayerType_values();

		for (int var2 = 0; var2 < var1.length; ++var2) {
			PlayerType var3 = var1[var2];
			if (var3.modIcon != -1 && var0.startsWith(Tile.addImageTag(var3.modIcon))) {
				var0 = var0.substring(6 + Integer.toString(var3.modIcon).length());
				break;
			}
		}

		return var0;
	}
}