package net.jmp.spring.boot.react.quote;

/*
 * (#)Quote.java    0.1.0   09/20/2025
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

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/// The Quote entity.
@Entity
public class Quote {
    /// The identifier of the quote.
    @Id @GeneratedValue private Long id;

    /// The quote text.
    private String text;

    /// The constructor.
    ///
    /// @param  text    java.lang.String
    public Quote(final String text) {
        super();

        this.text = text;
    }

    /// The default constructor.
    protected Quote() {
        super();
    }

    ///  Get the identifier of the quote.
    ///
    /// @return  java.lang.Long
    public Long getId() {
        return this.id;
    }

    /// Set the identifier of the quote.
    ///
    /// @param  id  java.lang.Long
    public void setId(final Long id) {
        this.id = id;
    }

    /// Get the quote text.
    ///
    /// @return  java.lang.String
    public String getText() {
        return this.text;
    }

    /// Set the quote text.
    ///
    /// @param  text    java.lang.String
    public void setText(final String text) {
        this.text = text;
    }

    /// The equals method.
    ///
    /// @param  o   java.lang.Object
    /// @return     boolean
    public boolean equals(final Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Quote quote))
            return false;

        return Objects.equals(this.id, quote.id) && Objects.equals(this.text, quote.text);
    }

    /// The hashCode method.
    ///
    /// @return  int
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.text);
    }

    /// The toString method.
    ///
    /// @return  java.lang.String
    @Override
    public String toString() {
        return "Quote{" + "id=" + this.id + ", text='" + this.text + '\'' + '}';
    }
}
