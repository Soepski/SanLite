import java.util.Comparator;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("pk")
@Implements("UserComparator2")
public class UserComparator2 implements Comparator {
	@ObfuscatedName("c")
	@Export("reversed")
	final boolean reversed;

	public UserComparator2(boolean var1) {
		this.reversed = var1;
	}

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "(Lmy;Lmy;I)I",
		garbageValue = "2031661860"
	)
	@Export("compare_bridged")
	int compare_bridged(Nameable var1, Nameable var2) {
		return this.reversed ? var1.getUsername().compareToTyped(var2.getUsername()) : var2.getUsername().compareToTyped(var1.getUsername());
	}

	public int compare(Object var1, Object var2) {
		return this.compare_bridged((Nameable)var1, (Nameable)var2);
	}

	public boolean equals(Object var1) {
		return super.equals(var1);
	}
}
