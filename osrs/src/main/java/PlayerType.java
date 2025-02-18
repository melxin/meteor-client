import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("nn")
@Implements("PlayerType")
public enum PlayerType implements class371 {
	@ObfuscatedName("au")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	@Export("PlayerType_normal")
	PlayerType_normal(0, -1, true, false, true),
	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	@Export("PlayerType_playerModerator")
	PlayerType_playerModerator(1, 0, true, true, true),
	@ObfuscatedName("ao")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	@Export("PlayerType_jagexModerator")
	PlayerType_jagexModerator(2, 1, true, true, false),
	@ObfuscatedName("at")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	@Export("PlayerType_ironman")
	PlayerType_ironman(3, 2, false, false, true),
	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	@Export("PlayerType_ultimateIronman")
	PlayerType_ultimateIronman(4, 3, false, false, true),
	@ObfuscatedName("ai")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	@Export("PlayerType_hardcoreIronman")
	PlayerType_hardcoreIronman(5, 10, false, false, true),
	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3500(6, 22, false, false, true),
	@ObfuscatedName("ap")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3495(7, 41, false, false, true),
	@ObfuscatedName("aa")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3484(8, 42, false, false, true),
	@ObfuscatedName("af")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3489(9, 43, false, false, true),
	@ObfuscatedName("ad")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3487(10, 44, false, false, true),
	@ObfuscatedName("aq")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3496(11, 45, false, false, true),
	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3492(12, 46, false, false, true),
	@ObfuscatedName("an")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3493(13, 47, false, false, true),
	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3497(14, 48, false, false, true),
	@ObfuscatedName("ab")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3485(15, 49, false, false, true),
	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "Lnn;"
	)
	field3490(16, 52, false, false, true);

	@ObfuscatedName("am")
	@Export("id")
	final int id;
	@ObfuscatedName("ax")
	@Export("modIcon")
	public final int modIcon;
	@ObfuscatedName("ah")
	@Export("isPrivileged")
	public final boolean isPrivileged;
	@ObfuscatedName("as")
	@Export("isUser")
	public final boolean isUser;

	PlayerType(int var3, int var4, boolean var5, boolean var6, boolean var7) {
		this.id = var3;
		this.modIcon = var4;
		this.isPrivileged = var6;
		this.isUser = var7;
	}

	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "(B)I",
		garbageValue = "41"
	)
	@Export("rsOrdinal")
	public int rsOrdinal() {
		return this.id;
	}

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "([BI)V",
		garbageValue = "414749289"
	)
	@Export("ByteArrayPool_release")
	public static synchronized void ByteArrayPool_release(byte[] var0) {
		if (var0.length == 100 && ByteArrayPool.ByteArrayPool_smallCount < ByteArrayPool.field3738) {
			ByteArrayPool.ByteArrayPool_small[++ByteArrayPool.ByteArrayPool_smallCount - 1] = var0;
		} else if (var0.length == 5000 && ByteArrayPool.ByteArrayPool_mediumCount < ByteArrayPool.field3741) {
			ByteArrayPool.ByteArrayPool_medium[++ByteArrayPool.ByteArrayPool_mediumCount - 1] = var0;
		} else if (var0.length == 10000 && ByteArrayPool.ByteArrayPool_largeCount < ByteArrayPool.field3740) {
			ByteArrayPool.ByteArrayPool_large[++ByteArrayPool.ByteArrayPool_largeCount - 1] = var0;
		} else if (var0.length == 30000 && ByteArrayPool.field3743 < ByteArrayPool.field3744) {
			ByteArrayPool.field3734[++ByteArrayPool.field3743 - 1] = var0;
		} else {
			if (class410.ByteArrayPool_arrays != null) {
				for (int var1 = 0; var1 < class416.ByteArrayPool_alternativeSizes.length; ++var1) {
					if (var0.length == class416.ByteArrayPool_alternativeSizes[var1] && StructComposition.ByteArrayPool_altSizeArrayCounts[var1] < class410.ByteArrayPool_arrays[var1].length) {
						class410.ByteArrayPool_arrays[var1][StructComposition.ByteArrayPool_altSizeArrayCounts[var1]++] = var0;
						return;
					}
				}
			}

		}
	}

	@ObfuscatedName("nq")
	@ObfuscatedSignature(
		descriptor = "(B)I",
		garbageValue = "-72"
	)
	static final int method1883() {
		float var0 = 200.0F * ((float)class449.clientPreferences.getBrightness() - 0.5F);
		return 100 - Math.round(var0);
	}
}