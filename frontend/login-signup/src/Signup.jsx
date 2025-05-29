import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./LoginSignup.css";

const Signup = () => {
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const apiUrl = process.env.REACT_APP_API_URL;

  const handleSignUp = async () => {
        if (firstname.length < 1 || lastname.length < 1 || username.length < 1 || password.length < 8) {
            alert("Please fill in all fields correctly.");
            return;
        }
        try {
            const response = await fetch(`${apiUrl}/api/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    password: password,
                    firstname: firstname,
                    lastname: lastname
                })
            });
            const data = await response.json();
            console.log("Signup successful:", data);
            navigate("/login");

        } catch (error) {
            console.error("Error during signup:", error);
            alert("An error occurred. Please try again.");
        }
  };

  return (
    <div className="container">
      <div className="form-header">
        <div className="text">Sign Up</div>
        <div className="underline"></div>
      </div>
      <div className="inputs">
        <div className="input">
          <input
            type="text"
            placeholder="First Name"
            value={firstname}
            required={true}
            onChange={(e) => setFirstname(e.target.value)}
          />
        </div>
        <div className="input">
          <input
            type="text"
            placeholder="Last Name"
            value={lastname}
            required={true}
            onChange={(e) => setLastname(e.target.value)}
          />
        </div>
        <div className="input">
          <input
            type="text"
            placeholder="Username"
            value={username}
            required={true}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="input">
          <input
            type="password"
            placeholder="Password (min. 8 characters)"
            value={password}
            minLength={8}
            required={true}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
      </div>
      <div className="submit-container">
        <button className="submit" onClick={handleSignUp}>
          Sign Up
        </button>
        <button className="submit small" onClick={() => navigate("/login")}>
          Login
        </button>
      </div>
    </div>
  );
};

export default Signup;
