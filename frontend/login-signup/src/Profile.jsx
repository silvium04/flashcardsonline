import React, { useEffect, useState } from "react";
import "./Profile.css";
import { useNavigate } from "react-router-dom";
import { authFetch } from "./authFetch";
import eyeOff from "./assets/images/icons8-invisible-24.png";
import eyeOn from "./assets/images/icons8-eye-24.png"; // <--- NEU

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
        method: "GET",
      });

      if (response.ok) {
        const data = await response.json();
        setUserData(data);
        setFormData({ ...data, password: "" });
      } else if (response.status === 401) {
        navigate("/login");
      } else {
        setError("Fehler beim Laden der Benutzerdaten");
      }
    } catch (err) {
      setError("Netzwerk- oder Serverfehler");
      console.error("Error:", err);
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

      <div className="inputs">
        <div className="input">
          {isEditing ? (
            <input
              type="text"
              name="firstname"
              placeholder="First Name"
              value={formData.firstname || ""}
              onChange={handleChange}
            />
          ) : (
            <input type="text" value={userData?.firstname || ""} disabled />
          )}
        </div>

        <div className="input">
          {isEditing ? (
            <input
              type="text"
              name="lastname"
              placeholder="Last Name"
              value={formData.lastname || ""}
              onChange={handleChange}
            />
          ) : (
            <input type="text" value={userData?.lastname || ""} disabled />
          )}
        </div>

        <div className="input">
          {isEditing ? (
            <input
              type="text"
              name="username"
              placeholder="Username"
              value={formData.username || ""}
              onChange={handleChange}
            />
          ) : (
            <input type="text" value={userData?.username || ""} disabled />
          )}
        </div>

        <div className="input">
          {isEditing ? (
            <>
              <input
                type={showPassword ? "text" : "password"}
                name="password"
                placeholder="New Password"
                value={formData.password || ""}
                onChange={handleChange}
              />
              <button
                onClick={() => setShowPassword((prev) => !prev)}
                className="togglePasswordButton"
              >
                {showPassword ? (
                  <img src={eyeOff} alt="hide" />
                ) : (
                  <img src={eyeOn} alt="show" />
                )}
              </button>
            </>
          ) : (
            <input type="password" value="********" disabled />
          )}
        </div>
      </div>

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
