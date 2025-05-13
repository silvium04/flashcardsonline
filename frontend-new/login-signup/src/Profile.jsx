import React, { useState } from "react";
import "./Profile.css";

const Profile = () => {
  const [isEditing, setIsEditing] = useState(false);
  const [userData, setUserData] = useState({
    firstName: "Max",
    lastName: "Mustermann",
    username: "max123",
    password: "passwort123",
  });

  const [formData, setFormData] = useState({ ...userData });
  const [showPassword, setShowPassword] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = () => {
    setUserData(formData);
    setIsEditing(false);
  };

  return (
    <div className="profile-container">
      <h2>Profil</h2>

      <div className="profile-info">
        <label>Vorname:</label>
        {isEditing ? (
          <input
            type="text"
            name="firstName"
            value={formData.firstName}
            onChange={handleChange}
          />
        ) : (
          <p>{userData.firstName}</p>
        )}

        <label>Nachname:</label>
        {isEditing ? (
          <input
            type="text"
            name="lastName"
            value={formData.lastName}
            onChange={handleChange}
          />
        ) : (
          <p>{userData.lastName}</p>
        )}

        <label>Username:</label>
        {isEditing ? (
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
          />
        ) : (
          <p>{userData.username}</p>
        )}

        <label>Passwort:</label>
        {isEditing ? (
          <input
            type={showPassword ? "text" : "password"}
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
        ) : (
          <p>********</p>
        )}
      </div>

      {isEditing && (
        <div className="toggle-password">
          <input
            type="checkbox"
            id="showPassword"
            checked={showPassword}
            onChange={() => setShowPassword(!showPassword)}
          />
          <label htmlFor="showPassword">Passwort anzeigen</label>
        </div>
      )}

      <div className="profile-buttons">
        {isEditing ? (
          <button onClick={handleSave}>Speichern</button>
        ) : (
          <button onClick={() => setIsEditing(true)}>Bearbeiten</button>
        )}
      </div>
    </div>
  );
};

export default Profile;
