package mx.edu.uteq.idgs09_3.Controller;

import java.net.URL;
import java.util.List;

public class SSRFProtector {

    // Dominios/IPs permitidos
    private static final List<String> ALLOWED_DOMAINS = List.of(
        "api.midominio.com",
        "recurso.seguro.com",
        "localhost"  // <- Permitir localhost para desarrollo
    );

    private static final List<String> ALLOWED_IPS = List.of(
        "192.168.1.1",
        "10.0.0.2",
        "127.0.0.1",  // <- IPv4 loopback
        "0:0:0:0:0:0:0:1"  // <- IPv6 loopback
    );

    private static final List<Integer> ALLOWED_PORTS = List.of(80, 443, 8080, 5173); // Puertos comunes en desarrollo

    public static void validateUrl(String inputUrl) throws Exception {
        URL url = new URL(inputUrl);

        // Validar protocolo (HTTP/HTTPS, relajado para desarrollo)
        if (!url.getProtocol().equals("https") && !url.getProtocol().equals("http")) {
            throw new SecurityException("Solo se permiten URLs HTTP/HTTPS");
        }

        // Validar dominio/IP
        String host = url.getHost();
        boolean isAllowedDomain = ALLOWED_DOMAINS.contains(host);
        boolean isAllowedIp = ALLOWED_IPS.contains(host);

        // Permitir "localhost" y direcciones de loopback
        boolean isLocalhost = host.equals("localhost") 
            || host.equals("127.0.0.1") 
            || host.equals("0:0:0:0:0:0:0:1");

        if (!isAllowedDomain && !isAllowedIp && !isLocalhost) {
            throw new SecurityException("Dominio/IP no permitido: " + host);
        }

        // Validar puerto
        int port = url.getPort() != -1 ? url.getPort() : url.getDefaultPort();
        if (!ALLOWED_PORTS.contains(port)) {
            throw new SecurityException("Puerto no permitido: " + port);
        }
    }
}