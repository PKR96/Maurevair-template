package com.maurevair.booking.web;

import com.maurevair.booking.domain.AirportInfo;
import com.maurevair.booking.domain.FlightDetails;
import com.maurevair.booking.service.AirportInfoService;
import com.maurevair.booking.service.FlightDetailsService;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/info")
public class RestApi {

    private final FlightDetailsService flightDetailsService;
    private final AirportInfoService airportInfoService;

    @Autowired
    public RestApi(FlightDetailsService flightDetailsService, AirportInfoService airportInfoService) {
        this.flightDetailsService = flightDetailsService;
        this.airportInfoService=airportInfoService;
    }

    private Logger logger = LoggerFactory.getLogger(RestApi.class);

    @ResponseBody
    @RequestMapping(value = "/saveFlightDetails", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> saveData(@RequestParam String xmlUri) {
        Map<String,String> messages = new HashMap<>();
        try {
            URL url = new URL(xmlUri);
            flightDetailsService.saveFlightDetails(url,messages);
            return new ResponseEntity<>(messages,HttpStatus.ACCEPTED);
        }
            catch(MalformedURLException | JAXBException malformedException){
                logger.error(malformedException.getMessage());
                messages.put("error",malformedException.getMessage());
                return new ResponseEntity<>(messages,HttpStatus.NOT_IMPLEMENTED);
            }
    }

    @ResponseBody
    @RequestMapping(value="/saveAirportInfo",method=RequestMethod.POST)
    public ResponseEntity<String> saveExcel(@RequestParam String excelLocation){
        File excelFile = new File(excelLocation);
        try {
            List<String[]> values = new CSVReaderBuilder(new FileReader(excelFile))
                    .withSkipLines(1)
                    .build()
                    .readAll();
            airportInfoService.saveAirportInfo(values);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (FileNotFoundException fnfe){
            logger.error(fnfe.getMessage());
        } catch (IOException | CsvException e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ResponseBody
    @RequestMapping(value="/listAllAirports", method=RequestMethod.GET)
    public List<AirportInfo> getAirportDetails(){
        return airportInfoService.getAllInfo();
    }

    @ResponseBody
    @RequestMapping(value="/getAirport/{airportId}",method = RequestMethod.GET)
    public AirportInfo getAirportInfo(@PathVariable String airportId){
        return airportInfoService.getAirport(Long.parseLong(airportId));
    }

    @ResponseBody
    @RequestMapping(value="/getAvailableFlight", method=RequestMethod.GET)
    public String getAvailableFlights(@RequestParam String origin, @RequestParam String destination){
    List<FlightDetails> flightDetails=flightDetailsService.getFlightDetailsForOriginAndDestination(origin,destination);
    return flightDetailsService.displayData(flightDetails);
    }

    @ResponseBody
    @RequestMapping(value="/reserveSeats",method=RequestMethod.POST)
    public String reserveFlight(HttpServletRequest request){
     return "<p>"+flightDetailsService.reserveFlight(request)+"</p>";
    }
}
