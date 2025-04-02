import React, { useState } from 'react';
import './Header.css';

const Header = () => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  const handleProfileClick = () => {
    alert('Profile clicked!');
  };

  const handleLogoutClick = () => {
    alert('Logout clicked!');
  };

  return (
    <header className="header">
      <h1 className="logo">My App</h1>
      <div className="dropdownContainer">
        <button className="dropdownButton" onClick={toggleDropdown}>
          ☰
        </button>
        {isDropdownOpen && (
          <div className="dropdownMenu">
            <button className="menuItem" onClick={handleProfileClick}>
              Profile
            </button>
            <button className="menuItem" onClick={handleLogoutClick}>
              Logout
            </button>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;