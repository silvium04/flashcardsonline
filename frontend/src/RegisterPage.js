import React, { useState } from 'react';

const RegisterPage = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        firstname: '',
        lastname: ''
    });

    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleRegister = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData),
                credentials: 'include'
            });

            if (response.ok) {
                setMessage('Registrierung erfolgreich!');
            } else {
                setMessage('Registrierung fehlgeschlagen.');
            }
        } catch (error) {
            console.error('Error:', error);
            setMessage('Fehler beim Senden der Anfrage.');
        }
    };

    return (
        <div style={{ maxWidth: '300px', margin: 'auto', padding: '1em' }}>
            <h2>Registrieren</h2>
            <form onSubmit={handleRegister}>
                <label>Vorname:</label>
                <input name="firstname" type="text" value={formData.firstname} onChange={handleChange} /><br /><br />

                <label>Nachname:</label>
                <input name="lastname" type="text" value={formData.lastname} onChange={handleChange} /><br /><br />

                <label>Benutzername:</label>
                <input name="username" type="text" value={formData.username} onChange={handleChange} /><br /><br />

                <label>Passwort:</label>
                <input name="password" type="password" value={formData.password} onChange={handleChange} /><br /><br />

                <button type="submit">Registrieren</button>
            </form>
            <p>{message}</p>
        </div>
    );
};

export default RegisterPage;
