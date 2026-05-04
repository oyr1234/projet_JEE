    package com.example.biblio.controller;

    import com.example.biblio.model.Emprunt;
    import com.example.biblio.service.EmpruntService;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/emprunts")
    @CrossOrigin(origins = "http://localhost:4200")
    public class EmpruntController {

        private final EmpruntService service;

        public EmpruntController(EmpruntService service) {
            this.service = service;
        }

        // USER request borrow
        @PostMapping("/{livreId}")
        public Emprunt create(@PathVariable Long livreId) {
            return service.create(livreId);
        }

        // BIBLIOTHECAIRE validates
        @PutMapping("/valider/{id}")
        public Emprunt valider(@PathVariable Long id) {
            return service.valider(id);
        }

        // Return book
        @PutMapping("/retour/{id}")
        public Emprunt retour(@PathVariable Long id) {
            return service.retour(id);
        }

        // All emprunts
        @GetMapping
        public List<Emprunt> getAll() {
            return service.getAll();
        }

        // Pending validation
        //@GetMapping("/pending")
        //public List<Emprunt> pendingRequests() {
           // return service.pendingRequests();
        }

//        // Returned books
//        @GetMapping("/returned")
//        public List<Emprunt> returnedBooks() {
//            return service.returnedBooks();
//        }
//
//        // Overdue books
//        @GetMapping("/overdue")
//        public List<Emprunt> overdueBooks() {
//            return service.overdueBooks();
//