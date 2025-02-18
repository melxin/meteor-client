import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;

@ObfuscatedName("ev")
@Implements("UrlRequester")
public abstract class UrlRequester implements Runnable {
	@ObfuscatedName("je")
	@Export("baseX")
	static int baseX;
	@ObfuscatedName("au")
	final Thread field1169;
	@ObfuscatedName("ae")
	@Export("isClosed")
	volatile boolean isClosed;
	@ObfuscatedName("ao")
	@Export("requests")
	Queue requests;
	@ObfuscatedName("at")
	int field1167;

	UrlRequester(int var1) {
		this.requests = new LinkedList();
		this.field1169 = new Thread(this);
		this.field1169.setPriority(1);
		this.field1169.start();
		this.field1167 = var1;
	}

	@ObfuscatedName("au")
	@ObfuscatedSignature(
		descriptor = "(Let;B)V",
		garbageValue = "-114"
	)
	@Export("vmethod2700")
	abstract void vmethod2700(UrlRequest var1) throws IOException;

	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "(Ljava/net/URLConnection;B)I",
		garbageValue = "20"
	)
	int method647(URLConnection var1) {
		int var2 = UrlRequest.field1176;
		if (var1 != null) {
			try {
				if (var1 instanceof HttpURLConnection) {
					var2 = ((HttpURLConnection)var1).getResponseCode();
				}
			} catch (IOException var4) {
			}
		}

		return var2;
	}

	@ObfuscatedName("ao")
	@ObfuscatedSignature(
		descriptor = "(Ljava/net/URLConnection;B)V",
		garbageValue = "61"
	)
	void method648(URLConnection var1) {
		var1.setConnectTimeout(5000);
		var1.setReadTimeout(5000);
		var1.setUseCaches(false);
		var1.setRequestProperty("Connection", "close");
		var1.setRequestProperty("User-Agent", "OldSchoolRuneScape/" + this.field1167);
	}

	@ObfuscatedName("at")
	@ObfuscatedSignature(
		descriptor = "(Ljava/net/URLConnection;Let;I)V",
		garbageValue = "-703916602"
	)
	void method649(URLConnection var1, UrlRequest var2) {
		DataInputStream var3 = null;

		try {
			int var5 = var1.getContentLength();
			var3 = new DataInputStream(var1.getInputStream());
			byte[] var4;
			if (var5 >= 0) {
				var4 = new byte[var5];
				var3.readFully(var4);
			} else {
				var4 = new byte[0];
				byte[] var6 = class125.ByteArrayPool_getArray(5000);

				byte[] var8;
				for (int var7 = var3.read(var6, 0, var6.length); var7 > -1; var4 = var8) {
					var8 = new byte[var4.length + var7];
					System.arraycopy(var4, 0, var8, 0, var4.length);
					System.arraycopy(var6, 0, var8, var4.length, var7);
				}

				PlayerType.ByteArrayPool_release(var6);
			}

			var2.response0 = var4;
		} catch (IOException var14) {
			var2.response0 = null;
		} finally {
			var2.field1179 = this.method647(var1);
		}

		if (var3 != null) {
			try {
				var3.close();
			} catch (IOException var13) {
			}
		}

	}

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "(Ljava/net/URL;B)Let;",
		garbageValue = "95"
	)
	@Export("request")
	public UrlRequest request(URL var1) {
		UrlRequest var2 = new UrlRequest(var1);
		synchronized(this) {
			this.requests.add(var2);
			this.notify();
			return var2;
		}
	}

	@ObfuscatedName("ai")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "-710296298"
	)
	@Export("close")
	public void close() {
		this.isClosed = true;

		try {
			synchronized(this) {
				this.notify();
			}

			this.field1169.join();
		} catch (InterruptedException var4) {
		}

	}

	@Export("run")
	@ObfuscatedName("run")
	public void run() {
		while (!this.isClosed) {
			try {
				UrlRequest var1;
				synchronized(this) {
					var1 = (UrlRequest)this.requests.poll();
					if (var1 == null) {
						try {
							this.wait();
						} catch (InterruptedException var5) {
						}
						continue;
					}
				}

				this.vmethod2700(var1);
			} catch (Exception var7) {
				Messages.RunException_sendStackTrace((String)null, var7);
			}
		}

	}

	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "2153026"
	)
	public static void method652() {
		class305.field2773.clear();
	}

	@ObfuscatedName("ax")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "-85"
	)
	public static void method653() {
		ItemComposition.ItemDefinition_cachedSprites.clear();
	}

	@ObfuscatedName("hb")
	@ObfuscatedSignature(
		descriptor = "(I)J",
		garbageValue = "286684178"
	)
	static long method654() {
		return Client.playerUUID;
	}

	@ObfuscatedName("jb")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "1352739934"
	)
	static boolean method655() {
		return (Client.drawPlayerNames & 2) != 0;
	}

	@ObfuscatedName("nj")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;I)V",
		garbageValue = "1224778546"
	)
	@Export("Clan_joinChat")
	static final void Clan_joinChat(String var0) {
		if (!var0.equals("")) {
			PacketBufferNode var1 = ApproximateRouteStrategy.getPacketBufferNode(ClientPacket.CLAN_JOINCHAT_LEAVECHAT, Client.packetWriter.isaacCipher);
			var1.packetBuffer.writeByte(AbstractSocket.stringCp1252NullTerminatedByteSize(var0));
			var1.packetBuffer.writeStringCp1252NullTerminated(var0);
			Client.packetWriter.addNode(var1);
		}
	}
}