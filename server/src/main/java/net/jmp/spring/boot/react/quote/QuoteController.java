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
 * Copyright (c) 2024 Jonathan M. Parker
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/// The quote controller.
@RestController
@RequestMapping("/api/quote")
public class QuoteController {
    /// The quote repository.
    private final QuoteRepository repository;

    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The constructor.
    ///
    /// @param repository net.jmp.spring.boot.react.quote.QuoteRepository
    public QuoteController(final QuoteRepository repository) {
        this.repository = repository;
    }

    /// The OK method.
    ///
    /// @return net.jmp.spring.boot.react.quote.QuoteResource
    @GetMapping("/ok")
    public QuoteResource ok() {
        final Quote quote = new Quote("OK");

        quote.setId(0L);

        return new QuoteResource(quote, "success");
    }

    /// The get all method.
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.quote.QuoteResource>
    @GetMapping("/all")
    public List<QuoteResource> getAll() {
        return this.repository.findAll().stream()
                .map(quote -> new QuoteResource(quote, "success"))
                .toList();
    }
}
