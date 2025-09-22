package net.jmp.spring.boot.react.quote;

/*
 * (#)QuoteRepositoryLoader.java    0.1.0   09/20/2025
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

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/// The quote repository loader class.
@Configuration
public class QuoteRepositoryLoader {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The default constructor.
    public QuoteRepositoryLoader() {
        super();
    }

    /// Load the quote repository.
    ///
    /// @param  repository  net.jmp.spring.boot.react.quote.QuoteRepository
    /// @return             org.springframework.boot.CommandLineRunner
    @Bean
    CommandLineRunner load(final QuoteRepository repository) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }

        return args -> {
            repository.save(new Quote("Working with Spring Boot is like pair-programming with the Spring developers."));
            repository.save(new Quote("With Boot you deploy everywhere you can find a JVM basically."));
            repository.save(new Quote("Spring has come quite a ways in addressing developer enjoyment and "
                    + "ease of use since the last time I built an application using it."));
            repository.save(new Quote(
                    "Previous to Spring Boot, I remember XML hell, confusing set up, and " + "many hours of frustration."));
            repository.save(new Quote("Spring Boot solves this problem. It gets rid of XML and wires up "
                    + "common components for me, so I don't have to spend hours scratching my "
                    + "head just to figure out how it's all pieced together."));
            repository.save(new Quote("It embraces " + "convention over configuration, providing an experience on par with "
                    + "frameworks that excel at early stage development, such as Ruby on " + "Rails."));
            repository.save(new Quote("The real benefit of Boot, however, is that it's just Spring. That "
                    + "means any direction the code takes, regardless of complexity, I know " + "it's a safe bet."));
            repository.save(new Quote("I don't worry about my code scaling. Boot allows the "
                    + "developer to peel back the layers and customize when it's appropriate "
                    + "while keeping the conventions that just work."));
            repository.save(new Quote("So easy it is to switch container in #springboot."));
            repository.save(new Quote("Really loving Spring Boot, makes stand alone Spring apps easy."));
            repository.save(new Quote("I have two hours today to build an app from scratch. @springboot to the rescue!"));
            repository.save(new Quote("@springboot with @springframework is pure productivity! Who said in #java one has "
                    + "to write double the code than in other langs? #newFavLib"));
        };
    }
}
