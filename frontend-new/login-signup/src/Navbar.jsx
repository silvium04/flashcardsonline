import React from "react";
import "./Navbar.css";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <header className="header">
      <nav className="navbar">
        <Link to="/login">Login</Link>
        <Link to="/home">Home</Link>
        <Link to="/decks">Decks</Link>
        <Link to="/profile">Profil</Link>
        <Link to="/"></Link>
      </nav>
    </header>
  );
};

export default Navbar;
