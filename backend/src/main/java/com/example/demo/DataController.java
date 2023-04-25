package com.example.demo;

import com.example.demo.entities.MinMaxValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/average_exchange/{code}/{date}")
    @ResponseBody
    public ResponseEntity<String> getAverageExchange(@PathVariable("code") String code, @PathVariable("date") String date) {
        if(!DateValidator.isValidDate(date))
            throw new BadRequestException("Invalid input");
        return dataService
                .getAverageExchangeRateByCodeAndDate(code, date)
                .map(averageExchange -> ResponseEntity.ok().body(averageExchange))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("min_max/{code}/{topCount}")
    @ResponseBody
    public ResponseEntity<MinMaxValue> getMinMaxValues(@PathVariable("code") String code, @PathVariable("topCount") int topCount) {
        if(topCount <= 0 || topCount > 255)
            throw new BadRequestException("Invalid input");
        return dataService
                .getMinMaxExchangeRateByCodeAndTopCount(code, topCount)
                .map(averageExchange -> ResponseEntity.ok().body(averageExchange))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("major_diff/{code}/{topCount}")
    @ResponseBody
    public ResponseEntity<Double> getMajorDiff(@PathVariable("code") String code, @PathVariable("topCount") int topCount) {
        if(topCount <= 0 || topCount > 255)
            throw new BadRequestException("Invalid input");
        return dataService
                .getDiffBidAskByCodeAndTopCount(code, topCount)
                .map(averageExchange -> ResponseEntity.ok().body(averageExchange))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
