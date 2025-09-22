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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/// The person controller.
@RestController
@RequestMapping("/api/person")
public class PersonController {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The people.
    private final Map<Long, Person> people;

    /// The constructor.
    public PersonController() {
        super();

        this.people = Map.of(
                1L, new Person("OK", 1L, "Spock", "Mister", "555-123-4567", "spock@domain.com"),
                2L, new Person("OK", 2L, "Kirk", "James", "555-234-5678", "james@domain.com"),
                3L, new Person("OK", 3L, "McCoy", "Leonard", "555-345-6789", "leonard@domain.com")
        );
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
}
