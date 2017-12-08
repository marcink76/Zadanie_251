package pl.javastart.minifilmweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//        GET /api/movies/
//        GET /api/movies/{id}
//        POST /api/movies/
//        PUT /api/movies/{id}
//        DELETE /api/movies/{id}
@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getAllMovies() {
        System.out.println("Odpytanie o wszystkie filmy");
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable("id") long id) {
        return movieRepository.findOne(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        Movie movie1 = movieRepository.save(movie);
        return ResponseEntity.ok(movie1);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") long id) {
        movieRepository.delete(id);
    }

    @PutMapping("/{id}")
    public void editMovie(@RequestBody Movie movie,
                          @PathVariable("id") long id) {
        Movie movieEdit = movieRepository.findOne(id);
        //movieEdit.setId(movie.getId());
        movieEdit.setTitle(movie.getTitle());
        movieEdit.setPremiereDate(movie.getPremiereDate());
        movieRepository.save(movieEdit);
    }
}