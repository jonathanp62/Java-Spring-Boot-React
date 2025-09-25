package net.jmp.spring.boot.react.person;

/*
 * (#)PersonController.java 0.1.0   09/22/2025
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2025 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.concurrent.atomic.AtomicLong;

/// The person controller.
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/person")
public class PersonController {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The people.
    private final Map<Long, Person> people;
    
    /// The counter for generating new person identifiers.
    private final AtomicLong idCounter;

    /// The constructor.
    public PersonController() {
        super();

        this.people = new HashMap<>(Map.of(
                1L, new Person("OK", 1L, "Spock", "Mister", "555-123-4567", "spock@domain.com"),
                2L, new Person("OK", 2L, "Kirk", "James", "555-234-5678", "james@domain.com"),
                3L, new Person("OK", 3L, "McCoy", "Leonard", "555-345-6789", "leonard@domain.com")
        ));
        
        this.idCounter = new AtomicLong(3L); // Start from 4 since we have 3 existing people
    }

    /// The OK method.
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final ResponseEntity<String> result = new ResponseEntity<>("OK", HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The people method.
    ///
    /// @return org.springframework.http.ResponseEntity<java.util.List<net.jmp.spring.boot.react.person.Person>>
    @GetMapping("/people")
    public ResponseEntity<List<Person>> people() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final var persons = new ArrayList<>(this.people.values());

        persons.sort(Comparator.comparing(Person::id));

        final ResponseEntity<List<Person>> result = new ResponseEntity<>(persons, HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The one method.
    ///
    /// @param  id  java.lang.Long
    /// @return     org.springframework.http.ResponseEntity<java.lang.Object>
    @GetMapping("/{id}")
    public ResponseEntity<Object> one(final @PathVariable Long id) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(id));
        }

        final Person person = this.people.getOrDefault(id, null);

        ResponseEntity<Object> result;

        if (person == null) {
            result = new ResponseEntity<>(new PersonApiError("Not found", "Person with identifier '" + id + "' was not found"), HttpStatus.NOT_FOUND);
        } else {
            result = new ResponseEntity<>(person, HttpStatus.OK);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The create method.
    ///
    /// @param  personRequest   net.jmp.spring.boot.react.person.PersonRequest
    /// @return                 org.springframework.http.ResponseEntity<java.lang.Object>
    @PostMapping
    public ResponseEntity<Object> create(final @RequestBody PersonRequest personRequest) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(personRequest));
        }

        ResponseEntity<Object> result;

        try {
            /* Validate the request */

            if (personRequest.lastName() == null || personRequest.lastName().trim().isEmpty()) {
                result = new ResponseEntity<>(new PersonApiError("Validation Error", "Last name is required"), HttpStatus.BAD_REQUEST);
            } else if (personRequest.firstName() == null || personRequest.firstName().trim().isEmpty()) {
                result = new ResponseEntity<>(new PersonApiError("Validation Error", "First name is required"), HttpStatus.BAD_REQUEST);
            } else {
                final Long newId = this.idCounter.incrementAndGet();    // Generate new ID
                
                /* Create the new person */

                final Person newPerson = new Person(
                    "OK",
                    newId,
                    personRequest.lastName().trim(),
                    personRequest.firstName().trim(),
                    personRequest.phoneNumber() != null ? personRequest.phoneNumber().trim() : "",
                    personRequest.emailAddress() != null ? personRequest.emailAddress().trim() : ""
                );

                this.people.put(newId, newPerson);  // Add to the collection
                
                /* Return created person with 201 status */

                result = new ResponseEntity<>(newPerson, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            this.logger.error("Error creating person", e);

            result = new ResponseEntity<>(new PersonApiError("Internal Error", "An error occurred while creating the person"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The update method
    ///
    /// @param  id             java.lang.Long
    /// @param  personRequest  net.jmp.spring.boot.react.person.PersonRequest
    /// @return                org.springframework.http.ResponseEntity<java.lang.Object>
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(final @PathVariable Long id, final @RequestBody PersonRequest personRequest) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(id, personRequest));
        }

        ResponseEntity<Object> result;

        try {
            /* Check if person exists and validate the request */

            if (!this.people.containsKey(id)) {
                result = new ResponseEntity<>(new PersonApiError("Not Found", "Person with identifier '" + id + "' was not found"), HttpStatus.NOT_FOUND);
            } else if (personRequest.lastName() == null || personRequest.lastName().trim().isEmpty()) {
                result = new ResponseEntity<>(new PersonApiError("Validation Error", "Last name is required"), HttpStatus.BAD_REQUEST);
            } else if (personRequest.firstName() == null || personRequest.firstName().trim().isEmpty()) {
                result = new ResponseEntity<>(new PersonApiError("Validation Error", "First name is required"), HttpStatus.BAD_REQUEST);
            } else {
                /* Update the existing person */

                final Person updatedPerson = new Person(
                    "OK",
                    id,
                    personRequest.lastName().trim(),
                    personRequest.firstName().trim(),
                    personRequest.phoneNumber() != null ? personRequest.phoneNumber().trim() : "",
                    personRequest.emailAddress() != null ? personRequest.emailAddress().trim() : ""
                );

                this.people.put(id, updatedPerson); // Update in the collection
                
                /* Return the updated person */

                result = new ResponseEntity<>(updatedPerson, HttpStatus.OK);
            }
        } catch (Exception e) {
            this.logger.error("Error updating person", e);

            result = new ResponseEntity<>(new PersonApiError("Internal Error", "An error occurred while updating the person"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }
}
