package com.example.driveintheater.service;

import com.example.driveintheater.model.Movie;
import com.example.driveintheater.model.Screening;
import com.example.driveintheater.repository.MovieRepository;
import com.example.driveintheater.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public List<Screening> getScreeningsForMovie(Long movieId) {
        return screeningRepository.findByMovieId(movieId);
    }

    public List<Movie> searchMoviesByTitle(String query) {
        return movieRepository.findByTitleContainingIgnoreCase(query); // search by TITLE
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

}
