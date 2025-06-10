package com.example.driveintheater.controller;

import com.example.driveintheater.model.Movie;
import com.example.driveintheater.model.Screening;
import com.example.driveintheater.repository.ScreeningRepository;
import com.example.driveintheater.service.MovieService;
import com.example.driveintheater.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    private final MovieService movieService;
    private final ScreeningService screeningService;

    private final ScreeningRepository screeningRepository;

    @Autowired
    public MovieController(MovieService movieService, ScreeningService screeningService, ScreeningRepository screeningRepository) {
        this.movieService = movieService;
        this.screeningService = screeningService;
        this.screeningRepository = screeningRepository;
    }


    @GetMapping("/")
    public String home(Model model) {
        List<Screening> upcomingScreenings = screeningRepository.findTop3ByScreeningTimeAfterOrderByScreeningTimeAsc(LocalDateTime.now());
        model.addAttribute("upcomingScreenings", upcomingScreenings);
        return "index";
    }
    @GetMapping("/movies")
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String getMovieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            List<Screening> screenings = movieService.getScreeningsForMovie(id);
            if (screenings.isEmpty()) {
                logger.info("Žádné projekce pro tento film.");
            } else {
                logger.info("Projekce nalezeny pro film: {}", movie.getTitle());
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            model.addAttribute("movie", movie);
            model.addAttribute("screenings", screenings);
            model.addAttribute("formatter", formatter);
        }
        return "movie-detail";
    }

    @GetMapping("/program")
    public String showProgram(@RequestParam(defaultValue = "0") int dayOffset,
                              @RequestParam(defaultValue = "false") boolean showAll,
                              Model model) {

        List<Screening> screenings;

        if (showAll) {
            screenings = screeningService.getAllScreenings();
        } else {
            LocalDate targetDate = LocalDate.now().plusDays(dayOffset);
            LocalDateTime startOfDay = targetDate.atStartOfDay();
            LocalDateTime endOfDay = targetDate.atTime(LocalTime.MAX);
            screenings = screeningService.getScreeningsBetween(startOfDay, endOfDay);
            model.addAttribute("currentDate", targetDate);
            model.addAttribute("dayOffset", dayOffset);
        }

        model.addAttribute("screenings", screenings);
        model.addAttribute("showAll", showAll);
        return "program";
    }


    @GetMapping("/movies/search")
    public String searchMovies(@RequestParam String query, Model model) {
        if (query == null || query.trim().isEmpty()) {
            return "redirect:/movies";
        }

        List<Movie> searchResults = movieService.searchMoviesByTitle(query);
        model.addAttribute("movies", searchResults);
        model.addAttribute("query", query);
        return "movies";
    }

    @PostMapping("/movies/add")
    public String addMovie(@RequestParam String title,
                           @RequestParam String description,
                           @RequestParam String genre,
                           @RequestParam int durationMinutes,
                           @RequestParam String imageUrl) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setGenre(genre);
        movie.setDurationMinutes(durationMinutes);
        movie.setImageUrl(imageUrl);

        movieService.addMovie(movie);

        return "redirect:/movies";
    }
}
