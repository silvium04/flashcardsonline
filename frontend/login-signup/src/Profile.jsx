import React, { useEffect, useState } from "react";
import "./Profile.css";
import { useNavigate } from "react-router-dom";
import { authFetch } from "./authFetch"; // <--- NEU

const Profile = () => {
  const [isEditing, setIsEditing] = useState(false);
  const [userData, setUserData] = useState(null);
  const [formData, setFormData] = useState({});
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const apiUrl = process.env.REACT_APP_API_URL;

  useEffect(() => {
    fetchUserData();
  }, []);

  const fetchUserData = async () => {
    try {
      const response = await authFetch(`${apiUrl}/api/user/profile`, {
        method: 'GET',
      });

      if (response.ok) {
        const data = await response.json();
        setUserData(data);
        setFormData({ ...data, password: ''});
      } else if (response.status === 401) {
        navigate('/login');
      } else {
        setError('Fehler beim Laden der Benutzerdaten');
      }
    } catch (err) {
      setError('Netzwerk- oder Serverfehler');
      console.error('Error:', err);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = () => {
    // Optional: Änderungen an den Server schicken
    setUserData(formData);
    setIsEditing(false);
  };

  return (
      <div className="profile-container">
        <h2>Profile</h2>

        {error && <p className="error">{error}</p>}

        <div className="profile-info">
          <label>First Name:</label>
          {isEditing ? (
              <input
                  type="text"
                  name="firstname"
                  value={formData.firstname || ""}
                  onChange={handleChange}
              />
          ) : (
              <p>{userData?.firstname}</p>
          )}

          <label>Last Name:</label>
          {isEditing ? (
              <input
                  type="text"
                  name="lastName"
                  value={formData.lastname || ""}
                  onChange={handleChange}
              />
          ) : (
              <p>{userData?.lastname}</p>
          )}

          <label>Username:</label>
          {isEditing ? (
              <input
                  type="text"
                  name="username"
                  value={formData.username || ""}
                  onChange={handleChange}
              />
          ) : (
              <p>{userData?.username}</p>
          )}

          <label>Password:</label>
          {isEditing ? (
              <input
                  type={showPassword ? "text" : "password"}
                  name="password"
                  placeholder="Type new password"
                  value={formData.password || ""}
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
              <label htmlFor="showPassword">show Password</label>
            </div>
        )}

        <div className="profile-buttons">
          {isEditing ? (
              <button onClick={handleSave}>Save Changes</button>
          ) : (
              <div>
                <div>
                  <button onClick={() => setIsEditing(true)}>Edit</button>
                </div>
                <div>
                  <button>Delete Profile</button>
                </div>
              </div>
          )}
        </div>
      </div>
  );
};

export default Profile;
