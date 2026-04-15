package it.esempio.rest;

import it.esempio.entity.Libro;
import it.esempio.service.LibroService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/libri")                         // Indirizzo URL per raggiungere la classe
@Produces(MediaType.APPLICATION_JSON)   // trasforma gli oggetti in risposta in JSON
public class LibroResource {            // Servizio REST

    @Inject                             // Dependency injection -> Invece di fare service = new LibroService()
    private LibroService service;

    @GET                                // Risponde alle richieste GET con la lista dei libri presenti
    public List<Libro> lista(){
        return service.tutti();
    }

    @POST                               // Tramite le richieste POST si possono aggiungere nuovi libri
    @Path("/{titolo}")
    public String crea(@PathParam("titolo") String titolo){
        service.salva(titolo);
        return "Libro '" + titolo + "' salvato!";
    }
}