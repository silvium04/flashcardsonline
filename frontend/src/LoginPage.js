import React, { useState } from 'react';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();

        const response = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password }),
            credentials: 'include'
        });

        if (response.ok) {
            setMessage('Login erfolgreich!');
        } else {
            setMessage('Login fehlgeschlagen.');
        }
    };

    return (
        <div style={{ maxWidth: '300px', margin: 'auto', padding: '1em' }}>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <label>Benutzername:</label>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} /><br /><br />
                <label>Passwort:</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} /><br /><br />
                <button type="submit">Login</button>
            </form>
            <p>{message}</p>
        </div>
    );
};

export default LoginPage;