package com.example.Homework_HTML_Forms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HotelController {

    private HotelRepository hotelRepository;
    private ResortRepository resortRepository;

    public HotelController(HotelRepository hotelRepository, ResortRepository resortRepository) {
        this.hotelRepository = hotelRepository;
        this.resortRepository = resortRepository;
    }

    @GetMapping("/hotels")
    public String getAllHotels(Model model){
        Iterable<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "hotels";
    }
    @GetMapping("/hotels/add")
    public String addHotelForm(Model model){
        Iterable<Resort> resorts = resortRepository.findAll();
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("resorts", resorts);
        return "addHotel";
    }

    @PostMapping("/hotels")
    public String addHotel(@ModelAttribute Hotel hotel, @RequestParam Long resort_id){
        var resort = resortRepository.findById(resort_id).orElse(null);

        if (resort!= null){
            hotel.setResort(resort);
        }
        hotelRepository.save(hotel);
        return "redirect:/hotels";
    }



}
