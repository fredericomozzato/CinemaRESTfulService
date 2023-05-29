package cinema.presentation;

import cinema.business.*;
import cinema.exceptionhandling.NonExistingSeatException;
import cinema.exceptionhandling.UnavailableTicketException;
import cinema.exceptionhandling.WrongPasswordException;
import cinema.exceptionhandling.WrongTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class Controller {

    private final Room cinemaRoom;
    private final RoomService roomService;
    private final RoomDtoMapper dtoMapper;
    private final StatisticsService statsService;

    @Autowired
    public Controller(Room cinemaRoom, RoomService roomService, RoomDtoMapper dtoMapper, StatisticsService statsService) {
        this.cinemaRoom = cinemaRoom;
        this.roomService = roomService;
        this.dtoMapper = dtoMapper;
        this.statsService = statsService;
    }

    @GetMapping("/seats")
    public RoomDTO getSeats() throws JsonProcessingException {
        return this.dtoMapper.mapToDTO(
                this.cinemaRoom
        );
    }

    @PostMapping("/purchase")
    public SeatDTO purchaseTicket(@RequestBody SeatRequest request) {
        return this.roomService.purchaseTicket(request);
    }

    @PostMapping("/return")
    public ReturnedTicketDTO returnTicket(@RequestBody TokenRequest token) {
//        System.out.println("Token from JSON: " + token);
        return this.roomService.returnTicket(token.getToken());
    }

    @PostMapping("/stats")
    public StatisticsDTO getStats(@RequestParam(value = "password", required = false) String password) {
        return this.statsService.getStats(password);
    }




    // exception handlers
    @ExceptionHandler(UnavailableTicketException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> unavailableTicketHandler(UnavailableTicketException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }
    @ExceptionHandler(NonExistingSeatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> nonExistingSeatHandler(NonExistingSeatException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }
    @ExceptionHandler(WrongTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> wrongTokenHandler(WrongTokenException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }
    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String, String> wrongPasswordHandler(WrongPasswordException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }
}
