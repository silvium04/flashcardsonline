import React from 'react';
import './SidebySideDiv.css';

const SideBySideDiv = () => {
  return (
    <div><h1 className='center'>Decks</h1>
    <div className="sideBySideContainer">
      <div className="sideBySideItem" style={{ backgroundColor: 'cyan' }}></div>
      <div className="sideBySideItem" style={{ backgroundColor: 'cyan' }}></div>
      <div className="sideBySideItem" style={{ backgroundColor: "cyan" }}></div>
    </div>
    </div>
  );
};

export default SideBySideDiv; // Ensure this is a default export