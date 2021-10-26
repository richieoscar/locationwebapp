package com.richieoscar.locationwebapp.controllers;

import com.richieoscar.locationwebapp.model.Location;
import com.richieoscar.locationwebapp.service.LocationService;
import com.richieoscar.locationwebapp.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LocationController {

    @Autowired
    LocationService service;

    @Autowired
    EmailUtil emailUtil;

    @RequestMapping("/create")
    String showCreatePage() {
        return "createLocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute Location location, Model model) {
        service.saveLocation(location);
        String message = "Success ";
        List<Location> allLocations = service.getAllLocations();
        model.addAttribute("msg", message);
        model.addAttribute("locations", allLocations);
        emailUtil.sendEmail("oscaranyiam94@gmail.com",
                "SAVED LOCATION",
                "Your have Successfully saved a new Location!\n"
                        +"Location Code: " +location.getCode() +"\n"
                        +"Location Name: " +location.getName());
        return "createLocation";
    }

    @RequestMapping("/displayLocations")
    public String getLocations(Model model) {
        List<Location> allLocations = service.getAllLocations();
        model.addAttribute("locations", allLocations);
        return "displayLocations";
    }

    @RequestMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("id") int id, Model model) {
        Location location = service.getLocationById(id);
        service.deleteLocation(location);
        List<Location> allLocations = service.getAllLocations();
        model.addAttribute("locations", allLocations);
        return "displayLocations";
    }

    @RequestMapping("/updateLocation")
    public String showUpdateLocation(@RequestParam("id") int id, Model model) {
        Location location = service.getLocationById(id);
        model.addAttribute("location", location);
        return "updateLocation";
    }

    @RequestMapping("/updateLoc")
    public String updateLocation(@ModelAttribute Location location, Model model) {
        service.updateLocation(location);
        List<Location> allLocations = service.getAllLocations();
        String msg = "Location Updated Successfully.";
        model.addAttribute("msg", msg);
        model.addAttribute("locations", allLocations);
        return "updateLocation";

    }
}
