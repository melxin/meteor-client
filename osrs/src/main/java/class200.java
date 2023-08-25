import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("hf")
public class class200 extends DualNode {
	@ObfuscatedName("ce")
	static String field1669;

	static {
		new EvictingDualNodeHashTable(64);
	}

	@ObfuscatedName("af")
	@ObfuscatedSignature(
		descriptor = "(CI)Z",
		garbageValue = "942209246"
	)
	@Export("isCharAlphabetic")
	public static boolean isCharAlphabetic(char var0) {
		return var0 >= 'A' && var0 <= 'Z' || var0 >= 'a' && var0 <= 'z';
	}

	@ObfuscatedName("aq")
	@ObfuscatedSignature(
		descriptor = "(ZB)V",
		garbageValue = "56"
	)
	static void method1009(boolean var0) {
		if (var0) {
			ByteArrayPool.method2148();
		} else {
			for (int var1 = 0; var1 < class305.musicSongs.size(); ++var1) {
				MusicSong var2 = (MusicSong)class305.musicSongs.get(var1);
				if (var2 == null) {
					class305.musicSongs.remove(var1);
					--var1;
				} else if (var2.field2863) {
					if (var2.midiPcmStream.field2809 > 0) {
						--var2.midiPcmStream.field2809;
					}

					var2.midiPcmStream.clear();
					var2.midiPcmStream.method1633();
					var2.midiPcmStream.setPcmStreamVolume(0);
					class305.musicSongs.remove(var1);
					--var1;
				} else {
					var2.field2863 = true;
				}
			}
		}

	}
}