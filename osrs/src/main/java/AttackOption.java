import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("dc")
@Implements("AttackOption")
public enum AttackOption implements class371 {
	@ObfuscatedName("au")
	@ObfuscatedSignature(
		descriptor = "Ldc;"
	)
	@Export("AttackOption_dependsOnCombatLevels")
	AttackOption_dependsOnCombatLevels(0),
	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "Ldc;"
	)
	@Export("AttackOption_alwaysRightClick")
	AttackOption_alwaysRightClick(1),
	@ObfuscatedName("ao")
	@ObfuscatedSignature(
		descriptor = "Ldc;"
	)
	field1099(2),
	@ObfuscatedName("at")
	@ObfuscatedSignature(
		descriptor = "Ldc;"
	)
	@Export("AttackOption_hidden")
	AttackOption_hidden(3),
	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "Ldc;"
	)
	field1097(4);

	@ObfuscatedName("bl")
	@ObfuscatedSignature(
		descriptor = "Luq;"
	)
	@Export("rasterProvider")
	public static AbstractRasterProvider rasterProvider;
	@ObfuscatedName("fr")
	@ObfuscatedSignature(
		descriptor = "Lnk;"
	)
	static Archive field1096;
	@ObfuscatedName("ai")
	@Export("id")
	final int id;

	AttackOption(int var3) {
		this.id = var3;
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

	@ObfuscatedName("bf")
	@ObfuscatedSignature(
		descriptor = "(ILdh;ZB)I",
		garbageValue = "-76"
	)
	static int method607(int var0, Script var1, boolean var2) {
		int var3;
		if (var0 == 4200) {
			var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
			Interpreter.Interpreter_stringStack[++class137.Interpreter_stringStackSize - 1] = InvDefinition.ItemDefinition_get(var3).name;
			return 1;
		} else {
			int var4;
			ItemComposition var5;
			if (var0 == 4201) {
				SoundCache.Interpreter_intStackSize -= 2;
				var3 = Interpreter.Interpreter_intStack[SoundCache.Interpreter_intStackSize];
				var4 = Interpreter.Interpreter_intStack[SoundCache.Interpreter_intStackSize + 1];
				var5 = InvDefinition.ItemDefinition_get(var3);
				if (var4 >= 1 && var4 <= 5 && var5.groundActions[var4 - 1] != null) {
					Interpreter.Interpreter_stringStack[++class137.Interpreter_stringStackSize - 1] = var5.groundActions[var4 - 1];
				} else {
					Interpreter.Interpreter_stringStack[++class137.Interpreter_stringStackSize - 1] = "";
				}

				return 1;
			} else if (var0 == 4202) {
				SoundCache.Interpreter_intStackSize -= 2;
				var3 = Interpreter.Interpreter_intStack[SoundCache.Interpreter_intStackSize];
				var4 = Interpreter.Interpreter_intStack[SoundCache.Interpreter_intStackSize + 1];
				var5 = InvDefinition.ItemDefinition_get(var3);
				if (var4 >= 1 && var4 <= 5 && var5.inventoryActions[var4 - 1] != null) {
					Interpreter.Interpreter_stringStack[++class137.Interpreter_stringStackSize - 1] = var5.inventoryActions[var4 - 1];
				} else {
					Interpreter.Interpreter_stringStack[++class137.Interpreter_stringStackSize - 1] = "";
				}

				return 1;
			} else if (var0 == 4203) {
				var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
				Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = InvDefinition.ItemDefinition_get(var3).price;
				return 1;
			} else if (var0 == 4204) {
				var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
				Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = InvDefinition.ItemDefinition_get(var3).isStackable == 1 ? 1 : 0;
				return 1;
			} else {
				ItemComposition var6;
				if (var0 == 4205) {
					var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
					var6 = InvDefinition.ItemDefinition_get(var3);
					if (var6.noteTemplate == -1 && var6.note >= 0) {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var6.note;
					} else {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var3;
					}

					return 1;
				} else if (var0 == 4206) {
					var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
					var6 = InvDefinition.ItemDefinition_get(var3);
					if (var6.noteTemplate >= 0 && var6.note >= 0) {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var6.note;
					} else {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var3;
					}

					return 1;
				} else if (var0 == 4207) {
					var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
					Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = InvDefinition.ItemDefinition_get(var3).isMembersOnly ? 1 : 0;
					return 1;
				} else if (var0 == 4208) {
					var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
					var6 = InvDefinition.ItemDefinition_get(var3);
					if (var6.placeholderTemplate == -1 && var6.placeholder >= 0) {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var6.placeholder;
					} else {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var3;
					}

					return 1;
				} else if (var0 == 4209) {
					var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
					var6 = InvDefinition.ItemDefinition_get(var3);
					if (var6.placeholderTemplate >= 0 && var6.placeholder >= 0) {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var6.placeholder;
					} else {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var3;
					}

					return 1;
				} else if (var0 == 4210) {
					String var7 = Interpreter.Interpreter_stringStack[--class137.Interpreter_stringStackSize];
					var4 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
					class161.findItemDefinitions(var7, var4 == 1);
					Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = class28.foundItemIdCount;
					return 1;
				} else if (var0 != 4211) {
					if (var0 == 4212) {
						class372.foundItemIndex = 0;
						return 1;
					} else if (var0 == 4213) {
						var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
						var4 = InvDefinition.ItemDefinition_get(var3).getShiftClickIndex();
						if (var4 == -1) {
							Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var4;
						} else {
							Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var4 + 1;
						}

						return 1;
					} else if (var0 == 4214) {
						var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = InvDefinition.ItemDefinition_get(var3).maleModel;
						return 1;
					} else if (var0 == 4215) {
						var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = InvDefinition.ItemDefinition_get(var3).maleModel1;
						return 1;
					} else if (var0 == 4216) {
						var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = InvDefinition.ItemDefinition_get(var3).maleModel2;
						return 1;
					} else if (var0 == 4217) {
						var3 = Interpreter.Interpreter_intStack[--SoundCache.Interpreter_intStackSize];
						var6 = InvDefinition.ItemDefinition_get(var3);
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = var6.field1806;
						return 1;
					} else {
						return 2;
					}
				} else {
					if (class156.foundItemIds != null && class372.foundItemIndex < class28.foundItemIdCount) {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = class156.foundItemIds[++class372.foundItemIndex - 1] & '\uffff';
					} else {
						Interpreter.Interpreter_intStack[++SoundCache.Interpreter_intStackSize - 1] = -1;
					}

					return 1;
				}
			}
		}
	}
}