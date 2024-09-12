package br.unitins.tp1.monitores.resource;

import java.util.List;

import br.unitins.tp1.monitores.model.Monitor;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;


@Path("/monitores")
public class MonitorResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Monitor> findAll(){



        return Monitor.listAll();
        
    }
}

