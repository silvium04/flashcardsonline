import React from "react";
import { useNavigate } from "react-router-dom";
import "./HomePage.css";

const HomePage = () => {
    const navigate = useNavigate();
  return (
    <div className="homepage">
      <h1>Welcome to FlashCardsOnline!</h1>
        <div>
            <button className="submit" onClick={() => navigate("/signup")}>
                Sign Up
            </button>
        </div>
        <div>
            <button className="submit" onClick={() => navigate("/login")}>
                Login
            </button>
        </div>

    </div>
  );
};

export default HomePage;
