package dev.saxo.trading;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;


@SpringBootApplication
public class SaxoTradingApplication {

    @Value("${h2db-port}")
    private String tcpPort;

    public static void main(String[] args) {
        SpringApplication.run(SaxoTradingApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", tcpPort);
    }
}
