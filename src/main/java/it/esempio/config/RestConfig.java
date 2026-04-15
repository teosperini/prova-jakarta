package it.esempio.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api") // Tutti i tuoi URL inizieranno con /api
public class RestConfig extends Application {
    // Lascia vuoto, serve solo ad attivare JAX-RS
}