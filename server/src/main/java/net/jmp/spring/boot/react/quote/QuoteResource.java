package net.jmp.spring.boot.react.quote;

/*
 * (#)QuoteResource.java    0.1.0   09/20/2025
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

/// The quote resource.
public class QuoteResource {
    /// The type of the quote.
    private String type;

    /// The value of the quote.
    private Quote value;

    /// The constructor.
    ///
    /// @param  value   The quote.
    /// @param  type    The type of the quote.
    QuoteResource(final Quote value, final String type) {
        this.value = value;
        this.type = type;
    }

    /// The get type method.
    ///
    /// @return java.lang.String
    public String getType() {
        return this.type;
    }

    /// The set type method.
    ///
    /// @param  type    The type of the quote.
    public void setType(final String type) {
        this.type = type;
    }

    /// The get value method.
    ///
    /// @return net.jmp.spring.boot.react.quote.Quote
    public Quote getValue() {
        return this.value;
    }

    /// The set value method.
    ///
    /// @param  value   The quote.
    public void setValue(final Quote value) {
        this.value = value;
    }

    /// The equals method.
    ///
    /// @param  o   java.lang.Object
    /// @return     boolean
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;

        if (o instanceof QuoteResource that) {
            return Objects.equals(this.type, that.type) && Objects.equals(this.value, that.value);
        }

        return false;
    }

    /// The hash code method.
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.value);
    }

    /// The to string method.
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "QuoteResource{" + "type='" + this.type + '\'' + ", value=" + this.value + '}';
    }
}
