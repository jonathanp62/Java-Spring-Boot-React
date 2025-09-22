package net.jmp.spring.boot.react.quote;

/*
 * (#)QuoteController.java  0.1.0   09/20/2025
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

import java.util.List;
import java.util.Random;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

/// The quote controller.
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/quote")
public class QuoteController {
    /// The quote repository.
    private final QuoteRepository repository;

    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The randomizer.
    private final Random randomizer = new Random();

    /// The constructor.
    ///
    /// @param repository net.jmp.spring.boot.react.quote.QuoteRepository
    public QuoteController(final QuoteRepository repository) {
        super();

        this.repository = repository;
    }

    /// The OK method.
    ///
    /// @return org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.quote.QuoteResource>
    @GetMapping("/ok")
    public ResponseEntity<QuoteResource> ok() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final Quote quote = new Quote("OK");

        quote.setId(0L);

        final ResponseEntity<QuoteResource> result = new ResponseEntity<>(new QuoteResource(quote, "success"), HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The get one method.
    ///
    /// @param  id  java.lang.Long
    /// @return     org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.quote.QuoteResource>
    @GetMapping("/{id}")
    public ResponseEntity<QuoteResource> getOne(final @PathVariable Long id) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(id));
        }

        final ResponseEntity<QuoteResource> result = this.repository.findById(id)
                .map(quote -> new ResponseEntity<>(new QuoteResource(quote, "success"), HttpStatus.OK))
                .orElseGet(() -> {
                    final Quote quote = new Quote("");

                    quote.setId(id);

                    return new ResponseEntity<>(new QuoteResource(quote, "Not found"), HttpStatus.NOT_FOUND);
                });

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The get all method.
    ///
    /// @return org.springframework.http.ResponseEntity<java.util.List<net.jmp.spring.boot.react.quote.QuoteResource>>
    @GetMapping("/all")
    public ResponseEntity<List<QuoteResource>> getAll() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final ResponseEntity<List<QuoteResource>> result = new ResponseEntity<>(this.repository.findAll().stream()
                .map(quote -> new QuoteResource(quote, "success"))
                .toList(), HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The get random one method.
    ///
    /// @return org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.quote.QuoteResource>
    @GetMapping("/random")
    public ResponseEntity<QuoteResource> getRandomOne() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final ResponseEntity<QuoteResource> result = this.getOne(nextLong(1, this.repository.count() + 1));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The next long method.
    ///
    /// @param  lowerRange  long
    /// @param  upperRange  long
    /// @return             long
    private long nextLong(final long lowerRange, final long upperRange) {
        return (long) (this.randomizer.nextDouble() * (upperRange - lowerRange)) + lowerRange;
    }
}
