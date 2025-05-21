import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./LoginSignup.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    const response = await fetch('http://localhost:8080/api/cards');
    if (!response.ok) {
      throw new Error('Fehler beim Laden der Karten');
    }
    return await response.json();
    console.log("Login:", { username, password });
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
