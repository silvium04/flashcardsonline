import React from "react";
import "./Navbar.css";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const Navbar = () => {
    const navigate = useNavigate();
    const logout = () => {
        localStorage.removeItem("token");
        navigate("/home");
    };

  return (
    <header className="header">
      <nav className="navbar">
        <Link to="/decks">Decks</Link>
        <Link to="/profile">Profile</Link>
          <button onClick={logout}>Logout</button>
        <Link to="/"></Link>
      </nav>
    </header>
  );
};

export default Navbar;
