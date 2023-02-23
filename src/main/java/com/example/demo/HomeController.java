package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model) {

        // we need to save director first because its id will go to child table
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        Movie movie = new Movie();
        movie.setTitle("Star Movie");

        movie.setYear(2017);
        movie.setDescription("About Stars...");
        movie.setDirector(director);

        Set<Movie> movies = new HashSet<>();
        movies.add(movie);

        movie = new Movie();
        movie.setTitle("DeathStar Ewoks");

        movie.setYear(2011);
        movie.setDescription("About Ewoks on the DeathStar...");
        movie.setDirector(director);

        movies.add(movie);

        director.setMovies(movies);

        directorRepository.save(director);

        /*// we need to save director first because its id will go to child table
        Director director =new Director("Stephen Bullock","Sci Fi");

        Movie movie = new Movie("Star Movie",2017,"About Stars...",director);
        director.setMovies(Arrays
                .asList(movie)
                .stream()
                .collect(Collectors.toSet()));
        directorRepository.save(director);*/

        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }
}
