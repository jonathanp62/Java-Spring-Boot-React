/*
 * (#)PersonComponent.jsx   0.1.0   09/23/2025
 *
 * @author  Jonathan Parker
 * @version 0.1.0
 * @since   0.1.0
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

import "./Tables.css";

import React, { useState, useEffect } from 'react';

import PersonFinder from "./PersonFinder";

const PersonComponent = () => {
    const [ok, setOk] = useState(null);
    const [people, setPeople] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [responseOK, responsePeople] = await Promise.all([
                    fetch('http://localhost:8080/api/person/ok'),
                    fetch('http://localhost:8080/api/person/people')
                ]);

                if (responseOK.ok && responsePeople.ok) {
                    const resultOK = await responseOK.text();
                    const resultPeople = await responsePeople.json();

                    setOk(resultOK);
                    setPeople(resultPeople);
                } else if (!responseOK.ok) {
                    setError(`OK API HTTP error: ${responseOK.status}`);
                } else if (!responsePeople.ok) {
                    setError(`People API HTTP error: ${responsePeople.status}`);
                }
            } catch (e) {
                setError(e.message);
            } finally {
                setIsLoading(false);
            }
        };

        fetchData();
    }, []); // The empty dependency array ensures this effect runs only once

    if (isLoading) {
        return <div>Loading ok...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            <h2>Person API</h2>
            <p>OK API: {ok}</p>
            <table className="table-container">
                {people.map((person) => (
                    <tr key={person.id}>
                        <td>{person.id}</td>
                        <td>{person.firstName} {person.lastName}</td>
                        <td>{person.phoneNumber}</td>
                        <td>{person.emailAddress}</td>
                    </tr>))}
            </table>
            <p/>
            <PersonFinder />
        </div>
    );
};

export default PersonComponent;