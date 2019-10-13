package com.vaptech.business.ftps;

import org.apache.commons.net.ftp.FTPSClient;

import javax.net.ssl.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Locale;

/**
 *
 * Class from GitHub.
 * Thanks this class, connection FTPS doesn't get error: 'Remote host closed connection during handshake'
 *
 * The Genius who did this:
 *
 * FTPSClient to patch TLS session resumption support in Apache FTPSClient
 * see also: http://eng.wealthfront.com/2016/06/10/connecting-to-an-ftps-server-with-ssl-session-reuse-in-java-7-and-8/
 * see also: https://gist.github.com/lukehansen/cd1931426d1e432d0993005f9976e6fb
 */
public class PatchedFTPSClient extends FTPSClient {

    public PatchedFTPSClient(boolean isImplicit){
        super(isImplicit);
    }

    @Override protected void _prepareDataSocket_(Socket socket) throws IOException {
        if (socket instanceof SSLSocket) {
            final SSLSession session = ((SSLSocket) _socket_).getSession();
            final SSLSessionContext context = session.getSessionContext();
            try {
                final Field sessionHostPortCache = context.getClass().getDeclaredField("sessionHostPortCache");
                sessionHostPortCache.setAccessible(true);
                final Object cache = sessionHostPortCache.get(context);
                final Method putMethod = cache.getClass().getDeclaredMethod("put", Object.class, Object.class);
                putMethod.setAccessible(true);
                final Method getHostMethod = socket.getClass().getDeclaredMethod("getHost");
                getHostMethod.setAccessible(true);
                Object host = getHostMethod.invoke(socket);
                final String key = String.format("%s:%s", host, String.valueOf(socket.getPort())).toLowerCase(Locale.ROOT);
                putMethod.invoke(cache, key, session);
            } catch (Exception e) {
                throw new IOException(e);
            }
        }
    }
}
