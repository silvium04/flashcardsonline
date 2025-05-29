import React from "react";
import "./HomePage.css";

const HomePage = () => {
  return (
    <div className="homepage">
      <h1>Welcome to FlashCardsOnline!</h1>
        <div>
            <button className="submit">
                Sign Up
            </button>
        </div>
        <div>
            <button className="submit">
                Login
            </button>
        </div>

    </div>
  );
};

export default HomePage;
