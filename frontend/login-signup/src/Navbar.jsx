import React from "react";
import "./Navbar.css";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <header className="header">
      <nav className="navbar">
        <Link to="/decks">Decks</Link>
        <Link to="/profile">Profile</Link>

        <Link to="/"></Link>
      </nav>
    </header>
  );
};

export default Navbar;
