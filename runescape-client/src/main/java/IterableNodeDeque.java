import java.util.Collection;
import java.util.Iterator;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("lt")
@Implements("IterableNodeDeque")
public class IterableNodeDeque implements Iterable, Collection {
	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "Loe;"
	)
	@Export("sentinel")
	Node sentinel;
	@ObfuscatedName("l")
	@ObfuscatedSignature(
		descriptor = "Loe;"
	)
	Node field4014;

	public IterableNodeDeque() {
		this.sentinel = new Node();
		this.sentinel.previous = this.sentinel;
		this.sentinel.next = this.sentinel;
	}

	@ObfuscatedName("c")
	@Export("rsClear")
	public void rsClear() {
		while (this.sentinel.previous != this.sentinel) {
			this.sentinel.previous.remove();
		}

	}

	@ObfuscatedName("l")
	@ObfuscatedSignature(
		descriptor = "(Loe;)V"
	)
	@Export("addFirst")
	public void addFirst(Node var1) {
		if (var1.next != null) {
			var1.remove();
		}

		var1.next = this.sentinel.next;
		var1.previous = this.sentinel;
		var1.next.previous = var1;
		var1.previous.next = var1;
	}

	@ObfuscatedName("s")
	@ObfuscatedSignature(
		descriptor = "(Loe;)V"
	)
	@Export("addLast")
	public void addLast(Node var1) {
		if (var1.next != null) {
			var1.remove();
		}

		var1.next = this.sentinel;
		var1.previous = this.sentinel.previous;
		var1.next.previous = var1;
		var1.previous.next = var1;
	}

	@ObfuscatedName("r")
	@ObfuscatedSignature(
		descriptor = "()Loe;"
	)
	@Export("last")
	public Node last() {
		return this.method5879((Node)null);
	}

	@ObfuscatedName("o")
	@ObfuscatedSignature(
		descriptor = "(Loe;)Loe;"
	)
	Node method5879(Node var1) {
		Node var2;
		if (var1 == null) {
			var2 = this.sentinel.previous;
		} else {
			var2 = var1;
		}

		if (var2 == this.sentinel) {
			this.field4014 = null;
			return null;
		} else {
			this.field4014 = var2.previous;
			return var2;
		}
	}

	@ObfuscatedName("i")
	@ObfuscatedSignature(
		descriptor = "()Loe;"
	)
	@Export("previous")
	public Node previous() {
		Node var1 = this.field4014;
		if (var1 == this.sentinel) {
			this.field4014 = null;
			return null;
		} else {
			this.field4014 = var1.previous;
			return var1;
		}
	}

	@ObfuscatedName("w")
	int method5881() {
		int var1 = 0;

		for (Node var2 = this.sentinel.previous; var2 != this.sentinel; var2 = var2.previous) {
			++var1;
		}

		return var1;
	}

	@ObfuscatedName("v")
	public boolean method5882() {
		return this.sentinel.previous == this.sentinel;
	}

	@ObfuscatedName("a")
	@ObfuscatedSignature(
		descriptor = "()[Loe;"
	)
	Node[] method5948() {
		Node[] var1 = new Node[this.method5881()];
		int var2 = 0;

		for (Node var3 = this.sentinel.previous; var3 != this.sentinel; var3 = var3.previous) {
			var1[var2++] = var3;
		}

		return var1;
	}

	@ObfuscatedName("y")
	@ObfuscatedSignature(
		descriptor = "(Loe;)Z"
	)
	boolean method5885(Node var1) {
		this.addFirst(var1);
		return true;
	}

	public int hashCode() {
		return super.hashCode();
	}

	public boolean isEmpty() {
		return this.method5882();
	}

	public boolean contains(Object var1) {
		throw new RuntimeException();
	}

	public Object[] toArray() {
		return this.method5948();
	}

	public Object[] toArray(Object[] var1) {
		int var2 = 0;

		for (Node var3 = this.sentinel.previous; var3 != this.sentinel; var3 = var3.previous) {
			var1[var2++] = var3;
		}

		return var1;
	}

	public boolean remove(Object var1) {
		throw new RuntimeException();
	}

	public boolean containsAll(Collection var1) {
		throw new RuntimeException();
	}

	public boolean removeAll(Collection var1) {
		throw new RuntimeException();
	}

	public void clear() {
		this.rsClear();
	}

	public boolean add(Object var1) {
		return this.method5885((Node)var1);
	}

	public boolean equals(Object var1) {
		return super.equals(var1);
	}

	public boolean retainAll(Collection var1) {
		throw new RuntimeException();
	}

	public Iterator iterator() {
		return new IterableNodeDequeDescendingIterator(this);
	}

	public boolean addAll(Collection var1) {
		throw new RuntimeException();
	}

	public int size() {
		return this.method5881();
	}

	@ObfuscatedName("e")
	@ObfuscatedSignature(
		descriptor = "(Loe;Loe;)V"
	)
	@Export("IterableNodeDeque_addBefore")
	public static void IterableNodeDeque_addBefore(Node var0, Node var1) {
		if (var0.next != null) {
			var0.remove();
		}

		var0.next = var1;
		var0.previous = var1.previous;
		var0.next.previous = var0;
		var0.previous.next = var0;
	}
}
