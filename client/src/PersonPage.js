import React, { useState, useEffect } from 'react';

const PersonPage = () => {
    const [ok, setOk] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Define an async function to perform the fetch
        const fetchOk = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/person/ok');

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.text();

                setOk(data);
            } catch (e) {
                setError(e.message);
            } finally {
                setIsLoading(false);
            }
        };

        fetchOk();
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
        </div>
    );
};

export default PersonPage;