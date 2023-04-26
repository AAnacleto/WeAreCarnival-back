package wearecarnival.com.models;
import org.springframework.http.HttpStatus;

import java.util.List;


public class EventosResponse {
    private List<Eventos> eventos;
    private String message;
    private HttpStatus httpStatus;

    public EventosResponse(List<Eventos> eventos, String message, HttpStatus httpStatus) {
        this.eventos = eventos;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
