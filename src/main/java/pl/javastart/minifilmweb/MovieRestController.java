package pl.javastart.minifilmweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    @ResponseBody
    public ResponseEntity<?> getMovie(@PathVariable("id") long id) {
        Movie movie = movieRepository.findOne(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PostMapping("")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        Movie movie1 = movieRepository.save(movie);
        return ResponseEntity.ok(movie1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") long id) {
        Movie movie = movieRepository.findOne(id);
        if (movie == null) {
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        } else {
            movieRepository.delete(id);
        }
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editMovie(@RequestBody Movie movie,
                                       @PathVariable("id") long id) {
        Movie movieEdit = movieRepository.findOne(id);
        if (movieEdit == null) {
            return ResponseEntity.notFound().build();
        }
        movieEdit.setTitle(movie.getTitle());
        movieEdit.setPremiereDate(movie.getPremiereDate());
        movieRepository.save(movieEdit);

        return ResponseEntity.ok().build();
    }
}