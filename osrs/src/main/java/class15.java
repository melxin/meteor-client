import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;
import org.bouncycastle.crypto.tls.TlsClientProtocol;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.Security;

@ObfuscatedName("ab")
public class class15 extends SSLSocketFactory {
	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "Lab;"
	)
	static class15 field46;
	@ObfuscatedName("au")
	SecureRandom field47;

	static {
		if (Security.getProvider("BC") == null) {
			Security.addProvider(new BouncyCastleProvider());
		}

	}

	class15() {
		this.field47 = new SecureRandom();
	}

	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;Lorg/bouncycastle/crypto/tls/TlsClientProtocol;B)Ljavax/net/ssl/SSLSocket;",
		garbageValue = "59"
	)
	SSLSocket method46(String var1, TlsClientProtocol var2) {
		return new class12(this, var2, var1);
	}

	@Export("createSocket")
	@ObfuscatedName("createSocket")
	public Socket createSocket(Socket var1, String var2, int var3, boolean var4) throws IOException {
		if (var1 == null) {
			var1 = new Socket();
		}

		if (!var1.isConnected()) {
			var1.connect(new InetSocketAddress(var2, var3));
		}

		TlsClientProtocol var5 = new TlsClientProtocol(var1.getInputStream(), var1.getOutputStream(), this.field47);
		return this.method46(var2, var5);
	}

	public String[] getDefaultCipherSuites() {
		return null;
	}

	public String[] getSupportedCipherSuites() {
		return null;
	}

	public Socket createSocket(String var1, int var2) throws IOException, UnknownHostException {
		return null;
	}

	public Socket createSocket(InetAddress var1, int var2) throws IOException {
		return null;
	}

	public Socket createSocket(String var1, int var2, InetAddress var3, int var4) throws IOException, UnknownHostException {
		return null;
	}

	public Socket createSocket(InetAddress var1, int var2, InetAddress var3, int var4) throws IOException {
		return null;
	}

	@ObfuscatedName("au")
	@ObfuscatedSignature(
		descriptor = "(I)Lab;",
		garbageValue = "1970056082"
	)
	public static class15 method45() {
		if (field46 == null) {
			field46 = new class15();
		}

		return field46;
	}
}