import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./LoginSignup.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const apiUrl = process.env.REACT_APP_API_URL;

  const handleLogin = async () => {
    try {
      const response = await fetch(`${apiUrl}/api/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          username: username,
          password: password
        })
      });
      const data = await response.json();
      if (!response.ok) {
        throw new Error(data.error);
      }
      console.log("Login erfolgreich:", data);
      navigate("/decks");
    } catch (error) {
      console.error("Fehler beim Login:", error.message);
      alert(error.message);
    }
  };

  return (
    <main className="page-wrapper">
      <div className="container">
        <div className="form-header">
          <div className="text">Login</div>
          <div className="underline"></div>
        </div>
        <div className="inputs">
          <div className="input">
            <input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="input">
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
        </div>
        <div className="submit-container">
          <button className="submit" onClick={handleLogin}>
            Login
          </button>
          <button className="submit small" onClick={() => navigate("/signup")}>
            Sign Up
          </button>
        </div>
      </div>
    </main>
  );
};

export default Login;
